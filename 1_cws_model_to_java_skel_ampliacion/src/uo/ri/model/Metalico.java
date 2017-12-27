package uo.ri.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import uo.ri.model.exception.BusinessException;

@Entity
@Table(name = "TMETALICOS")
public class Metalico extends MedioPago {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	Metalico() {
	}

	public Metalico(Cliente cliente) throws BusinessException {
		Association.Pagar.link(this, cliente);
	}
}
