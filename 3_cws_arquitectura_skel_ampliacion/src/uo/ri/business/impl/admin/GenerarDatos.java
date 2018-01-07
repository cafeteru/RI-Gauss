package uo.ri.business.impl.admin;

import java.util.ArrayList;
import java.util.List;

import alb.util.date.DateUtil;
import alb.util.random.Random;
import uo.ri.business.impl.Command;
import uo.ri.business.repository.AveriaRepository;
import uo.ri.business.repository.CargoRepository;
import uo.ri.business.repository.ClienteRepository;
import uo.ri.business.repository.FacturaRepository;
import uo.ri.business.repository.MecanicoRepository;
import uo.ri.business.repository.MedioPagoRepository;
import uo.ri.business.repository.TipoVehiculoRepository;
import uo.ri.business.repository.VehiculoRepository;
import uo.ri.conf.Factory;
import uo.ri.model.Association;
import uo.ri.model.Averia;
import uo.ri.model.Cargo;
import uo.ri.model.Cliente;
import uo.ri.model.Factura;
import uo.ri.model.Mecanico;
import uo.ri.model.Metalico;
import uo.ri.model.TipoVehiculo;
import uo.ri.model.Vehiculo;
import uo.ri.model.types.Address;
import uo.ri.util.exception.BusinessException;

/**
 * Sin terminar, solo crea clientes, mecanicos y recomendaciones
 * 
 * @author igm1990
 *
 */
public class GenerarDatos implements Command<Void> {
	private List<Cliente> clientes = new ArrayList<>();
	private List<Mecanico> mecanicos = new ArrayList<>();

	private int numClientes = 50, numMecanicos = 10;
	private Long numFactura = 0L;
	private TipoVehiculo t;

	private String[] nombres = { "MARIA CARMEN", "MARIA", "CARMEN", "JOSEFA",
			"ISABEL", "ANA MARIA", "MARIA PILAR", "MARIA DOLORES",
			"MARIA TERESA", "ANA", "LAURA", "FRANCISCA", "MARIA ANGELES",
			"CRISTINA", "ANTONIA", "MARTA", "DOLORES", "MARIA ISABEL",
			"MARIA JOSE", "LUCIA", "MARIA LUISA", "PILAR", "ELENA",
			"CONCEPCION", "ANTONIO", "JOSE", "MANUEL", "FRANCISCO", "JUAN",
			"DAVID", "JOSE ANTONIO", "JOSE LUIS", "JAVIER", "FRANCISCO JAVIER",
			"JESUS", "DANIEL", "CARLOS", "MIGUEL", "ALEJANDRO", "JOSE MANUEL",
			"RAFAEL", "PEDRO", "ANGEL", "MIGUEL ANGEL", "JOSE MARIA",
			"FERNANDO", "PABLO", "LUIS", "SERGIO", "JORGE", "ALBERTO",
			"JUAN CARLOS", "JUAN JOSE", "ALVARO", "DIEGO", "ADRIAN",
			"JUAN ANTONIO", "RAUL", "ENRIQUE", "RAMON", "VICENTE", "IVAN" };

	private String[] apellidos = { "Aguilar", "Alonso", "Álvarez", "Arias",
			"Benítez", "Blanco", "Blesa", "Bravo", "Caballero", "Cabrera",
			"Calvo", "Cambil", "Campos", "Cano", "Carmona", "Carrasco",
			"Castillo", "Castro", "Cortés", "Crespo", "Cruz", "Delgado", "Díaz",
			"Díez", "Domínguez", "Durán", "Esteban", "Fernández", "Ferrer",
			"Flores", "Fuentes", "Gallardo", "Gallego", "García", "Garrido",
			"Gil", "Giménez", "Gómez", "González", "Guerrero", "Gutiérrez",
			"Hernández", "Herrera", "Herrero", "Hidalgo", "Ibáñez", "Iglesias",
			"Jiménez", "León", "López", "Lorenzo", "Lozano", "Marín", "Márquez",
			"Martín", "Martínez", "Medina", "Méndez", "Molina", "Montero",
			"Montoro", "Mora", "Morales", "Moreno", "Moya", "Muñoz", "Navarro",
			"Nieto", "Núñez", "Ortega", "Ortiz", "Parra", "Pascual", "Pastor",
			"Peña", "Pérez", "Prieto", "Ramírez", "Ramos", "Rey", "Reyes",
			"Rodríguez", "Román", "Romero", "Rubio", "Ruiz", "Sáez", "Sánchez",
			"Santana", "Santiago", "Santos", "Sanz", "Serrano", "Soler", "Soto",
			"Suárez", "Torres", "Vargas", "Vázquez", "Vega", "Velasco",
			"Vicente", "Vidal" };

