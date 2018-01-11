package uo.ri.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import alb.util.random.Random;
import uo.ri.model.types.Address;
import uo.ri.util.exception.BusinessException;
import uo.ri.util.exception.Check;

@Entity
@Table(name = "TCLIENTES")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String dni;

	private String nombre;
	private String apellidos;

	@Embedded
	private Address address;

	@OneToMany(mappedBy = "cliente")
	private Set<Vehiculo> vehiculos = new HashSet<Vehiculo>();

	@OneToMany(mappedBy = "cliente")
	private Set<MedioPago> mediosPago = new HashSet<MedioPago>();

	@OneToOne
	private Recomendacion rRecibida;

	@OneToMany(mappedBy = "recomendador")
	private Set<Recomendacion> rRealizadas = new HashSet<Recomendacion>();

	private String email;
	private String phone;

	Cliente() {
	}

	public Cliente(String dni) throws BusinessException {
		this.dni = Check.checkString(dni, "Dni");
	}

	public Cliente(String dni, String nombre, String apellidos)
			throws BusinessException {
		this(dni);
		this.nombre = nombre;
		this.apellidos = apellidos;
	}

	public Long getId() {
		return id;
	}

	public String getDni() {
		return new String(dni);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Set<Vehiculo> getVehiculos() {
		return new HashSet<Vehiculo>(vehiculos);
	}

	Set<Vehiculo> _getVehiculos() {
		return vehiculos;
	}

	public Set<MedioPago> getMediosPago() {
		return new HashSet<MedioPago>(mediosPago);
	}

	Set<MedioPago> _getMediosPago() {
		return mediosPago;
	}

	public Recomendacion getRecomendacionRecibida() {
		return rRecibida;
	}

	void _setRecomendador(Recomendacion recomendador) {
		this.rRecibida = recomendador;
	}

	public Set<Recomendacion> getRecomendacionesHechas() {
		return new HashSet<>(rRealizadas);
	}

	Set<Recomendacion> _getRecomendados() {
		return rRealizadas;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Crea una lista bono para el cliente.
	 * 
	 * @param numero
	 *            Cantidad de bonos a crear.
	 * @param cantidad
	 *            Importe disponible dentro de cada bono
	 * @param descripcion
	 *            Descripción del bono.
	 * @return La lista de bonos creados
	 * @throws BusinessException
	 */
	private Set<Bono> crearBono(int numero, int cantidad, String descripcion)
			throws BusinessException {
		Set<Bono> bonos = new HashSet<>();
		while (numero > 0) {
			String nombre = "B" + Random.string('0', '9', 4);
			Bono bono = new Bono(nombre, descripcion, cantidad);
			Association.Pagar.link(this, bono);
			bonos.add(bono);
			numero--;
		}
		return bonos;
	}

	/**
	 * Crea los bonos por tres averías y marca las averias para que no puedan
	 * volver a generar bonos con ellas.
	 * 
	 * @return Una lista con los bono creados
	 * @throws BusinessException
	 */
	public Set<Bono> crearBono3Averia() throws BusinessException {
		List<Averia> lista = getAveriasBono3NoUsadas();
		int numBonos = lista.size() / 3;
		if (numBonos > 0) {
			for (int i = 0; i < numBonos * 3; i++) {
				lista.get(i).markAsBono3Used();
			}
			return crearBono(numBonos, 20, "Por tres averías");
		}
		return null;
	}

	/**
	 * Inspecciona todas las averías de los vehiculos del cliente y seleccionan
	 * la que se pueden usar para generar bonos.
	 * 
	 * @return La lista con las averias que se pueden usar para generar bonos
	 */
	public List<Averia> getAveriasBono3NoUsadas() {
		List<Averia> averias = new ArrayList<>();
		for (Vehiculo v : vehiculos) {
			List<Averia> averiasVehiculo = v.getAveriasBono3NoUsadas();
			for (Averia a : averiasVehiculo)
				averias.add(a);
		}
		return averias;
	}

	/**
	 * Crea los bonos por recomendaciones realizadas, 3 o más. Además marca las
	 * recomendaciones para que no generen bonos adicionales.
	 * 
	 * @return Una lista con los bonos creados
	 * @throws BusinessException
	 */
	public Set<Bono> crearBono3Recomendacion() throws BusinessException {
		if (reparacionRealizada()) {
			List<Recomendacion> lista = listarRecomendacionBono();
			int bonos = lista.size() / 3;
			if (bonos > 0) {
				marcarRecomendaciones(lista, bonos * 3);
				return crearBono(bonos, 25, "Por recomendación");
			}
		}
		return null;
	}

	/**
	 * Método para los test de Alberto para crear los bonos por recomendaciones.
	 * 
	 * @return True si se ha creado algún bono. En caso contrario false.
	 * @throws BusinessException
	 */
	public boolean elegibleBonoPorRecomendaciones() throws BusinessException {
		return crearBono3Recomendacion() != null;
	}

	/**
	 * Comprueba que el cliente ha utilizado los servicios del taller.
	 * 
	 * @return True en caso afirmativo, false en caso negativo.
	 */
	private boolean reparacionRealizada() {
		for (Vehiculo v : vehiculos) {
			if (v.getAverias().size() > 0)
				return true;
		}
		return false;
	}

	/**
	 * Lista todas las recomendaciones que se pueden usar para generar bonos.
	 * Para ello comprueba que no hayan sido usadas ya para generar bonos y que
	 * el cliente recomendado hay realizado alguna gestión en el taller.
	 * 
	 * @return Una lista con las recomendaciones seleccionadas
	 */
	private List<Recomendacion> listarRecomendacionBono() {
		List<Recomendacion> lista = new ArrayList<>();
		for (Recomendacion r : rRealizadas) {
			if (!r.isUsadaBono() && r.getRecomendado().reparacionRealizada()) {
				lista.add(r);
			}
		}
		return lista;
	}

	/**
	 * Marca las recomendaciones que han sido usadas para generar bonos.
	 * 
	 * @param lista
	 *            Lista de recomendaciones totales del cliente
	 * @param limite
	 *            Cantidad de recomendaciones que se deben marcar.
	 */
	private void marcarRecomendaciones(List<Recomendacion> lista, int limite) {
		for (int i = 0; i < limite; i++) {
			lista.get(i).markAsUsadaBono();
		}
	}

	/**
	 * Método que crear los bonos por facturas con importes mayores de 500 €.
	 * 
	 * @return Lista de bonos creados.
	 * @throws BusinessException
	 */
	public Set<Bono> crearBonoFacturas500() throws BusinessException {
		Set<Factura> facturas = new HashSet<Factura>();
		for (Vehiculo v : vehiculos) {
			for (Averia a : v.getAverias()) {
				if (a.getFactura() != null
						&& a.getFactura().puedeGenerarBono500()) {
					facturas.add(a.getFactura());
				}
			}
		}
		Set<Bono> bonos = new HashSet<Bono>();
		for (Factura f : facturas) {
			bonos.add(f.markAsBono500Used());
		}
		return bonos;
	}

	/**
	 * Desvincula un cliente con su método de pago métalico.
	 * 
	 * @throws BusinessException
	 */
	public void eliminarMetalico() throws BusinessException {
		for (MedioPago m : mediosPago) {
			if (m instanceof Metalico) {
				Association.Pagar.unlink(this, m);
				return; // Por eficiencia, solo hay un métalico por cliente
			}
		}
	}

	/**
	 * Elimina TODAS las recomendaciones del cliente.
	 * 
	 * @throws BusinessException
	 */
	public void eliminarRecomendaciones() throws BusinessException {
		eliminarRecomendacionRecibida();
		eliminarRecomendacionesRealizadas();
	}

	/**
	 * Elimina, si tiene, la recomendación recibida por este cliente.
	 * 
	 * @throws BusinessException
	 */
	private void eliminarRecomendacionRecibida() throws BusinessException {
		if (rRecibida != null)
			Association.Recomendar.unlink(rRecibida);
	}

	/**
	 * Elimina, si existen las recomendaciones realizadas del cliente.
	 * 
	 * @throws BusinessException
	 */
	private void eliminarRecomendacionesRealizadas() throws BusinessException {
		if (!rRealizadas.isEmpty()) {
			for (Recomendacion r : getRecomendacionesHechas()) {
				Association.Recomendar.unlink(r);
			}
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cliente [dni=" + dni + ", nombre=" + nombre + ", apellidos="
				+ apellidos + ", address=" + address + ", email=" + email
				+ ", phone=" + phone + "]";
	}

}