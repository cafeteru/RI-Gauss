package uo.ri.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import uo.ri.model.exception.BusinessException;

@Entity
@DiscriminatorValue("TMETALICO")
@Table(name = "TMEDIOSPAGO")
public class Metalico extends MedioPago {
	Metalico() {
	}

	public Metalico(Cliente cliente) throws BusinessException {
		Association.Pagar.link(this, cliente);
	}
}