	private String[] ciudades = { "Oviedo", "Gijón", "Mieres", "Aviles",
			"Grado", "Navia", "Lugones", "Colloto", "Nava" };

	private String[] marcas = { "Seat", "Opel", "Ford", "Fiat", "Nissan",
			"Hyundai", "Saab", "Ferrari", "Mercedes" };

	ClienteRepository c1 = Factory.repository.forCliente();
	MecanicoRepository m1 = Factory.repository.forMechanic();
	// RecomendacionRepository r1 = Factory.repository.forRecomendacion();
	MedioPagoRepository mp1 = Factory.repository.forMedioPago();
	FacturaRepository f1 = Factory.repository.forFactura();
	CargoRepository ca1 = Factory.repository.forCargo();
	TipoVehiculoRepository tv1 = Factory.repository.forTipo();
	VehiculoRepository v1 = Factory.repository.forVehiculo();
	AveriaRepository a1 = Factory.repository.forAveria();
	// IntervencionRepository i1 = Factory.repository.forIntervencion();
	// RepuestoRepository re1 = Factory.repository.forRepuesto();
	// SustitucionRepository s1 = Factory.repository.forSustitucion();

	@Override
	public Void execute() throws BusinessException {
		t = new TipoVehiculo("Muycarooo", 696969);
		tv1.add(t);
		for (int i = 0; i < numMecanicos; i++) {
			crearMecanico();
		}
		for (int i = 0; i < numClientes; i++) {
			crearCliente();
		}

		return null;
	}

	private void crearMecanico() throws BusinessException {
		Mecanico m = new Mecanico(
				"" + Random.integer(10_000_000, 99_999_999) + Random.string(1));
		String nombre = nombres[Random.integer(0, nombres.length)];
		m.setNombre(nombre);
		m.setApellidos(apellidos[Random.integer(0, apellidos.length)]
				.toUpperCase() + " "
				+ apellidos[Random.integer(0, apellidos.length)].toUpperCase());
		mecanicos.add(m);
		m1.add(m);
	}

	private void crearCliente() throws BusinessException {
		Cliente c = new Cliente(
				"" + Random.integer(10_000_000, 99_999_999) + Random.string(1));
		String nombre = nombres[Random.integer(0, nombres.length)];
		c.setNombre(nombre);
		String apellido = apellidos[Random.integer(0, apellidos.length)];
		c.setApellidos(apellidos[Random.integer(0, apellidos.length)]
				.toUpperCase() + " "
				+ apellidos[Random.integer(0, apellidos.length)].toUpperCase());
		String street = Random.string(15);
		String city = ciudades[Random.integer(0, ciudades.length)]
				.toUpperCase();
		String zipCode = "" + Random.integer(10_000, 50_000);
		Address a = new Address(street, city, zipCode);
		c.setAddress(a);
		String email = nombre.split(" ")[0].toLowerCase()
				+ apellido.toLowerCase() + "@gmail.com";
		c.setEmail(email);
		c.setPhone("" + Random.integer(600_000_000, 699_999_999));
		clientes.add(c);
		c1.add(c);
		Metalico m = new Metalico(c);
		mp1.add(m);
		crearVehiculos(c);
	}

	private void crearVehiculos(Cliente cliente) throws BusinessException {
		Vehiculo v = new Vehiculo("mat-" + Random.integer(1000, 9999999));
		v.setMarca(marcas[Random.integer(0, marcas.length)]);
		v.setModelo("B" + Random.integer(1, 99));
		Association.Poseer.link(cliente, v);
		Association.Clasificar.link(t, v);
		v1.add(v);
		for (int i = 0; i < 5; i++) {
			crearAverias(v);
		}
	}

	int contador = 0;

	private void crearAverias(Vehiculo v) throws BusinessException {
		Averia a = new Averia(DateUtil.addDays(DateUtil.now(), -(++contador)),
				v);
		a1.add(a);

		Mecanico m = mecanicos.get(Random.integer(0, mecanicos.size()));
		a.assignTo(m);
		a.markAsFinished();
		a.setImporte(Random.integer(525, 1000));
		Factura f = new Factura(++numFactura);
		f.addAveria(a);
		f1.add(f);
		Metalico m1 = (Metalico) v.getCliente().getMediosPago().iterator()
				.next();
		Cargo c = new Cargo(f, m1, f.getImporte());
		f.settle();
		ca1.add(c);
	}

}
