package uo.ri.util;

import uo.ri.model.Mecanico;

public class MechanicBuilder {

	private Mecanico mecanico;

	public MechanicBuilder() {
		this.mecanico = new Mecanico();
	}

	public Mecanico build() {
		return mecanico;
	}

}
