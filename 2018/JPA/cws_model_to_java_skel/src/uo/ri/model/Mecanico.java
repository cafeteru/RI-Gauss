package uo.ri.model;

import java.util.HashSet;
import java.util.Set;

public class Mecanico {

	private String dni;
	private String apellidos;
	private String nombre;

	private Set<Averia> averias = new HashSet<>();
	private Set<Intervencion> intervenciones = new HashSet<>();
	private Set<Contract> contracts = new HashSet<>();

	public Mecanico(String dni) {
		super();
		this.dni = dni;
	}

	public Mecanico(String dni, String nombre, String apellidos) {
		this(dni);
		this.nombre = nombre;
		this.apellidos = apellidos;
	}

	public Mecanico() {
	}

	public String getDni() {
		return dni;
	}

	public String getApellidos() {
		return apellidos;
	}

	public String getNombre() {
		return nombre;
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
		Mecanico other = (Mecanico) obj;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Mecanico [dni=" + dni + ", apellidos=" + apellidos + ", nombre="
				+ nombre + "]";
	}

	Set<Averia> _getAverias() {
		return averias;
	}

	Set<Intervencion> _getIntervenciones() {
		return intervenciones;
	}

	public Set<Intervencion> getIntervenciones() {
		return new HashSet<>(intervenciones);
	}

	Set<Averia> _getAsignadas() {
		return averias;
	}

	public Set<Averia> getAsignadas() {
		return new HashSet<>(averias);
	}

	public Set<Contract> getContracts() {
		return new HashSet<>(contracts);
	}

	Set<Contract> _getContracts() {
		return contracts;
	}

	public Contract getActiveContract() {
		for (Contract contract : contracts) {
			if (!contract.isFinished())
				return contract;
		}
		return null;
	}

}
