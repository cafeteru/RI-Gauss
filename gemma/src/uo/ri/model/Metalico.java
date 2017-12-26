package uo.ri.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TMETALICOS")
public class Metalico extends MedioPago {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	Metalico() {
	}

	public Metalico(Cliente cliente) {
		Association.Pagar.link(cliente, this);
	}

	public Long getId() {
		return id;
	}

	public void pagar(int importe) {
		this.acumulado += importe;
	}

}
