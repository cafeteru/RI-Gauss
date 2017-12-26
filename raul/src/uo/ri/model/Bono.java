package uo.ri.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import uo.ri.util.exception.BusinessException;

@Entity
@Table(name="TBonos")
public class Bono extends MedioPago {

	protected double disponible = 0.0;
	protected String descripcion;
	@Column(unique=true)protected String codigo;
	
	Bono(){
		
	}
	public Bono(String codigo) {
		super();
		this.codigo = codigo;
	}
	public Bono(String codigo, double disponible) {
		this(codigo);
		this.disponible = disponible;
		this.descripcion = "";
	}
		
	public Bono(String code, String descripcion, double disponible) {
		this(code, disponible);
		this.descripcion = descripcion;
	}
	public double getDisponible() {
		return disponible;
	}

	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getCodigo() {
		return codigo;
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
		return "Bono [disponible=" + disponible + ", descripcion=" + descripcion + ", codigo=" + codigo + ", acumulado="
				+ acumulado + "]";
	}
	
	public void pagar(double importe)throws BusinessException {
		if(importe < this.disponible) {
			this.disponible -= importe;
		}
		else {
			throw new BusinessException("El saldo disponible del bono no es suficiente para ese importe");
		}
		
	}
		
	
}
