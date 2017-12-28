package uo.ri.model;

import java.util.Date;

import javax.persistence.*;

import alb.util.date.DateUtil;
import uo.ri.model.exception.BusinessException;
import uo.ri.model.util.Checker;

@Entity
@Table(name = "TTARJETASCREDITO")
public class TarjetaCredito extends MedioPago {

	@Column(unique = true)
	private String numero;
	private String tipo;

	@Temporal(TemporalType.DATE)
	private Date validez;

	TarjetaCredito() {
	}

	public TarjetaCredito(Cliente cliente, String numero)
			throws BusinessException {
		this(numero);
		Association.Pagar.link(cliente, this);
	}

	public TarjetaCredito(String numero) throws BusinessException {
		this.numero = Checker.checkString(numero, "Número");
	}

	public String getNumero() {
		return numero;
	}

	public String getTipo() {
		return tipo;
	}

	public Date getValidez() {
		return validez;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setValidez(Date validez) {
		this.validez = validez;
	}

	@Override
	public void pagar(double importe) throws BusinessException {
		if (isValidNow())
			this.acumulado += importe;
		else
			throw new BusinessException("La tarjeta de credito esta caducada");
	}

	public boolean isValidNow() {
		return validez.after(DateUtil.today());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
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
		TarjetaCredito other = (TarjetaCredito) obj;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TarjetaCredito [numero=" + numero + ", tipo=" + tipo
				+ ", validez=" + validez + "]";
	}

}
