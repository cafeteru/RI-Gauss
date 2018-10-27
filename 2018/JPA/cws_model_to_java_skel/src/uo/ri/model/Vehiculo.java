package uo.ri.model;

import java.util.HashSet;
import java.util.Set;

public class Vehiculo {

	private String marca;
	private String matricula;
	private String modelo;

	private int numAverias = 0;

	private Cliente cliente;
	private TipoVehiculo tipoVehiculo;

	private Set<Averia> averias = new HashSet<>();

	public Vehiculo(String matricula) {
		super();
		this.matricula = matricula;
	}

	public Vehiculo(String mat, String marca, String modelo) {
		this(mat);
		this.marca = marca;
		this.modelo = modelo;
		// TODO Auto-generated constructor stub
	}

	public String getMarca() {
		return marca;
	}

	public String getMatricula() {
		return matricula;
	}

	public String getModelo() {
		return modelo;
	}

	public int getNumAverias() {
		return numAverias;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((matricula == null) ? 0 : matricula.hashCode());
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
		Vehiculo other = (Vehiculo) obj;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Vehiculo [marca=" + marca + ", matricula=" + matricula
				+ ", modelo=" + modelo + ", numAverias=" + numAverias + "]";
	}

	void _setCliente(Cliente c) {
		this.cliente = c;
		// TODO Auto-generated method stub

	}

	public Cliente getCliente() {
		// TODO Auto-generated method stub
		return cliente;
	}

	void _setTipoVehiculo(TipoVehiculo tipoVehiculo) {
		// TODO Auto-generated method stub
		this.tipoVehiculo = tipoVehiculo;
	}

	public TipoVehiculo getTipo() {
		// TODO Auto-generated method stub
		return tipoVehiculo;
	}

	Set<Averia> _getAverias() {
		// TODO Auto-generated method stub
		return averias;
	}

	public Set<Averia> getAverias() {
		return new HashSet<>(averias);
	}

}
