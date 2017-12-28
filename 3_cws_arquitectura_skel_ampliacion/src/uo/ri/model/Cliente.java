package uo.ri.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import uo.ri.util.exception.BusinessException;
import uo.ri.model.types.Address;
import uo.ri.model.util.Checker;

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
	private Recomendacion recomendador;

	@OneToMany(mappedBy = "recomendador")
	private Set<Recomendacion> recomendados = new HashSet<Recomendacion>();

	private String email;
	private String phone;

	Cliente() {
	}

	public Cliente(String dni) throws BusinessException {
		this.dni = Checker.checkString(dni, "Dni");
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
		return dni;
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

	public Recomendacion getRecomendador() {
		return recomendador;
	}

	void _setRecomendador(Recomendacion recomendador) {
		this.recomendador = recomendador;
	}

	public Set<Recomendacion> getRecomendados() {
		return new HashSet<>(recomendados);
	}

	Set<Recomendacion> _getRecomendados() {
		return recomendados;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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
		return "Cliente [nombre=" + nombre + ", apellidos=" + apellidos
				+ ", dni=" + dni + "]";
	}

	public List<Averia> getAveriasBono3NoUsadas() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean elegibleBonoPorRecomendaciones() {
		// TODO Auto-generated method stub
		return false;
	}

	public Set<Recomendacion> getRecomendacionesHechas() {
		// TODO Auto-generated method stub
		return null;
	}

	public Recomendacion getRecomendacionRecibida() {
		// TODO Auto-generated method stub
		return null;
	}

}