package uo.ri.util;

import uo.ri.model.Mecanico;

public class MechanicBuilder {

	private Mecanico mecanico;

	public MechanicBuilder() {
		this.mecanico = new Mecanico();
	}

	public MechanicBuilder withDni(String dni) {
		mecanico.setDni(dni);
		return this;
	}

	public MechanicBuilder withNombre(String nombre) {
		mecanico.setNombre(nombre);
		return this;
	}

	public MechanicBuilder withApellidos(String apellidos) {
		mecanico.setApellidos(apellidos);
		return this;
	}

	public Mecanico build() {
		return mecanico;
	}

}
