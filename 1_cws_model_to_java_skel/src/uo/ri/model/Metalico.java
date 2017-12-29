package uo.ri.model;

import uo.ri.model.exception.BusinessException;

public class Metalico extends MedioPago {

	Metalico() {
	}

	public Metalico(Cliente cliente) throws BusinessException {
		Association.Pagar.link(this, cliente);
	}
}
