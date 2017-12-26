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
import uo.ri.util.exception.BusinessException;
import uo.ri.util.exception.Check;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "FECHA, VEHICULO_ID") }, name = "TAVERIAS")
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
	private Mecanico mecanico;
	@OneToMany(mappedBy = "averia")
	private Set<Intervencion> intervenciones = new HashSet<Intervencion>();
	@ManyToOne
	private Factura factura;

	// clave primaria vehiculo y fecha

	Averia() {
	}

	public Averia(Vehiculo vehiculo) {
		super();
		this.fecha = new Date();
		this.vehiculo = vehiculo;
		Association.Averiar.link(vehiculo, this);
	}

	public Averia(Vehiculo vehiculo, String descripcion) {
		this(vehiculo);
		this.descripcion = descripcion;
	}

	public Long getId() {
		return id;
	}

	public Set<Intervencion> getIntervenciones() {
		return new HashSet<Intervencion>(intervenciones);
	}

	Set<Intervencion> _getIntervenciones() {
		return intervenciones;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	void _setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public Factura getFactura() {
		return factura;
	}

	void _setFactura(Factura factura) {
		this.factura = factura;
	}

	public Mecanico getMecanico() {
		return mecanico;
	}

	void _setMecanico(Mecanico mecanico) {
		this.mecanico = mecanico;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Date getFecha() {
		return fecha;
	}

	public double getImporte() {
		return importe;
	}

	public AveriaStatus getStatus() {
		return status;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public void setStatus(AveriaStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Averia [descripcion=" + descripcion + ", fecha=" + fecha + ", importe=" + importe + ", status=" + status
				+ ", vehiculo=" + vehiculo + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + ((vehiculo == null) ? 0 : vehiculo.hashCode());
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

	/**
	 * Asigna la averia al mecanico
	 * 
	 * @param mecanico
	 * @throws BusinessException
	 */
	public void assignTo(Mecanico mecanico) throws BusinessException {
		// Solo se puede asignar una averia que está ABIERTA
		// linkado de averia y mecanico
		// la averia pasa a ASIGNADA
		Check.isTrue(getStatus() == AveriaStatus.ABIERTA);
		Association.Asignar.link(mecanico, this);
		this.setStatus(AveriaStatus.ASIGNADA);
	}

	/**
	 * El mecánico da por finalizada esta avería, entonces se calcula el importe
	 * 
	 * @throws BusinessException
	 * 
	 */
	public void markAsFinished() throws BusinessException {
		// Se verifica que está en estado ASIGNADA
		// se calcula el importe
		// se desvincula mecanico y averia
		// el status cambia a TERMINADA
		Check.isTrue(this.getStatus() == AveriaStatus.ASIGNADA);
		double importe = 0.0;
		for (Intervencion intervencion : intervenciones) {
			importe += intervencion.getImporte();
		}
		this.setImporte(importe);
		Association.Asignar.unlink(mecanico, this);
		this.setStatus(AveriaStatus.TERMINADA);
	}

	/**
	 * Una averia en estado TERMINADA se puede asignar a otro mecánico (el primero
	 * no ha podido terminar la reparación), pero debe ser pasada a ABIERTA primero
	 * 
	 * @throws BusinessException
	 */
	public void reopen() throws BusinessException {
		// Solo se puede reabrir una averia que está TERMINADA
		// la averia pasa a ABIERTA
		Check.isTrue(this.getStatus() == AveriaStatus.TERMINADA);
		// la averia pasa a ABIERTA
		this.setStatus(AveriaStatus.ABIERTA);
	}

	/**
	 * Una avería ya facturada se elimina de la factura
	 * 
	 * @throws BusinessException
	 */
	public void markBackToFinished() throws BusinessException {
		// verificar que la averia está FACTURADA
		// cambiar status a TERMINADA
		Check.isTrue(this.getStatus() == AveriaStatus.FACTURADA);
		this.setStatus(AveriaStatus.TERMINADA);
	}

	public void markAsInvoiced() throws BusinessException {
		Check.isTrue(this.getStatus() == AveriaStatus.TERMINADA);
		Check.isNotNull(factura, "No está asignada a ninguna factura");
		this.setStatus(AveriaStatus.FACTURADA);
	}

	public void desassign() {
		Association.Asignar.unlink(mecanico, this);
		this.setStatus(AveriaStatus.ABIERTA);
	}

}
