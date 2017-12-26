package uo.ri.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import uo.ri.model.types.FacturaStatus;
import uo.ri.util.exception.BusinessException;

@Entity
@Table(name = "TCargos", uniqueConstraints= {
		@UniqueConstraint(columnNames="FACTURA_ID, MEDIOPAGO_ID")
})
public class Cargo {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
	
	@ManyToOne private Factura factura;
	@ManyToOne private MedioPago medioPago;
	private double importe = 0.0;
	
	Cargo(){
		
	}
	
	public Cargo(Factura factura, MedioPago medioPago, double importe) throws BusinessException {
		// incrementar el importe en el acumulado del medio de pago
		// guardar el importe
		// enlazar (link) factura, este cargo y medioDePago
		medioPago.acumulado += importe;
		if(medioPago instanceof Bono) {
			((Bono) medioPago).pagar(importe);
		}
		this.importe = importe;
		Association.Cargar.link(factura, this, medioPago);
	}	
	
	public Factura getFactura() {
		return factura;
	}
	void _setFactura(Factura factura) {
		this.factura = factura;
	}
	
	public MedioPago getMedioPago() {
		return medioPago;
	}
	void _setMedioPago(MedioPago medioPago) {
		this.medioPago = medioPago;
	}
	public void setImporte(double importe) {
		this.importe = importe;
	}
	
	/**
	 * Anula (retrocede) este cargo de la factura y el medio de pago
	 * Solo se puede hacer si la factura no está abonada
	 * Decrementar el acumulado del medio de pago
	 * Desenlazar el cargo de la factura y el medio de pago 
	 * @throws BusinessException
	 */
	public void rewind() throws BusinessException {
		// verificar que la factura no esta ABONADA
		// decrementar acumulado en medio de pago
		// desenlazar factura, cargo y edio de pago
		if(getFactura().getStatus().equals(FacturaStatus.SIN_ABONAR)) {
			getMedioPago().acumulado -= this.importe;
			Association.Cargar.unlink(this);
		}
	}
	

}
