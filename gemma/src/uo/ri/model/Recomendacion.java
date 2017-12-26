package uo.ri.model;

public class Recomendacion {
	private boolean usada_bono;
	private Cliente recomendador;
	private Cliente recomendado;

	public Recomendacion(Cliente recomendador, Cliente recomendado) {
		this.recomendador = recomendador;
		this.recomendado = recomendado;
		Association.Recomendar.link(recomendador, this, recomendado);
	}

	public Cliente getRecomendador() {
		return recomendador;
	}

	public Cliente getRecomendado() {
		return recomendado;
	}

	void _setRecomendador(Cliente recomendador) {
		this.recomendador = recomendador;
	}

	void _setRecomendado(Cliente recomendado) {
		this.recomendado = recomendado;
	}

	public boolean isUsada_bono() {
		return usada_bono;
	}

	public void setUsada_bono(boolean usada_bono) {
		this.usada_bono = usada_bono;
	}

	@Override
	public String toString() {
		return "Recomendacion [usada_bono=" + usada_bono + "]";
	}

	public void unlink() {
		Association.Recomendar.unlink(this);
	}

	public void markAsUsadaBono() {
		for (Recomendacion r : recomendador.getRecomendacionesHechas())
			r.usada_bono = true;
	}

}
