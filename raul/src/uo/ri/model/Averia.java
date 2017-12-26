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

import alb.util.assertion.Assert;
import uo.ri.model.types.AveriaStatus;
import uo.ri.model.types.FacturaStatus;
import uo.ri.util.exception.BusinessException;

@Entity
@Table(name = "TAverias", uniqueConstraints= {
		@UniqueConstraint(columnNames="FECHA, VEHICULO_ID")
})
public class Averia {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
	
	private String descripcion;
	@Temporal(TemporalType.TIMESTAMP) private Date fecha;
	private double importe = 0.0;
	@Enumerated(EnumType.STRING) private AveriaStatus status = AveriaStatus.ABIERTA;
	
	@ManyToOne private Vehiculo vehiculo;		
	@ManyToOne private Mecanico mecanico;	
	@ManyToOne private Factura factura;
	@OneToMany(mappedBy="averia") private Set<Intervencion> intervenciones = new HashSet<>();

	private boolean usada_bono;
	
	Averia(){
		
	}
	public Averia(Vehiculo vehiculo) {
		super();
		this.fecha = new Date();
		Association.Averiar.link(vehiculo, this);
	}
	public Averia(Vehiculo vehiculo, String descripcion) {
		this(vehiculo);
		this.descripcion = descripcion;
	}
	
	public long getId() {
		return id;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Date getFecha() {
		return (Date)fecha.clone();
	}
	public double getImporte() {
		return importe;
	}
	public void setImporte(double importe) {
		this.importe = importe;
	}
	public AveriaStatus getStatus() {
		return status;
	}	
	public void setStatus(AveriaStatus status) {
		this.status = status;
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
	
	
	@Override
	public String toString() {
		return "Averia [descripcion=" + descripcion + ", fecha=" + fecha + ", importe=" + importe + ", status=" + status
				+ ", vehiculo=" + vehiculo + "]";
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}
	
	void _setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}
	
	public Mecanico getMecanico() {
		return mecanico;
	}	
	void _setMecanico(Mecanico mecanico) {
		this.mecanico = mecanico;
	}
	
	Set<Intervencion> _getIntervenciones() {
		return intervenciones;
	}
	
	public Set<Intervencion> getIntervenciones() {
		return new HashSet<>(intervenciones);
	}
	
	public Factura getFactura() {
		return factura;
	}
	
	void _setFactura(Factura factura) {
		this.factura = factura;
	}

	/**
	 * Asigna la averia al mecanico
	 * @param mecanico
	 * @throws BusinessException 
	 */
	public void assignTo(Mecanico mecanico) throws BusinessException {
		// Solo se puede asignar una averia que está ABIERTA
		// linkado de averia y mecanico
		// la averia pasa a ASIGNADA
		if (status.equals(AveriaStatus.ABIERTA)) {
			Association.Asignar.link(mecanico, this);
			this.setStatus(AveriaStatus.ASIGNADA);
		}
		else {
			throw new BusinessException("La averia no esta abierta");
		}
	}


	/**
	 * El mecánico da por finalizada esta avería, entonces se calcula el 
	 * importe
	 * @throws BusinessException 
	 * 
	 */
	public void markAsFinished() throws BusinessException {
		// Se verifica que está en estado ASIGNADA
		// se calcula el importe
		// se desvincula mecanico y averia
		// el status cambia a TERMINADA
		if (status.equals(AveriaStatus.ASIGNADA)) {
			Association.Asignar.unlink(mecanico, this);
			calcularImporte();
			this.setStatus(AveriaStatus.TERMINADA);
		}
		else {
			throw new BusinessException("La averia no esta asignada");
		}
	}


	public double calcularImporte() {
		setImporte(0.0);
		for(Intervencion i: intervenciones) {
			importe += i.calcularImporte();
		}
		return importe;
		
	}
	/**
	 * Una averia en estado TERMINADA se puede asignar a otro mecánico
	 * (el primero no ha podido terminar la reparación), pero debe ser pasada 
	 * a ABIERTA primero
	 * @throws BusinessException 
	 */
	public void reopen() throws BusinessException {
		// Solo se puede reabrir una averia que está TERMINADA
		// la averia pasa a ABIERTA
		if (status.equals(AveriaStatus.TERMINADA)) {
			this.setStatus(AveriaStatus.ABIERTA);			
		}
		else {
			throw new BusinessException("La averia no esta terminada");
		}
	}

	/**
	 * Una avería ya facturada se elimina de la factura
	 * @throws BusinessException 
	 */
	public void markBackToFinished() throws BusinessException {
		// verificar que la averia está FACTURADA
		// cambiar status a TERMINADA
		if (status.equals(AveriaStatus.FACTURADA)) {
			this.setStatus(AveriaStatus.TERMINADA);			
		}
		else {
			throw new BusinessException("La averia no esta facturada");
		}
	}

	public void desassign() {
		Assert.isTrue(status.equals(AveriaStatus.ASIGNADA));
		Association.Asignar.unlink(mecanico, this);
		status = AveriaStatus.ABIERTA;		
	}
	public void markAsInvoiced() throws BusinessException {
		if (factura != null) {
			if(AveriaStatus.TERMINADA.equals(status)) {
				setStatus(AveriaStatus.FACTURADA);
			}
			else {
				throw new BusinessException("Averia no terminada");
			}			
		}
		else {
			throw new BusinessException("No tiene factura asignada");
		}
		
	}
	public boolean esElegibleParaBono3()  {
		if(getStatus().equals(AveriaStatus.FACTURADA)) {
			if(factura.getStatus().equals(FacturaStatus.ABONADA)) {
				if(!this.usada_bono) {
					return true;
				}
				else {
					return false;
				}
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	
	public void markAsBono3Used() {
		this.usada_bono = true;		
	}
	
	public boolean isUsadaBono() {
		return usada_bono;
	}

	
}
