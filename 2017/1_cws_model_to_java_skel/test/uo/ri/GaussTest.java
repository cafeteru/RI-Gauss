package uo.ri;

import static org.junit.Assert.*;

import org.junit.Test;

import uo.ri.model.*;
import uo.ri.model.exception.BusinessException;
import uo.ri.model.types.AveriaStatus;
import uo.ri.model.types.FacturaStatus;

public class GaussTest {
	private Cliente cliente;
	private Mecanico mecanico;
	private Vehiculo vehiculo;
	private TipoVehiculo tipo;
	private Averia averia;
	private Bono bono;
	private Metalico metalico;
	private TarjetaCredito tarjeta;
	private Factura factura;
	private Cargo cargo;
	private Intervencion intervencion;
	private Repuesto repuesto;
	private Sustitucion sustitucion;

	@Test(expected = BusinessException.class)
	public void test01() throws BusinessException {
		cliente = new Cliente("");
	}

	@Test(expected = BusinessException.class)
	public void test02() throws BusinessException {
		cliente = new Cliente(null);
	}

	@Test(expected = BusinessException.class)
	public void test03() throws BusinessException {
		mecanico = new Mecanico("");
	}

	@Test(expected = BusinessException.class)
	public void test04() throws BusinessException {
		mecanico = new Mecanico(null);
	}

	@Test(expected = BusinessException.class)
	public void test05() throws BusinessException {
		vehiculo = new Vehiculo("");
	}

	@Test(expected = BusinessException.class)
	public void test06() throws BusinessException {
		vehiculo = new Vehiculo(null);
	}

	@Test(expected = BusinessException.class)
	public void test07() throws BusinessException {
		tipo = new TipoVehiculo("");
	}

	@Test(expected = BusinessException.class)
	public void test08() throws BusinessException {
		tipo = new TipoVehiculo(null);
	}

	@Test(expected = BusinessException.class)
	public void test09() throws BusinessException {
		averia = new Averia(null);
	}

	@Test(expected = BusinessException.class)
	public void test10() throws BusinessException {
		averia = new Averia(null, "Descripción");
	}

	@Test(expected = BusinessException.class)
	public void test11() throws BusinessException {
		averia = new Averia(null, "Descripción");
	}

	@Test(expected = BusinessException.class)
	public void test12() throws BusinessException {
		bono = new Bono(null, null);
	}

	@Test(expected = BusinessException.class)
	public void test13() throws BusinessException {
		bono = new Bono(null, "codigo");
	}

	@Test(expected = BusinessException.class)
	public void test14() throws BusinessException {
		bono = new Bono(null, "");
	}

	@Test(expected = BusinessException.class)
	public void test15() throws BusinessException {
		cliente = new Cliente("a");
		bono = new Bono(cliente, "");
	}

	@Test(expected = BusinessException.class)
	public void test16() throws BusinessException {
		cliente = new Cliente("a");
		bono = new Bono(cliente, null);
	}

	@Test(expected = BusinessException.class)
	public void test17() throws BusinessException {
		metalico = new Metalico(null);
	}

	@Test(expected = BusinessException.class)
	public void test18() throws BusinessException {
		tarjeta = new TarjetaCredito("");
	}

	@Test(expected = BusinessException.class)
	public void test19() throws BusinessException {
		tarjeta = new TarjetaCredito(null);
	}

	@Test(expected = BusinessException.class)
	public void test20() throws BusinessException {
		tarjeta = new TarjetaCredito(null, null);
	}

	@Test(expected = BusinessException.class)
	public void test21() throws BusinessException {
		cliente = new Cliente("a");
		tarjeta = new TarjetaCredito(cliente, null);
	}

	@Test(expected = BusinessException.class)
	public void test22() throws BusinessException {
		cliente = new Cliente("a");
		tarjeta = new TarjetaCredito(cliente, "");
	}

	@Test(expected = BusinessException.class)
	public void test23() throws BusinessException {
		factura = new Factura((long) -1250);
	}

	@Test(expected = BusinessException.class)
	public void test24() throws BusinessException {
		cargo = new Cargo(null, null);
	}

	@Test(expected = BusinessException.class)
	public void test25() throws BusinessException {
		factura = new Factura(1L);
		cargo = new Cargo(factura, null);
	}

	@Test(expected = BusinessException.class)
	public void test26() throws BusinessException {
		bono = new Bono(new Cliente("a"), "as");
		cargo = new Cargo(null, bono);
	}

	@Test(expected = BusinessException.class)
	public void test27() throws BusinessException {
		bono = new Bono(new Cliente("a"), "as");
		cargo = new Cargo(null, bono);
	}

