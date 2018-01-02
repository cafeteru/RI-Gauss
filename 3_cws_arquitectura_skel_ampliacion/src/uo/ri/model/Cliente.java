package uo.ri.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import alb.util.random.Random;
import uo.ri.util.exception.BusinessException;
import uo.ri.util.exception.Check;
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

	public boolean elegibleBonoPorRecomendaciones() throws BusinessException {
		if (reparacionRealizada()) {
			List<Recomendacion> lista = listarRecomendacionBono();
			int bonos = lista.size() / 3;
			if (bonos > 0) {
				crearBono25(bonos);
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

	private void crearBono25(int numero) throws BusinessException {
		while (numero > 0) {
			Bono bono = new Bono(Random.string(8), "Por recomendación", 25);
			Association.Pagar.link(this, bono);
			numero--;
		}
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

}