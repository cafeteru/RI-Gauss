package uo.ri;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import alb.util.date.DateUtil;
import uo.ri.model.Association;
import uo.ri.model.Averia;
import uo.ri.model.Bono;
import uo.ri.model.Cargo;
import uo.ri.model.Cliente;
import uo.ri.model.Factura;
import uo.ri.model.Intervencion;
import uo.ri.model.Mecanico;
import uo.ri.model.MedioPago;
import uo.ri.model.Metalico;
import uo.ri.model.Repuesto;
import uo.ri.model.Sustitucion;
import uo.ri.model.TarjetaCredito;
import uo.ri.model.TipoVehiculo;
import uo.ri.model.Vehiculo;
import uo.ri.model.types.Address;
import uo.ri.util.exception.BusinessException;

public class GaussPersistence {

	private EntityManagerFactory factory;
	private Cliente cliente, cl1, cl2, cl3;
	private Sustitucion sustitucion, s1, s2, s3;
	private Cargo cargo, c1, c2, c3;

	@Before
	public void setUp() throws BusinessException {
		factory = Persistence.createEntityManagerFactory("carworkshop");
		List<Object> graph = createGraph();
		persistGraph(graph);
	}

	@After
	public void tearDown() {
		removeGraph();
		factory.close();
	}

	@Test
	public void testCliente() {
		EntityManager mapper = factory.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();

		Cliente cl = mapper.merge(cliente);

		assertNotNull(cl.getId());
		assertEquals(cl.getApellidos(), "apellidos");
		assertEquals(cl.getNombre(), "nombre");
		assertEquals(cl.getDni(), "dni");

		trx.commit();
		mapper.close();
	}

	@Test
	public void testVehiculos() {
		EntityManager mapper = factory.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();

		Cliente cl = mapper.merge(cliente);
		Set<Vehiculo> vehiculos = cl.getVehiculos();
		Vehiculo v = vehiculos.iterator().next();

		assertTrue(vehiculos.size() == 1);
		assertSame(v.getCliente(), cl);
		assertNotNull(v.getId());
		assertEquals(v.getMarca(), "seat");
		assertEquals(v.getModelo(), "ibiza");
		assertEquals(v.getMatricula(), "1234 GJI");

		trx.commit();
		mapper.close();
	}

	@Test
	public void testSustituir() {
		EntityManager mapper = factory.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();

		Sustitucion s = mapper.merge(sustitucion);
		Repuesto r = s.getRepuesto();
		Intervencion i = s.getIntervencion();

		assertTrue(r.getSustituciones().contains(s));
		assertTrue(i.getSustituciones().contains(s));

		trx.commit();
		mapper.close();
	}

	@Test
	public void testTrabajarArreglar() {
		EntityManager mapper = factory.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();

		Sustitucion s = mapper.merge(sustitucion);
		Intervencion i = s.getIntervencion();
		Mecanico m = i.getMecanico();
		Averia a = i.getAveria();

		assertTrue(m.getIntervenciones().contains(i));
		assertTrue(a.getIntervenciones().contains(i));

		trx.commit();
		mapper.close();
	}

	@Test
	public void testTener() {
		EntityManager mapper = factory.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();

		Sustitucion s = mapper.merge(sustitucion);
		Averia a = s.getIntervencion().getAveria();
		Vehiculo v = a.getVehiculo();

		assertTrue(v.getAverias().contains(a));

		trx.commit();
		mapper.close();
	}

	@Test
	public void testSerPoseer() {
		EntityManager mapper = factory.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();

		Sustitucion s = mapper.merge(sustitucion);
		Vehiculo v = s.getIntervencion().getAveria().getVehiculo();
		TipoVehiculo tv = v.getTipo();
		Cliente c = v.getCliente();

		assertTrue(tv.getVehiculos().contains(v));
		assertTrue(c.getVehiculos().contains(v));

		trx.commit();
		mapper.close();
	}

	@Test
	public void testCargar() {
		EntityManager mapper = factory.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();

		Cargo c = mapper.merge(cargo);
		Factura f = c.getFactura();
		MedioPago mp = c.getMedioPago();

		assertTrue(mp.getCargos().contains(c));
		assertTrue(f.getCargos().contains(c));

		trx.commit();
		mapper.close();
	}

	@Test
	public void testFacturar() {
		EntityManager mapper = factory.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();

		Sustitucion s = mapper.merge(sustitucion);
		Averia a = s.getIntervencion().getAveria();
		Factura f = a.getFactura();

		assertTrue(f.getAverias().contains(a));

		trx.commit();
		mapper.close();
	}

	@Test
	public void testPagar() {
		EntityManager mapper = factory.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();

		Sustitucion s = mapper.merge(sustitucion);
		Cliente c = s.getIntervencion().getAveria().getVehiculo().getCliente();
		Set<MedioPago> medios = c.getMediosPago();

		for (MedioPago mp : medios) {
			assertSame(mp.getCliente(), c);
		}

		trx.commit();
		mapper.close();
	}

