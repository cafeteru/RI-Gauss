package uo.ri.model;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import alb.util.date.Dates;
import alb.util.math.Round;
import uo.ri.model.types.AveriaStatus;
import uo.ri.model.types.FacturaStatus;

public class Factura {

	private Long numero;
	private Date fecha;
	private double importe;
	private double iva;
	private FacturaStatus status = FacturaStatus.SIN_ABONAR;

	private Set<Averia> averias = new HashSet<>();
	protected Set<Cargo> cargos = new HashSet<>();

	public Factura(Long numero) {
		super();
		this.numero = numero;
		setFecha(Dates.now());
	}

	public Factura(Long numero, Date fecha) {
		this(numero);
		setFecha(fecha);
	}

	public Factura(Long numero, List<Averia> averias) {
		this(numero);
		añadirAverias(averias);
	}

	public Factura(Long numero, Date fecha, List<Averia> averias) {
		this(numero, fecha);
		añadirAverias(averias);
	}

	private void añadirAverias(List<Averia> averias) {
		for (Averia averia : averias) {
			if (averia.getStatus().equals(AveriaStatus.TERMINADA)) {
				addAveria(averia);
			} else {
				throw new IllegalStateException("averia no terminada");
			}
		}
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public Date getFecha() {
		return new Date(fecha.getTime());
	}

	Date _getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = new Date(fecha.getTime());
		calcularIva();
	}

	public double getImporte() {
		return importe;
	}

	public void setIva(double iva) {
		this.iva = iva;
	}

	public double getIva() {
		return iva;
	}

	public FacturaStatus getStatus() {
		return status;
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
		Factura other = (Factura) obj;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Factura [numero=" + numero + ", fecha=" + fecha + ", importe="
				+ importe + ", iva=" + iva + ", status=" + status + "]";
	}

	/**
	 * Añade la averia a la factura y actualiza el importe e iva de la factura
	 * 
	 * @param averia
	 * @see Diagramas de estados en el enunciado de referencia
	 * @throws IllegalStateException si la factura no está en estado SIN_ABONAR
	 */
	public void addAveria(Averia averia) {
		// Verificar que la factura está en estado SIN_ABONAR
		// Verificar que La averia está TERMINADA
		// linkar factura y averia
		// marcar la averia como FACTURADA ( averia.markAsInvoiced() )
		// calcular el importe

		if (getStatus().equals(FacturaStatus.SIN_ABONAR)) {
			if (averia.getStatus().equals(AveriaStatus.TERMINADA)) {
				Association.Facturar.link(this, averia);
				calcularImporte();
				averia.markAsInvoiced();
			}
		} else {
			throw new IllegalStateException(
					"La factura tiene el estado SIN_ABONAR");
		}
	}

	/**
	 * Calcula el importe de la avería y su IVA, teniendo en cuenta la fecha de
	 * factura
	 */
	void calcularImporte() {
		// iva = ...
		// importe = ...
		double total = 0.0;
		for (Averia a : averias) {
			total += a.getImporte();
		}
		total *= 1 + getIva() / 100;
		setImporte(Round.twoCents(total));
	}

	void calcularIva() {
		if (this.fecha.after(Dates.fromString("1/7/2012")))
			setIva(21.0);
		else
			setIva(18.0);
	}

	/**
	 * Elimina una averia de la factura, solo si está SIN_ABONAR y recalcula el
	 * importe
	 * 
	 * @param averia
	 * @see Diagramas de estados en el enunciado de referencia
	 * @throws IllegalStateException si la factura no está en estado SIN_ABONAR
	 */
	public void removeAveria(Averia averia) {
		// verificar que la factura está sin abonar
		// desenlazar factura y averia
		// retornar la averia al estado FINALIZADA ( averia.markBackToFinished()
		// )
		// volver a calcular el importe
		if (getStatus().equals(FacturaStatus.SIN_ABONAR)) {
			averia.markBackToFinished();
			Association.Facturar.unlink(this, averia);
			calcularImporte();
		} else {
			throw new IllegalStateException(
					"No se puede eliminar la avería porque ya esta abonada la "
							+ "factura");
		}
	}

	/**
	 * Marks the invoice as ABONADA, but
	 * 
	 * Comprueba que concuerdan los cargos de una factura con su importe.
	 * 
	 * @see Diagramas de estados en el enunciado de referencia
	 * @throws IllegalStateException if - Is already settled, or - the amounts
	 *                               paid with charges to payment means does not
	 *                               cover the total of the invoice
	 */
	public void settle() {
		if (getAverias().size() > 0) {
			double sumaAverias = 0;
			for (Cargo c : getCargos()) {
				sumaAverias += c.getImporte();
			}
			if (Math.abs(importe - sumaAverias) <= 0.01) {
				setStatus(FacturaStatus.ABONADA);
			} else {
				throw new IllegalStateException(
						"Los cargos no igualan el importe");
			}
		} else {
			throw new IllegalStateException("La factura no tiene averías");
		}
	}

	public Set<Averia> getAverias() {
		return new HashSet<>(averias);
	}

	public Set<Cargo> getCargos() {
		return new HashSet<>(cargos);
	}

	Set<Cargo> _getCargos() {
		return cargos;
	}

	Set<Averia> _getAverias() {
		return averias;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public void setStatus(FacturaStatus status) {
		this.status = status;
	}

}
