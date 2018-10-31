package uo.ri.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import uo.ri.model.types.AveriaStatus;

@Entity
@Table(name = "TAVERIAS", uniqueConstraints = {
		@UniqueConstraint(columnNames = "VEHICULO_ID, FECHA") })
public class Averia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String descripcion;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	private double importe = 0.0;

	@Enumerated(EnumType.STRING)
	private AveriaStatus status = AveriaStatus.ABIERTA;

	@ManyToOne
	private Vehiculo vehiculo;

	@ManyToOne
	private Factura factura;
	
	@ManyToOne
	private Mecanico mecanico;

	@OneToMany(mappedBy = "averia")
	private Set<Intervencion> intervenciones = new HashSet<>();

	Averia() {
	}

	public Averia(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
		this.fecha = new Date();
		Association.Averiar.link(vehiculo, this);
		vehiculo.setNumAverias(vehiculo.getNumAverias() + 1);
	}

	public Averia(Vehiculo vehiculo, String descripcion) {
		this(vehiculo);
		this.descripcion = descripcion;
	}

	public Long getId() {
		return id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Date getFecha() {
		return new Date(fecha.getTime());
	}

	Date _getFecha() {
		return fecha;
	}

	public double getImporte() {
		return importe;
	}

	public AveriaStatus getStatus() {
		return status;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result
				+ ((vehiculo == null) ? 0 : vehiculo.hashCode());
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
		Averia other = (Averia) obj;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (vehiculo == null) {
			if (other.vehiculo != null)
				return false;
		} else if (!vehiculo.equals(other.vehiculo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Averia [descripcion=" + descripcion + ", fecha=" + fecha
				+ ", importe=" + importe + ", status=" + status + "]";
	}

	/**
	 * Asigna la averia al mecanico y esta queda marcada como ASIGNADA
	 * 
	 * @see Diagramas de estados en el enunciado de referencia
	 * @param mecanico
	 * @throws IllegalStateException si - La avería no está en estado ABIERTA, o
	 *                               - La avería ya está enlazada con otro
	 *                               mecánico
	 */
	public void assignTo(Mecanico mecanico) {
		// Solo se puede asignar una averia que está ABIERTA
		// linkado de averia y mecanico
		// la averia pasa a ASIGNADA
		if (status.equals(AveriaStatus.ABIERTA) && this.mecanico == null) {
			Association.Asignar.link(mecanico, this);
			status = AveriaStatus.ASIGNADA;
		} else {
			throw new IllegalStateException("La avería no está en estado "
					+ "ABIERTA, o La avería ya está enlazada con otro "
					+ "mecánico");
		}
	}

	private void calcularImporte() {
		double precio = 0;
		for (Intervencion intervencion : intervenciones)
			precio += intervencion.getImporte();
		setImporte(precio);
	}

	/**
	 * El mecánico da por finalizada esta avería, entonces se calcula el importe
	 * y pasa a TERMINADA
	 * 
	 * @see Diagramas de estados en el enunciado de referencia
	 * @throws IllegalStateException si - La avería no está en estado ASIGNADA,
	 *                               o - La avería no está enlazada con un
	 *                               mecánico
	 */
	public void markAsFinished() {
		if (getStatus().equals(AveriaStatus.ASIGNADA)
				&& getMecanico() != null) {
			Association.Asignar.unlink(mecanico, this);
			setStatus(AveriaStatus.TERMINADA);
			calcularImporte();
		} else
			throw new IllegalStateException(
					"La avería no está en estado ASIGNADA, o la avería no está"
							+ " enlazada con un mecánico");
	}

	/**
	 * Una averia en estado TERMINADA se puede asignar a otro mecánico (p.e., el
	 * primero no ha hecho bien la reparación), pero debe ser pasada a ABIERTA
	 * primero
	 * 
	 * @see Diagramas de estados en el enunciado de referencia
	 * @throws IllegalStateException si - La avería no está en estado TERMINADA
	 */
	public void reopen() {
		// Se verifica que está en estado TERMINADA
		// Se pasa la averia a ABIERTA
		if (status.equals(AveriaStatus.TERMINADA)) {
			setStatus(AveriaStatus.ABIERTA);
		} else
			throw new IllegalStateException(
					"La avería no está en estado TERMINADA");
	}

	/**
	 * Este método se llama desde la factura al ejecutar factura.removeAveria()
	 * Retrocede la averia a TERMINADA
	 * 
	 * @see Diagramas de estados en el enunciado de referencia
	 * @throws IllegalStateException si - La averia no está en estado FACTURADA,
	 *                               o - La avería aún está enlazada con la
	 *                               factura
	 */
	public void markBackToFinished() {
		if (getStatus().equals(AveriaStatus.FACTURADA)
				&& getFactura() != null) {
			Association.Facturar.unlink(factura, this);
			setStatus(AveriaStatus.TERMINADA);
		} else
			throw new IllegalStateException(
					"La averia no está en estado FACTURADA,"
							+ "o - La avería aún está enlazada con la"
							+ " factura");
	}

	/**
	 * Edte método se llama desde la factura al ejecutar factura.addAveria()
	 * Marca la averia como FACTURADA
	 * 
	 * @see Diagramas de estados en el enunciado de referencia
	 * @throws IllegalStateException si - La averia no está en estado TERMINADA,
	 *                               o - La avería no está enlazada con una
	 *                               factura
	 */
	public void markAsInvoiced() {
		if (status.equals(AveriaStatus.TERMINADA) && factura != null) {
			setStatus(AveriaStatus.FACTURADA);
		} else
			throw new IllegalStateException(
					"La averia no está en estado TERMINADA, o La avería no "
							+ "está enlazada con una factura");
	}

	/**
	 * Desvincula la avería en estado ASIGNADA del mecánico y la pasa a ABIERTA
	 * 
	 * @see Diagramas de estados en el enunciado de referencia
	 * @throws IllegalStateException si - La averia no está en estado ASIGNADA,
	 *                               o
	 */
	public void desassign() {
		if (status.equals(AveriaStatus.ASIGNADA) && mecanico != null) {
			setStatus(AveriaStatus.ABIERTA);
		} else
			throw new IllegalStateException(
					"La averia no está en estado ASIGNADA, o La avería no "
							+ "está enlazada con un mecanico");
	}

	public void _setVehiculo(Vehiculo v) {
		this.vehiculo = v;

	}

	public Factura getFactura() {
		return factura;
	}

	public void _setMecanico(Mecanico mecanico) {
		this.mecanico = mecanico;

	}

	public Set<Intervencion> getIntervenciones() {
		return new HashSet<>(intervenciones);
	}

	Set<Intervencion> _getIntervenciones() {
		return intervenciones;
	}

	public Mecanico getMecanico() {
		return mecanico;
	}

	void _setFactura(Factura factura) {
		this.factura = factura;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public void setStatus(AveriaStatus status) {
		this.status = status;
	}

}
