import java.util.HashSet;
import java.util.Set;

import alb.util.random.Random;
import uo.ri.model.Cliente;
import uo.ri.model.Mecanico;
import uo.ri.model.Recomendacion;
import uo.ri.model.types.Address;
import uo.ri.util.exception.BusinessException;

public class GenerarDatos {
	private static Set<Cliente> clientes = new HashSet<>();
	private static Set<Mecanico> mecanicos = new HashSet<>();

	private static int numClientes = 50, numMecanicos = 10;

	private static String[] nombres = { "MARIA CARMEN", "MARIA", "CARMEN",
			"JOSEFA", "ISABEL", "ANA MARIA", "MARIA PILAR", "MARIA DOLORES",
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

	private static String[] apellidos = { "Aguilar", "Alonso", "Álvarez",
			"Arias", "Benítez", "Blanco", "Blesa", "Bravo", "Caballero",
			"Cabrera", "Calvo", "Cambil", "Campos", "Cano", "Carmona",
			"Carrasco", "Castillo", "Castro", "Cortés", "Crespo", "Cruz",
			"Delgado", "Díaz", "Díez", "Domínguez", "Durán", "Esteban",
			"Fernández", "Ferrer", "Flores", "Fuentes", "Gallardo", "Gallego",
			"García", "Garrido", "Gil", "Giménez", "Gómez", "González",
			"Guerrero", "Gutiérrez", "Hernández", "Herrera", "Herrero",
			"Hidalgo", "Ibáñez", "Iglesias", "Jiménez", "León", "López",
			"Lorenzo", "Lozano", "Marín", "Márquez", "Martín", "Martínez",
			"Medina", "Méndez", "Molina", "Montero", "Montoro", "Mora",
			"Morales", "Moreno", "Moya", "Muñoz", "Navarro", "Nieto", "Núñez",
			"Ortega", "Ortiz", "Parra", "Pascual", "Pastor", "Peña", "Pérez",
			"Prieto", "Ramírez", "Ramos", "Rey", "Reyes", "Rodríguez", "Román",
			"Romero", "Rubio", "Ruiz", "Sáez", "Sánchez", "Santana", "Santiago",
			"Santos", "Sanz", "Serrano", "Soler", "Soto", "Suárez", "Torres",
			"Vargas", "Vázquez", "Vega", "Velasco", "Vicente", "Vidal" };

	private static String[] ciudades = { "Oviedo", "Gijón", "Mieres", "Aviles",
			"Grado", "Navia", "Lugones", "Colloto", "Nava" };

	public static void main(String[] args) throws BusinessException {
		for (int i = 0; i < numClientes; i++) {
			crearCliente();
		}
		for (int i = 0; i < numMecanicos; i++) {
			crearMecanico();
		}

		for (int i = 0; i < numClientes; i++) {
			crearRecomendaciones();
		}
		imprimirDatos();
	}

	private static void imprimirDatos() {
		for (Cliente c1 : clientes) {
			System.out.println(c1);
			System.out.println(c1.getRecomendacionRecibida());
			System.out.println(c1.getRecomendacionesHechas().size());
		}
		for (Mecanico c1 : mecanicos) {
			System.out.println(c1);
		}
	}

	private static void crearCliente() throws BusinessException {
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
	}

	private static void crearMecanico() throws BusinessException {
		Mecanico m = new Mecanico(
				"" + Random.integer(10_000_000, 99_999_999) + Random.string(1));
		String nombre = nombres[Random.integer(0, nombres.length)];
		m.setNombre(nombre);
		m.setApellidos(apellidos[Random.integer(0, apellidos.length)]
				.toUpperCase() + " "
				+ apellidos[Random.integer(0, apellidos.length)].toUpperCase());
		mecanicos.add(m);
	}

	private static void crearRecomendaciones() throws BusinessException {
		Cliente cliente1 = null, cliente2 = null;
		int tamaño = clientes.size();
		int posicion1 = Random.integer(0, tamaño);
		int posicion2 = 0;

		do {
			posicion2 = Random.integer(0, tamaño);
		} while (posicion1 == posicion2);

		int contador = 0;
		for (Cliente cliente : clientes) {
			if (contador == posicion1) {
				cliente1 = cliente;
			} else if (contador == posicion2)
				cliente2 = cliente;
			contador++;
		}
		if (cliente2.getRecomendacionRecibida() == null)
			new Recomendacion(cliente1, cliente2);
	}

}
