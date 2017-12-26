package uo.ri.model.types;

import java.io.Serializable;

public class RecomendacionKey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Long recomendador;
	Long recomendado;

	RecomendacionKey() {
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
		CargoKey other = (CargoKey) obj;
		if (recomendado == null) {
			if (other.factura != null)
				return false;
		} else if (!recomendado.equals(other.factura))
			return false;
		if (recomendador == null) {
			if (other.medioPago != null)
				return false;
		} else if (!recomendador.equals(other.medioPago))
			return false;
		return true;
	}

}
