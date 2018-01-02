package uo.ri.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import alb.util.random.Random;
import uo.ri.util.exception.BusinessException;
import uo.ri.util.exception.Check;
import uo.ri.business.dto.VoucherSummary;
import uo.ri.model.types.Address;

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

	public List<Averia> getAveriasBono3NoUsadas() {
		List<Averia> averias = new ArrayList<>();
		for (Vehiculo v : vehiculos) {
			List<Averia> averiasVehiculo = v.getAveriasBono3NoUsadas();
			for (Averia a : averiasVehiculo)
				averias.add(a);
		}
		return averias;
	}

	public Set<Bono> crearBonoAveria() throws BusinessException {
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

	public boolean elegibleBonoPorRecomendaciones() throws BusinessException {
		if (reparacionRealizada()) {
			List<Recomendacion> lista = listarRecomendacionBono();
			int bonos = lista.size() / 3;
			if (bonos > 0) {
				crearBono(bonos, 25, "Por recomendación");
				marcarRecomendaciones(lista, bonos * 3);
				return true;
			}
		}
		return false;
	}

	private boolean reparacionRealizada() {
		for (Vehiculo v : vehiculos) {
			if (v.getAverias().size() > 0)
				return true;
		}
		return false;
	}

	private List<Recomendacion> listarRecomendacionBono() {
		List<Recomendacion> lista = new ArrayList<>();
		for (Recomendacion r : rRealizadas) {
			if (!r.isUsadaBono() && r.getRecomendado().reparacionRealizada()) {
				lista.add(r);
			}
		}
		return lista;
	}

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

	private void marcarRecomendaciones(List<Recomendacion> lista, int limite) {
		for (int i = 0; i < limite; i++) {
			lista.get(i).markAsUsadaBono();
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

	public Set<Bono> getFacturasBono500() throws BusinessException {
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

	public Set<Bono> getRecomendacion3() throws BusinessException {
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

	public VoucherSummary realizarResumen() {
		VoucherSummary v = new VoucherSummary();
		v.dni = getDni();
		v.name = getNombre();
		v.surname = getApellidos();
		int contador = 0;
		for (MedioPago m : mediosPago) {
			if (m instanceof Bono) {
				contador++;
				v.available += ((Bono) m).getDisponible();
				v.consumed += m.getAcumulado();
			}
		}
		v.emitted = contador;
		v.totalAmount = v.available + v.consumed;
		return v;
	}

	public void eliminarMetalico() throws BusinessException {
		for (MedioPago m : mediosPago) {
			if (m instanceof Metalico) {
				Association.Pagar.unlink(this, m);
			}
		}
	}

	public void eliminarRecomendaciones() throws BusinessException {
		eliminarRecomendacionRecibida();
		eliminarRecomendacionesRealizadas();
	}

	private void eliminarRecomendacionRecibida() throws BusinessException {
		if (rRecibida != null)
			Association.Recomendar.unlink(rRecibida);
	}

	private void eliminarRecomendacionesRealizadas() throws BusinessException {
		if (!rRealizadas.isEmpty()) {
			for (Recomendacion r : getRecomendacionesHechas()) {
				Association.Recomendar.unlink(r);
			}
		}
	}

}