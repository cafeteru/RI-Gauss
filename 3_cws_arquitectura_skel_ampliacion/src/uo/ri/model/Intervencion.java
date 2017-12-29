package uo.ri.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import alb.util.math.Round;
import uo.ri.util.exception.BusinessException;

/**
 * Clase que simula una intervención
 * 
 * @author Iván González Mahagamage
 *
 */
@Entity
@Table(name = "TINTERVENCIONES", uniqueConstraints = {
		@UniqueConstraint(columnNames = "AVERIA_ID, MECANICO_ID") })
public class Intervencion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Averia averia;

	@ManyToOne
	private Mecanico mecanico;

	private int minutos;

	@OneToMany(mappedBy = "intervencion")
	private Set<Sustitucion> sustituciones = new HashSet<Sustitucion>();

	Intervencion() {
	}

	public Intervencion(Mecanico mecanico, Averia averia)
			throws BusinessException {
		Association.Intervenir.link(averia, this, mecanico);
	}

	public Averia getAveria() {
		return averia;
	}

	void _setAveria(Averia averia) {
		this.averia = averia;
	}

	public Mecanico getMecanico() {
		return mecanico;
	}

	void _setMecanico(Mecanico mecanico) {
		this.mecanico = mecanico;
	}

	public int getMinutos() {
		return minutos;
	}

	public void setMinutos(int minutos) {
		this.minutos = minutos;
	}

	public Set<Sustitucion> getSustituciones() {
		return new HashSet<Sustitucion>(sustituciones);
	}

	Set<Sustitucion> _getSustituciones() {
		return sustituciones;
	}

	public Double getImporte() {
		Double manoDeObra = averia.getVehiculo().getTipo().getPrecioHora()
				* minutos / 60;
		Double piezas = 0.0;
		for (Sustitucion s : sustituciones)
			piezas += s.getImporte();
		return Round.twoCents(manoDeObra + piezas);
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
		return "Intervencion [averia=" + averia + ", mecanico=" + mecanico
				+ ", minutos=" + minutos + "]";
	}

}