	protected List<Object> createGraph() throws BusinessException {

		cliente = new Cliente("dni", "nombre", "apellidos");
		Address address = new Address("street", "city", "zipcode");
		cliente.setAddress(address);

		cl1 = new Cliente("dni1", "nombre1", "apellidos1");
		Address a1 = new Address("street1", "city1", "zipcode1");
		cl1.setAddress(a1);

		cl2 = new Cliente("dni2", "nombre2", "apellidos2");
		Address a2 = new Address("street2", "city2", "zipcode2");
		cl2.setAddress(a2);

		cl3 = new Cliente("dni3", "nombre3", "apellidos3");
		Address a3 = new Address("street3", "city3", "zipcode3");
		cl3.setAddress(a3);

		Vehiculo vehiculo = new Vehiculo("1234 GJI", "seat", "ibiza");
		Association.Poseer.link(cliente, vehiculo);

		Vehiculo vehiculo1 = new Vehiculo("12341", "seat", "cordoba");
		Association.Poseer.link(cl1, vehiculo1);

		Vehiculo vehiculo2 = new Vehiculo("12342", "renault", "clio");
		Association.Poseer.link(cl2, vehiculo2);

		Vehiculo vehiculo3 = new Vehiculo("12343", "renault", "megane");
		Association.Poseer.link(cl3, vehiculo3);

		TipoVehiculo tipoVehiculo = new TipoVehiculo("coche", 50.0);
		TipoVehiculo tipoVehiculo1 = new TipoVehiculo("coche2", 250.0);
		Association.Clasificar.link(tipoVehiculo, vehiculo);
		Association.Clasificar.link(tipoVehiculo, vehiculo1);
		Association.Clasificar.link(tipoVehiculo1, vehiculo2);
		Association.Clasificar.link(tipoVehiculo1, vehiculo3);

		Averia averia = new Averia(vehiculo, "falla la junta la trocla");
		Averia averia1 = new Averia(vehiculo1, "falla la junta la trocla");
		Averia averia2 = new Averia(vehiculo2, "falla la junta la trocla");
		Averia averia3 = new Averia(vehiculo3, "falla la junta la trocla");
		Mecanico mecanico = new Mecanico("dni-mecanico", "nombre", "apellidos");
		averia.assignTo(mecanico);
		averia1.assignTo(mecanico);
		averia2.assignTo(mecanico);
		averia3.assignTo(mecanico);

		Intervencion intervencion = new Intervencion(mecanico, averia);
		intervencion.setMinutos(60);
		averia.markAsFinished();

		Intervencion intervencion1 = new Intervencion(mecanico, averia1);
		intervencion1.setMinutos(600);
		averia1.markAsFinished();

		Intervencion intervencion2 = new Intervencion(mecanico, averia2);
		intervencion2.setMinutos(160);
		averia2.markAsFinished();

		Intervencion intervencion3 = new Intervencion(mecanico, averia3);
		intervencion3.setMinutos(260);
		averia3.markAsFinished();

		Repuesto repuesto = new Repuesto("R1001", "junta la trocla", 100.0);
		sustitucion = new Sustitucion(repuesto, intervencion);
		sustitucion.setCantidad(2);

		Repuesto repuesto1 = new Repuesto("R1002", "junta la trocla", 1020.0);
		s1 = new Sustitucion(repuesto1, intervencion1);
		s1.setCantidad(2);

		Repuesto repuesto2 = new Repuesto("R1003", "junta la trocla", 1100.0);
		s2 = new Sustitucion(repuesto2, intervencion2);
		s2.setCantidad(2);

		Repuesto repuesto3 = new Repuesto("R1004", "junta la trocla", 1300.0);
		s3 = new Sustitucion(repuesto3, intervencion3);
		s3.setCantidad(2);

		Bono bono = new Bono("B-100", 100.0);
		bono.setDescripcion("Voucher just for testing");
		Association.Pagar.link(cliente, bono);

		TarjetaCredito tarjetaCredito = new TarjetaCredito("1234567");
		tarjetaCredito.setTipo("Visa");
		tarjetaCredito.setValidez(DateUtil.inYearsTime(1));
		Association.Pagar.link(cliente, tarjetaCredito);

		Metalico metalico = new Metalico(cliente);

		Bono bono1 = new Bono("B", 100000000.0);
		bono.setDescripcion("Testiiiiiingggg");
		Association.Pagar.link(cl1, bono1);

		TarjetaCredito tarjetaCredito2 = new TarjetaCredito("22121990");
		tarjetaCredito.setTipo("MasterGauss");
		tarjetaCredito.setValidez(DateUtil.inYearsTime(100));
		Association.Pagar.link(cl2, tarjetaCredito2);

		Metalico metalico3 = new Metalico(cl3);

		Factura factura = new Factura(1L);
		factura.setFecha(DateUtil.today());
		factura.addAveria(averia);

		Factura factura1 = new Factura(2L);
		factura1.setFecha(DateUtil.today());
		factura1.addAveria(averia1);

		Factura factura2 = new Factura(3L);
		factura2.setFecha(DateUtil.today());
		factura.addAveria(averia2);

		Factura factura3 = new Factura(4L);
		factura3.setFecha(DateUtil.today());
		factura.addAveria(averia3);

		cargo = new Cargo(factura, tarjetaCredito, factura.getImporte());
		c1 = new Cargo(factura1, bono1, factura1.getImporte());
		c2 = new Cargo(factura2, tarjetaCredito2, factura2.getImporte());
		c3 = new Cargo(factura3, metalico3, factura3.getImporte());

		List<Object> res = new LinkedList<Object>();

		res.add(tipoVehiculo);
		res.add(tipoVehiculo1);
		res.add(repuesto);
		res.add(repuesto1);
		res.add(repuesto2);
		res.add(repuesto3);
		res.add(mecanico);
		res.add(cl3);
		res.add(cl2);
		res.add(cl1);
		res.add(cliente);
		res.add(bono);
		res.add(tarjetaCredito);
		res.add(metalico);
		res.add(bono1);
		res.add(tarjetaCredito2);
		res.add(metalico3);
		res.add(vehiculo);
		res.add(vehiculo1);
		res.add(vehiculo2);
		res.add(vehiculo3);
		res.add(factura);
		res.add(factura1);
		res.add(factura2);
		res.add(factura3);
		res.add(averia);
		res.add(averia1);
		res.add(averia2);
		res.add(averia3);
		res.add(intervencion);
		res.add(intervencion1);
		res.add(intervencion2);
		res.add(intervencion3);
		res.add(sustitucion);
		res.add(s1);
		res.add(s2);
		res.add(s3);
		res.add(cargo);
		res.add(c1);
		res.add(c2);
		res.add(c3);

		return res;
	}

