package uo.ri.model;

import java.util.Date;

import javax.persistence.*;

@Entity
@DiscriminatorValue("TTARJETASCREDITO")
@Table(name = "TMEDIOSPAGO")
public class TarjetaCredito extends MedioPago {

	private String numero;
	private String tipo;

	@Temporal(TemporalType.DATE)
	private Date validez;

	public TarjetaCredito(Cliente cliente, String numero) {
		Association.Pagar.link(this, cliente);
		this.numero = numero;
	}

	public TarjetaCredito(String string) {
		this.numero = string;
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

}
