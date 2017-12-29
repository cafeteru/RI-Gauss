package uo.ri.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import alb.util.date.DateUtil;
import alb.util.random.Random;
import uo.ri.util.exception.BusinessException;
import uo.ri.model.types.AveriaStatus;
import uo.ri.model.types.FacturaStatus;
import uo.ri.model.util.Checker;

@Entity
@Table(name = "TFACTURAS")
public class Factura {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private Long numero;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	private double importe;
	private double iva;

	@Enumerated(EnumType.STRING)
	private FacturaStatus status = FacturaStatus.SIN_ABONAR;

	@OneToMany(mappedBy = "factura")
	private Set<Averia> averias = new HashSet<Averia>();

	@OneToMany(mappedBy = "factura")
	private Set<Cargo> cargos = new HashSet<Cargo>();

	@Column(name = "USADA_BONO")
	private boolean usadaBono;

	Factura() {
	}

	public Factura(Long numero) throws BusinessException {
		this.numero = Checker.positiveNumber(numero);
		this.fecha = DateUtil.today();
	}

	public Factura(long numero, Date today) throws BusinessException {
		this(numero);
		this.fecha = new Date(today.getTime());
	}

	public Factura(long numero, List<Averia> averias) throws BusinessException {
		this(numero);
		for (Averia a : averias) {
			addAveria(a);
		}
	}

	public Factura(long numero, Date fecha, List<Averia> averias)
			throws BusinessException {
		this(numero, fecha);
		for (Averia a : averias) {
			addAveria(a);
		}
	}

	public Long getId() {
		return id;
	}

	public Long getNumero() {
		return new Long(numero);
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public double getImporte() {
		calcularImporte();
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public double getIva() {
		calcularIva();
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

	public Set<Averia> getAverias() {
		return new HashSet<>(averias);
	}

	Set<Averia> _getAverias() {
		return this.averias;
	}

	public Set<Cargo> getCargos() {
		return new HashSet<>(cargos);
	}

	Set<Cargo> _getCargos() {
		return cargos;
	}

	/**
	 * Añade la averia a la factura
	 * 
	 * @param averia
	 * @throws BusinessException
	 */
	public void addAveria(Averia averia) throws BusinessException {
		// Verificar que la factura está en estado SIN_ABONAR
		// Verificar que La averia está TERMINADA
		// linkar factura y averia
		// marcar la averia como FACTURADA ( averia.markAsInvoiced() )
		// calcular el importe
		if (averia.getStatus().equals(AveriaStatus.TERMINADA)
				&& getStatus().equals(FacturaStatus.SIN_ABONAR)) {
			averia.setStatus(AveriaStatus.FACTURADA);
			Association.Facturar.link(this, averia);
			calcularImporte();
		} else {
			throw new BusinessException("No se puede añadir la avería a"
					+ " la factura, no está terminada");
		}
	}

	/**
	 * Calcula el importe de la avería y su IVA, teniendo en cuenta la fecha de
	 * factura
	 */
	private void calcularImporte() {
		// iva = ...
		// importe = ...
		double importe = 0;
		for (Averia a : averias) {
			importe += a.getImporte();
		}

		importe *= 1 + getIva() / 100;
		setImporte(Math.rint(importe * 100) / 100);
	}

	/**
	 * Elimina una averia de la factura, solo si está SIN_ABONAR y recalcula el
	 * importe
	 * 
	 * @param averia
	 * @throws BusinessException
	 */
	public void removeAveria(Averia averia) throws BusinessException {
		// verificar que la factura está sin abonar
		// desenlazar factura y averia
		// la averia vuelve al estado FINALIZADA ( averia.markBackToFinished() )
		// TODO Auto-generated method stub
		// volver a calcular el importe
		if (getStatus().equals(FacturaStatus.SIN_ABONAR)) {
			Association.Facturar.unlink(this, averia);
			averia.setStatus(AveriaStatus.TERMINADA);
			calcularImporte();
		} else {
			throw new BusinessException(
					"No se puede eliminar la avería porque ya esta abonada la "
							+ "factura");
		}
	}

	private void calcularIva() {
		if (this.fecha.after(DateUtil.fromString("1/7/2012")))
			this.iva = 21.0;
		else
			this.iva = 18.0;
	}

	public void settle() throws BusinessException {
		if (this.getAverias().size() > 0) {
			double sumaAverias = 0;
			for (Cargo c : getCargos()) {
				sumaAverias += c.getImporte();
			}

			if (Math.abs(importe - sumaAverias) <= 0.01) {
				this.setStatus(FacturaStatus.ABONADA);
			} else {
				throw new BusinessException("El importe es erroneo");
			}
		} else {
			throw new BusinessException("La factura no tiene averías");
		}
	}

	public boolean isSettled() {
		if (this.getStatus() == FacturaStatus.ABONADA)
			return true;
		return false;
	}

	public boolean puedeGenerarBono500() {
		if (!usadaBono && importe >= 500 && isSettled())
			return true;
		return false;
	}

	public void markAsBono500Used() throws BusinessException {
		if (puedeGenerarBono500()) {
			crearBono30();
		} else {
			throw new BusinessException("Donde vaaaas");
		}
	}

	private void crearBono30() throws BusinessException {
		Bono bono = new Bono(Random.string(8), "Por factura superior a 500€",
				30);
		Iterator<Cargo> it = cargos.iterator();
		Association.Pagar.link(it.next().getMedioPago().getCliente(), bono);
		usadaBono = true;
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
				+ importe + ", iva=" + iva + ", status=" + status + ", averias="
				+ averias + "]";
	}
}
