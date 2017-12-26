package uo.ri.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import uo.ri.util.exception.BusinessException;

@Entity
@Table(name = "TBONOS")
public class Bono extends MedioPago {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	protected double disponible = 0.0;
	protected String descripcion;
	@Column(unique = true)
	protected String codigo;

	Bono() {
	}

	public Bono(String codigo) {
		super();
		this.codigo = codigo;
	}

	public Bono(String codigo, double disponible) {
		this.codigo = codigo;
		this.disponible = disponible;
		this.descripcion = "";
	}

	public Bono(String codigo, String descripcion, double disponible) {
		this(codigo, disponible);
		this.descripcion = descripcion;
	}

	public Long getId() {
		return id;
	}

	public String getCodigo() {
		return codigo;
	}

	public double getDisponible() {
		return disponible;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDisponible(double disponible) {
		this.disponible = disponible;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	public void pagar(double importe) throws BusinessException {
		if (importe <= disponible) {
			this.disponible -= importe;
			this.acumulado += importe;
		} else
			throw new BusinessException(
					"El importe es mayor que el valor disponible");
	}

}
