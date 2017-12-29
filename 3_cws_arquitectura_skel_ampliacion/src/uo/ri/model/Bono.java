package uo.ri.model;

import javax.persistence.*;

import uo.ri.util.exception.BusinessException;
import uo.ri.model.util.Checker;

@Entity
@Table(name = "TBONOS")
public class Bono extends MedioPago {

	private double disponible = 0.0;
	private String descripcion;

	@Column(unique = true)
	private String codigo;

	Bono() {
	}

	public Bono(Cliente cliente, String codigo) throws BusinessException {
		this.codigo = Checker.checkString(codigo, "Codigo");
		Association.Pagar.link(cliente, this);
	}

	public Bono(String codigo, double disponible) {
		this.codigo = codigo;
		this.disponible = disponible;
		this.descripcion = new String();
	}

	public Bono(String code, String descripcion, double disponible) {
		this(code, disponible);
		this.descripcion = descripcion;
	}


	public double getDisponible() {
		return disponible;
	}

	public void setDisponible(double disponible) {
		this.disponible = disponible;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCodigo() {
		return new String(codigo);
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
		Bono other = (Bono) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Bono [disponible=" + disponible + ", descripcion=" + descripcion
				+ ", codigo=" + codigo + "]";
	}

	@Override
	public void pagar(double importe) throws BusinessException {
		if (disponible >= importe) {
			acumulado += importe;
			disponible -= importe;
		} else
			throw new BusinessException(
					"El importe es mayor que el valor disponible");
	}

}
