package uo.ri.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import uo.ri.model.types.Address;

@Entity
@Table(name = "TCLIENTES")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	// HACER EN TODAS LAS CLAVES NATURALES (IDENTIDAD DEL MODELO DEL DOMINIO)
	@Column(unique = true)
	private String dni;
	private String nombre;
	private String apellidos;

	private Address address;
	/*
	 * private String telefono; private String email;
	 */

	@OneToMany(mappedBy = "cliente")
	private Set<Vehiculo> vehiculos = new HashSet<Vehiculo>();
	@OneToMany(mappedBy = "cliente")
	private Set<MedioPago> medios = new HashSet<MedioPago>();

	@Transient
	private Set<Recomendacion> recomendacionesHechas = new HashSet<Recomendacion>();
	@Transient
	private Recomendacion recomendacionRecibida;

	Cliente() {
	}

	public Cliente(String dni) {
		// constructor que recibe la identidad
		super();
		this.dni = dni;
	}

	public Cliente(String dni, String nombre, String apellidos, Address address, String telefono, String email) {
		this(dni);
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.address = address;
		/*
		 * this.telefono = telefono; this.email = email;
		 */
	}

	public Cliente(String dni, String nombre, String apellidos) {
		this(dni);
		this.nombre = nombre;
		this.apellidos = apellidos;
	}

	public Long getId() {
		return id;
	}

	public Recomendacion getRecomendacionRecibida() {
		return recomendacionRecibida;
	}

	void _setRecomendacionRecibida(Recomendacion recomendacionRecibida) {
		this.recomendacionRecibida = recomendacionRecibida;
	}

	Set<Vehiculo> _getVehiculos() {
		return vehiculos;
	}

	public Set<Vehiculo> getVehiculos() {
		return new HashSet<>(vehiculos);
	}

	public Set<MedioPago> getMediosPago() {
		return new HashSet<>(medios);
	}

	Set<MedioPago> _getMediosPago() {
		return medios;
	}

	public Set<Recomendacion> getRecomendacionesHechas() {
		return new HashSet<>(recomendacionesHechas);
	}

	Set<Recomendacion> _getRecomendacionesHechas() {
		return recomendacionesHechas;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDni() {
		return dni;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	/*
	 * public String getTelefono() { return telefono; }
	 * 
	 * public void setTelefono(String telefono) { this.telefono = telefono; }
	 * 
	 * public String getEmail() { return email; }
	 * 
	 * public void setEmail(String email) { this.email = email; }
	 */

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
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
		return "Cliente [dni=" + dni + ", nombre=" + nombre + ", apellidos=" + apellidos + ", address=" + address
				+ ", telefono=" + "]";
		// telefono + ", email=" + email + ;
	}

	public boolean elegibleBonoPorRecomendaciones() {
		if (comprobarAverias() == true && comprobarAveriasDeRecomendados() == true) {
			int countRecomendacionesHechas = 0;
			for (Recomendacion r : recomendacionesHechas) {
				if (r.isUsada_bono() == false)
					countRecomendacionesHechas++;
			}

			if (countRecomendacionesHechas >= 3)
				return true;
		}
		return false;
	}

	public boolean comprobarAverias() {
		for (Vehiculo v : vehiculos)
			if (v.getNumAverias() > 0)
				return true;
		return false;
	}

	public boolean comprobarAveriasDeRecomendados() {
		for (Recomendacion r : recomendacionesHechas) {
			for (Vehiculo v : r.getRecomendado().getVehiculos())
				if (v.getNumAverias() > 0)
					return true;
		}
		return false;
	}

}
