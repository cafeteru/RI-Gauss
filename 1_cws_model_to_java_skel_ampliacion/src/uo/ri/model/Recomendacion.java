package uo.ri.model;

import javax.persistence.*;

import uo.ri.model.exception.BusinessException;
import uo.ri.model.types.RecomendacionKey;

@Entity
@IdClass(RecomendacionKey.class)
@Table(name = "TRECOMENDACION")
public class Recomendacion {

	@Id
	@ManyToOne
	private Cliente recomendador;

	@OneToOne
	private Cliente recomendado;

	private boolean usadaBono;

	Recomendacion() {
	}

	public Recomendacion(Cliente recomendador, Cliente recomendado)
			throws BusinessException {
		Association.Recomendar.link(recomendador, this, recomendado);
		this.usadaBono = false;
	}

	public Cliente getRecomendador() {
		return recomendador;
	}

	void _setRecomendador(Cliente recomendador) {
		this.recomendador = recomendador;
	}

	public Cliente getRecomendado() {
		return recomendado;
	}

	void _setRecomendado(Cliente recomendado) {
		this.recomendado = recomendado;
	}

	public boolean isUsadaBono() {
		return usadaBono;
	}

	public void setUsadaBono(boolean usadaBono) {
		this.usadaBono = usadaBono;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((recomendado == null) ? 0 : recomendado.hashCode());
		result = prime * result
				+ ((recomendador == null) ? 0 : recomendador.hashCode());
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
		Recomendacion other = (Recomendacion) obj;
		if (recomendado == null) {
			if (other.recomendado != null)
				return false;
		} else if (!recomendado.equals(other.recomendado))
			return false;
		if (recomendador == null) {
			if (other.recomendador != null)
				return false;
		} else if (!recomendador.equals(other.recomendador))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Recomendacion [recomendador=" + recomendador + ", recomendado="
				+ recomendado + ", usadaBono=" + usadaBono + "]";
	}

}
