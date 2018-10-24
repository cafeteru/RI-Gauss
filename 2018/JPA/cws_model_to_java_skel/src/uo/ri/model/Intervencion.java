package uo.ri.model;

import java.util.HashSet;
import java.util.Set;

import alb.util.math.Round;

public class Intervencion {

	private Averia averia;
	private Mecanico mecanico;
	private int minutos;

	private Set<Sustitucion> sustituciones = new HashSet<>();

	public Averia getAveria() {
		return averia;
	}

	public Mecanico getMecanico() {
		return mecanico;
	}

	public int getMinutos() {
		return minutos;
	}

	public void setMinutos(int i) {
		this.minutos = i;
	}

	public Intervencion(Mecanico mecanico, Averia averia) {
		this.mecanico = mecanico;
		this.averia = averia;
		Association.Intervenir.link(averia, this, mecanico);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((averia == null) ? 0 : averia.hashCode());
		result = prime * result
				+ ((mecanico == null) ? 0 : mecanico.hashCode());
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
		Intervencion other = (Intervencion) obj;
		if (averia == null) {
			if (other.averia != null)
				return false;
		} else if (!averia.equals(other.averia))
			return false;
		if (mecanico == null) {
			if (other.mecanico != null)
				return false;
		} else if (!mecanico.equals(other.mecanico))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Intervencion [minutos=" + minutos + "]";
	}

	void _setAveria(Averia averia) {
		this.averia = averia;
	}

	void _setMecanico(Mecanico mecanico) {
		this.mecanico = mecanico;
	}

	Set<Sustitucion> _getSustituciones() {
		return sustituciones;
	}

	public Set<Sustitucion> getSustituciones() {
		return new HashSet<Sustitucion>(sustituciones);
	}

	public Double getImporte() {
		Double manoDeObra = averia.getVehiculo().getTipo().getPrecioHora()
				* minutos / 60;
		Double piezas = 0.0;
		for (Sustitucion s : sustituciones)
			piezas += s.getImporte();
		return Round.twoCents(manoDeObra + piezas);
	}

}