	private void persistGraph(List<Object> graph) {
		EntityManager mapper = factory.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();

		for (Object o : graph) {
			mapper.persist(o);
		}

		trx.commit();
		mapper.close();
	}

	private void removeGraph() {
		EntityManager mapper = factory.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();

		List<Object> merged = mergeGraph(mapper);

		for (Object o : merged) {
			mapper.remove(o);
		}

		trx.commit();
		mapper.close();
	}

	private List<Object> mergeGraph(EntityManager mapper) {
		List<Object> res = new LinkedList<Object>();

		res.add(mapper.merge(cargo));
		res.add(mapper.merge(c1));
		res.add(mapper.merge(c2));
		res.add(mapper.merge(c3));

		Sustitucion s = mapper.merge(sustitucion);
		res.add(s);
		res.add(s.getRepuesto());
		res.add(s.getIntervencion());
		res.add(s.getIntervencion().getMecanico());
		res.add(s.getIntervencion().getAveria());
		res.add(s.getIntervencion().getAveria().getVehiculo());
		res.add(s.getIntervencion().getAveria().getVehiculo().getTipo());
		res.add(s.getIntervencion().getAveria().getFactura());

		s = mapper.merge(s1);
		res.add(s);
		res.add(s.getRepuesto());
		res.add(s.getIntervencion());
		res.add(s.getIntervencion().getMecanico());
		res.add(s.getIntervencion().getAveria());
		res.add(s.getIntervencion().getAveria().getVehiculo());
		res.add(s.getIntervencion().getAveria().getVehiculo().getTipo());
		res.add(s.getIntervencion().getAveria().getFactura());

		s = mapper.merge(s2);
		res.add(s);
		res.add(s.getRepuesto());
		res.add(s.getIntervencion());
		res.add(s.getIntervencion().getMecanico());
		res.add(s.getIntervencion().getAveria());
		res.add(s.getIntervencion().getAveria().getVehiculo());
		res.add(s.getIntervencion().getAveria().getVehiculo().getTipo());
		res.add(s.getIntervencion().getAveria().getFactura());

		s = mapper.merge(s3);
		res.add(s);
		res.add(s.getRepuesto());
		res.add(s.getIntervencion());
		res.add(s.getIntervencion().getMecanico());
		res.add(s.getIntervencion().getAveria());
		res.add(s.getIntervencion().getAveria().getVehiculo());
		res.add(s.getIntervencion().getAveria().getVehiculo().getTipo());
		res.add(s.getIntervencion().getAveria().getFactura());

		Cliente cl = mapper.merge(cliente);
		res.add(cl);
		for (MedioPago mp : cl.getMediosPago()) {
			res.add(mp);
		}

		Cliente cl1 = mapper.merge(this.cl1);
		res.add(cl1);
		for (MedioPago mp : cl1.getMediosPago()) {
			res.add(mp);
		}

		Cliente cl2 = mapper.merge(this.cl2);
		res.add(cl2);
		for (MedioPago mp : cl2.getMediosPago()) {
			res.add(mp);
		}

		Cliente cl3 = mapper.merge(this.cl3);
		res.add(cl3);
		for (MedioPago mp : cl.getMediosPago()) {
			res.add(mp);
		}

		return res;
	}
}
