package uo.ri.model;

import java.util.HashSet;
import java.util.Set;

import uo.ri.model.exception.BusinessException;
import uo.ri.model.types.Address;
import uo.ri.model.util.Checker;

public class Cliente {

	private String dni;

	private String nombre;
	private String apellidos;

	private Address address;

	private Set<Vehiculo> vehiculos = new HashSet<Vehiculo>();

	private Set<MedioPago> mediosPago = new HashSet<MedioPago>();

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

}