	@Test(expected = BusinessException.class)
	public void test28() throws BusinessException {
		intervencion = new Intervencion(null, null);
	}

	@Test(expected = BusinessException.class)
	public void test29() throws BusinessException {
		mecanico = new Mecanico("a");
		intervencion = new Intervencion(mecanico, null);
	}

	@Test(expected = BusinessException.class)
	public void test30() throws BusinessException {
		averia = new Averia(new Vehiculo("a"));
		intervencion = new Intervencion(null, averia);
	}

	@Test(expected = BusinessException.class)
	public void test31() throws BusinessException {
		repuesto = new Repuesto("");
	}

	@Test(expected = BusinessException.class)
	public void test32() throws BusinessException {
		repuesto = new Repuesto(null);
	}

	@Test(expected = BusinessException.class)
	public void test33() throws BusinessException {
		sustitucion = new Sustitucion(null, null);
	}

	@Test(expected = BusinessException.class)
	public void test34() throws BusinessException {
		repuesto = new Repuesto("a");
		sustitucion = new Sustitucion(repuesto, null);
	}

	@Test(expected = BusinessException.class)
	public void test35() throws BusinessException {
		intervencion = new Intervencion(new Mecanico("A"),
				new Averia(new Vehiculo("A")));
		sustitucion = new Sustitucion(null, intervencion);
	}

	@Test(expected = BusinessException.class)
	public void test36() throws BusinessException {
		vehiculo = new Vehiculo("a");
		assertEquals(0, vehiculo.getNumAverias());
		averia = new Averia(null, vehiculo);
	}

	@Test(expected = BusinessException.class)
	public void test37() throws BusinessException {
		vehiculo = new Vehiculo("a");
		assertEquals(0, vehiculo.getNumAverias());
		averia = new Averia(vehiculo);
		assertNotNull(averia);
		assertEquals(1, vehiculo.getNumAverias());
		averia.assignTo(null);
	}

	@Test(expected = BusinessException.class)
	public void test38() throws BusinessException {
		vehiculo = new Vehiculo("a");
		mecanico = new Mecanico("a");
		assertEquals(0, vehiculo.getNumAverias());
		averia = new Averia(vehiculo);
		assertNotNull(averia);
		assertEquals(1, vehiculo.getNumAverias());
		averia.assignTo(mecanico);
		Mecanico m = new Mecanico("b");
		averia.assignTo(m);
	}

	@Test(expected = BusinessException.class)
	public void test39() throws BusinessException {
		vehiculo = new Vehiculo("a");
		mecanico = new Mecanico("a");
		assertEquals(0, vehiculo.getNumAverias());
		averia = new Averia(vehiculo);
		assertNotNull(averia);
		assertEquals(1, vehiculo.getNumAverias());
		averia.assignTo(mecanico);
		averia.markAsFinished();
		averia.markAsFinished();
	}

	@Test(expected = BusinessException.class)
	public void test40() throws BusinessException {
		vehiculo = new Vehiculo("a");
		mecanico = new Mecanico("a");
		assertEquals(0, vehiculo.getNumAverias());
		averia = new Averia(vehiculo);
		assertNotNull(averia);
		assertEquals(1, vehiculo.getNumAverias());
		averia.assignTo(mecanico);
		averia.markAsFinished();
		averia.assignTo(mecanico);
	}

	@Test(expected = BusinessException.class)
	public void test41() throws BusinessException {
		vehiculo = new Vehiculo("a");
		mecanico = new Mecanico("a");
		assertEquals(0, vehiculo.getNumAverias());
		averia = new Averia(vehiculo);
		assertNotNull(averia);
		assertEquals(1, vehiculo.getNumAverias());
		averia.markAsFinished();
	}

	@Test(expected = BusinessException.class)
	public void test42() throws BusinessException {
		vehiculo = new Vehiculo("a");
		mecanico = new Mecanico("a");
		assertEquals(0, vehiculo.getNumAverias());
		averia = new Averia(vehiculo);
		assertNotNull(averia);
		assertEquals(1, vehiculo.getNumAverias());
		averia.assignTo(mecanico);
		averia.markAsFinished();
		averia.reopen();
		averia.reopen();
	}

	@Test(expected = BusinessException.class)
	public void test43() throws BusinessException {
		vehiculo = new Vehiculo("a");
		mecanico = new Mecanico("a");
		assertEquals(0, vehiculo.getNumAverias());
		averia = new Averia(vehiculo);
		assertNotNull(averia);
		assertEquals(1, vehiculo.getNumAverias());
		averia.markBackToFinished();
	}

