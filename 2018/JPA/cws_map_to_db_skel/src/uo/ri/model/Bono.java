package uo.ri.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "TBONOS")
public class Bono extends MedioPago {

	@Column(unique = true)
	private String codigo;
	
	private double disponible = 0.0;
	private String descripcion;

	Bono() {
	}

	public Bono(String codigo, double disponible, String descripcion) {
		super();
		this.codigo = codigo;
		this.disponible = disponible;
		this.descripcion = descripcion;
	}

	public Bono(String codigo, double disponible) {
		this.codigo = codigo;
		this.disponible = disponible;
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

	@Override
	public void setAcumulado(double acumulado) {
		super.setAcumulado(acumulado);
		disponible -= acumulado;
	}

	@Override
	public void pagar(double importe) {
		super.pagar(importe);
		disponible -= importe;
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
		return "Bono [codigo=" + codigo + ", disponible=" + disponible
				+ ", descripcion=" + descripcion + "]";
	}

	@Override
	public void isValid(double importe) {
		if (getDisponible() < importe) {
			throw new IllegalStateException("No hay dinero suficiente el bono");
		}
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
