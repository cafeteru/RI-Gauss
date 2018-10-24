package uo.ri.model;

import java.util.HashSet;
import java.util.Set;

public class Repuesto {

	private String codigo;
	private String descripcion;
	private double precio;

	private Set<Sustitucion> sustituciones = new HashSet<>();

	public Repuesto(String codigo) {
		super();
		this.codigo = codigo;
	}

	public Repuesto(String codigo, String descripcion, double precio) {
		this(codigo);
		this.descripcion = descripcion;
		this.precio = precio;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public double getPrecio() {
		return precio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		Repuesto other = (Repuesto) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Repuesto [codigo=" + codigo + ", descripcion=" + descripcion + ", precio=" + precio + "]";
	}

	Set<Sustitucion> _getSustituciones() {
		return sustituciones;
	}

	public Set<Sustitucion> getSustituciones() {
		return new HashSet<Sustitucion>(sustituciones);
	}
}
