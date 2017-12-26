package uo.ri.model;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import alb.util.date.DateUtil;
import uo.ri.model.types.AveriaStatus;
import uo.ri.model.types.FacturaStatus;
import uo.ri.util.exception.BusinessException;

@Entity
@Table(name="TFacturas")
public class Factura {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
	
	@Column(unique=true)private Long numero;
	@Temporal(TemporalType.TIMESTAMP)private Date fecha;
	private double importe;
	private double iva;
	@Enumerated(EnumType.STRING)private FacturaStatus status = FacturaStatus.SIN_ABONAR;	
	private boolean usada_bono;
	
	@OneToMany(mappedBy = "factura") private Set<Cargo> cargos = new HashSet<>();
	@OneToMany(mappedBy = "factura") private Set<Averia> averias = new HashSet<>();
		
	Factura(){
		
	}
	public Factura(Long numero) {
		super();
		this.numero = numero;
		this.iva = 0.21;
		this.fecha = new Date();
	}	

	public Factura(long numero, Date fecha) {
		this(numero);
		this.fecha = fecha;
	}
	
	public Factura(long numero, List<Averia> averias)throws BusinessException{
		this(numero);
		for (Averia a: averias) {
			if(!a.getStatus().equals(AveriaStatus.TERMINADA)) {
				throw new BusinessException("No se pueden facturar averias no terminadas");
			}
			this.addAveria(a);
		}
	}
	
	public Factura(long numero, Date fecha, List<Averia> averias) throws BusinessException {
		this(numero, averias);
		this.fecha = fecha;
		Date JULY_1_2012 = DateUtil.fromString("1/7/2012");
		if(fecha.before(JULY_1_2012)) {
			this.setIva(0.18);
			calcularImporte();
		}
	}

	public Long getNumero() {
		return numero;
	}
	
	public Date getFecha() {
		return (Date)fecha.clone();
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public double getImporte() {
		return importe;
	}
	public void setImporte(double importe) {
		this.importe = importe;
	}
	
	public double getIva() {
		return iva;
	}
	public void setIva(double iva) {
		this.iva = iva;
	}
	
	public FacturaStatus getStatus() {
		return status;
	}	
	public void setStatus(FacturaStatus status) {
		this.status = status;
	}

	Set<Averia> _getAverias() {
		return averias;
	}
	public Set<Averia> getAverias() {
		return new HashSet<>(averias);
	}
	
	Set<Cargo> _getCargos() {
		return cargos;
	}
	
	public Set<Cargo> getCargos() {
		return new HashSet<Cargo>(cargos);
	}

	/**
	 * Añade  la averia a la factura
	 * @param averia
	 * @throws BusinessException 
	 */
	public void addAveria(Averia averia) throws BusinessException {
		// Verificar que la factura está en estado SIN_ABONAR
		// Verificar que La averia está TERMINADA
		// linkar factura y averia
		// marcar la averia como FACTURADA ( averia.markAsInvoiced() )
		// calcular el importe
		if (getStatus().equals(FacturaStatus.SIN_ABONAR)) {
			if(averia.getStatus().equals(AveriaStatus.TERMINADA)) {
				Association.Facturar.link(averia, this);
				averia.setStatus(AveriaStatus.FACTURADA);
				calcularImporte();
			}
			else {
				throw new BusinessException("La averia no esta terminada");
			}
			
		}
		else {
			throw new BusinessException("La factura ya esta abonada");
		}
	}

	/**
	 * Calcula el importe de la avería y su IVA, teniendo en cuenta la fecha de 
	 * factura
	 */
	void calcularImporte() {
		setImporte(0);
		for (Averia a: averias) {
			importe += a.calcularImporte();
		}
		importe = importe * (1 + iva);
		importe = (double) Math.round(importe * 100d) / 100d;
	}

	/**
	 * Elimina una averia de la factura, solo si está SIN_ABONAR y recalcula 
	 * el importe
	 * @param averia
	 * @throws BusinessException 
	 */
	public void removeAveria(Averia averia) throws BusinessException {
		// verificar que la factura está sin abonar
		// desenlazar factura y averia
		// la averia vuelve al estado FINALIZADA ( averia.markBackToFinished() )
		// TODO Auto-generated method stub
		// volver a calcular el importe
		if(getStatus().equals(FacturaStatus.SIN_ABONAR)) {
			Association.Facturar.unlink(averia, this);
			averia.markBackToFinished();
			calcularImporte();
		}
		else {
			throw new BusinessException("La factura ya esta abonada.");
		}
	}
	public Long getId() {
		return id;
	}
	public void settle() throws BusinessException {
		for (Averia averia: averias) {
			if (!averia.getStatus().equals(AveriaStatus.FACTURADA)) {
				throw new BusinessException("Hay averias sin facturar");
			}
		}
		this.status = FacturaStatus.ABONADA;
	}
	
	public boolean isSettled() {
		return (this.status.equals(FacturaStatus.ABONADA));
	}
	
	public boolean puedeGenerarBono500(){
		if(!this.usada_bono) {
			if(getImporte() > 500) {
				if(getStatus().equals(FacturaStatus.ABONADA)) {
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
	
	public void markAsBono500Used() throws BusinessException {
		if(puedeGenerarBono500()) {
			this.usada_bono = true;
		}		
		else {
			throw new BusinessException("No genera bono 500");
		}
	}


}
