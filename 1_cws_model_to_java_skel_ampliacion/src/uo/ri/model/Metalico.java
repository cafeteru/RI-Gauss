package uo.ri.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("TMETALICO")
@Table(name = "TMEDIOSPAGO")
public class Metalico extends MedioPago {
	Metalico() {
	}

	public Metalico(Cliente cliente) {
		Association.Pagar.link(this, cliente);
	}
}
