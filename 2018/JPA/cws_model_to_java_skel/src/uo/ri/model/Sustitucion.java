package uo.ri.model;

public class Sustitucion {

	private Repuesto repuesto;
	private Intervencion intervencion;
	private int cantidad;

	public Sustitucion(Repuesto repuesto, Intervencion intervencion) {
		Association.Sustituir.link(intervencion, this, repuesto);
	}

	public Sustitucion(Repuesto repuesto, Intervencion intervencion,
			int cantidad) {
		this(repuesto, intervencion);
		if (cantidad <= 0) {
			throw new IllegalArgumentException(
					"No puede haber una intervención sin repuestos");
		}
		this.cantidad = cantidad;
	}

	public Repuesto getRepuesto() {
		return repuesto;
	}

	public Intervencion getIntervencion() {
		return intervencion;
	}

	public int getCantidad() {
		return cantidad;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((intervencion == null) ? 0 : intervencion.hashCode());
		result = prime * result
				+ ((repuesto == null) ? 0 : repuesto.hashCode());
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
		Sustitucion other = (Sustitucion) obj;
		if (intervencion == null) {
			if (other.intervencion != null)
				return false;
		} else if (!intervencion.equals(other.intervencion))
			return false;
		if (repuesto == null) {
			if (other.repuesto != null)
				return false;
		} else if (!repuesto.equals(other.repuesto))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Sustitucion [cantidad=" + cantidad + "]";
	}

	public void _setIntervencion(Intervencion i) {
		this.intervencion = i;
	}

	public void _setRepuesto(Repuesto r) {
		this.repuesto = r;
	}

	public Double getImporte() {
		if (this.cantidad != 0)
			return cantidad * repuesto.getPrecio();
		return 0.0;
	}

}
