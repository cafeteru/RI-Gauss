package uo.ri.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import alb.util.date.DateUtil;
import uo.ri.util.exception.BusinessException;

@Entity
@Table(name = "TTARJETASCREDITO")
public class TarjetaCredito extends MedioPago {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	protected String numero;
	protected String tipo;
	@Temporal(TemporalType.TIMESTAMP)
	protected Date validez;

	TarjetaCredito() {
	}

	public TarjetaCredito(String numero) {
		super();
		this.numero = numero;
		this.validez = new Date();
		this.tipo = "UNKNOWN";
	}

	public TarjetaCredito(String numero, String tipo, Date validez) {
		this(numero);
		this.tipo = tipo;
		this.validez = validez;
	}

	public Long getId() {
		return id;
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

	public void pagar(int importe) throws BusinessException {
		if (isValidNow())
			this.acumulado += importe;
		else
			throw new BusinessException("Tarjeta caducada");
	}

	public boolean isValidNow() {
		return validez.after(DateUtil.today());
	}

}