	@Test(expected = BusinessException.class)
	public void test44() throws BusinessException {
		vehiculo = new Vehiculo("a");
		mecanico = new Mecanico("a");
		assertEquals(0, vehiculo.getNumAverias());
		averia = new Averia(vehiculo);
		assertNotNull(averia);
		assertEquals(1, vehiculo.getNumAverias());
		averia.assignTo(mecanico);
		averia.markBackToFinished();
	}

	@Test(expected = BusinessException.class)
	public void test45() throws BusinessException {
		vehiculo = new Vehiculo("a");
		mecanico = new Mecanico("a");
		assertEquals(0, vehiculo.getNumAverias());
		averia = new Averia(vehiculo);
		assertNotNull(averia);
		assertEquals(1, vehiculo.getNumAverias());
		averia.assignTo(mecanico);
		averia.markAsFinished();
		averia.markBackToFinished();
	}

	@Test(expected = BusinessException.class)
	public void test46() throws BusinessException {
		factura = new Factura(2L);
		metalico = new Metalico(new Cliente("a"));
		cargo = new Cargo(factura, metalico);
		assertNotEquals(FacturaStatus.ABONADA, factura.getStatus());
		factura.setStatus(FacturaStatus.ABONADA);
		cargo.rewind();
	}

	@Test
	public void test47() throws BusinessException {
		factura = new Factura(2L);
		tarjeta = new TarjetaCredito("a");
		cargo = new Cargo(factura, tarjeta);
		assertNotEquals(FacturaStatus.ABONADA, factura.getStatus());
		cargo.rewind();
	}

	@Test(expected = BusinessException.class)
	public void test48() throws BusinessException {
		factura = new Factura(2L);
		averia = new Averia(new Vehiculo("a"));
		assertEquals(FacturaStatus.SIN_ABONAR, factura.getStatus());
		assertNotEquals(AveriaStatus.TERMINADA, averia.getStatus());
		factura.addAveria(averia);
	}

	@Test(expected = BusinessException.class)
	public void test49() throws BusinessException {
		factura = new Factura(2L);
		averia = new Averia(new Vehiculo("a"));
		assertEquals(FacturaStatus.SIN_ABONAR, factura.getStatus());
		assertNotEquals(AveriaStatus.TERMINADA, averia.getStatus());
		factura.setStatus(FacturaStatus.ABONADA);
		averia.setStatus(AveriaStatus.TERMINADA);
		factura.addAveria(averia);
	}

	@Test(expected = BusinessException.class)
	public void test50() throws BusinessException {
		factura = new Factura(2L);
		averia = new Averia(new Vehiculo("a"));
		assertEquals(FacturaStatus.SIN_ABONAR, factura.getStatus());
		assertNotEquals(AveriaStatus.TERMINADA, averia.getStatus());
		factura.setStatus(FacturaStatus.ABONADA);
		factura.addAveria(averia);
	}

	@Test(expected = BusinessException.class)
	public void test51() throws BusinessException {
		factura = new Factura(2L);
		averia = new Averia(new Vehiculo("a"));
		averia.setStatus(AveriaStatus.TERMINADA);
		factura.addAveria(averia);
		factura.setStatus(FacturaStatus.ABONADA);
		factura.removeAveria(averia);
	}

	@Test
	public void test52() throws BusinessException {
		tipo = new TipoVehiculo("a", 20);
		vehiculo = new Vehiculo("a");
		Association.Clasificar.link(tipo, vehiculo);
		assertNotNull(vehiculo.getTipo());
		assertEquals(1, tipo.getVehiculos().size());
		Association.Clasificar.unlink(tipo, vehiculo);
		assertNull(vehiculo.getTipo());
		assertEquals(0, tipo.getVehiculos().size());
	}

	@Test
	public void test53() throws BusinessException {
		repuesto = new Repuesto("a");
		intervencion = new Intervencion(new Mecanico("a"),
				new Averia(new Vehiculo("a")));
		sustitucion = new Sustitucion(repuesto, intervencion);
		assertNotNull(sustitucion.getIntervencion());
		assertNotNull(sustitucion.getRepuesto());
		assertEquals(1, repuesto.getSustituciones().size());
		assertEquals(1, intervencion.getSustituciones().size());
		Association.Sustituir.unlink(sustitucion);
		assertNull(sustitucion.getIntervencion());
		assertNull(sustitucion.getRepuesto());
		assertEquals(0, repuesto.getSustituciones().size());
		assertEquals(0, intervencion.getSustituciones().size());
	}

}
