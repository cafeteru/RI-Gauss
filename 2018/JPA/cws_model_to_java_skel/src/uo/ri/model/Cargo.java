package uo.ri.model;

import uo.ri.model.types.FacturaStatus;

public class Cargo {

	private Factura factura;
	private MedioPago medioPago;
	private double importe = 0.0;

	public Cargo(Factura factura, MedioPago medioPago, double importe) {
		// incrementar el importe en el acumulado del medio de pago
		// guardar el importe
		// enlazar (link) factura, este cargo y medioDePago
		medioPago.isValid(importe);
		medioPago.setAcumulado(medioPago.getAcumulado() + importe);
		this.setImporte(importe);
		Association.Cargar.link(factura, this, medioPago);
	}

	/**
	 * Anula (retrocede) este cargo de la factura y el medio de pago Solo se
	 * puede hacer si la factura no está abonada Decrementar el acumulado del
	 * medio de pago Desenlazar el cargo de la factura y el medio de pago
	 * 
	 * @throws IllegalStateException if the invoice is already settled
	 */
	public void rewind() {
		// verificar que la factura no esta ABONADA
		// decrementar acumulado en medio de pago (medioPago.pagar( -importe ))
		// desenlazar factura, cargo y medio de pago
		if (!factura.getStatus().equals(FacturaStatus.ABONADA)) {
			medioPago.pagar(importe);
			Association.Cargar.unlink(this);
		} else {
			throw new IllegalStateException("El cargo no esta abonado");
		}
	}

	public Factura getFactura() {
		return factura;
	}

	public MedioPago getMedioPago() {
		return medioPago;
	}

	void _setFactura(Factura factura) {
		this.factura = factura;

	}

	void _setMedioPago(MedioPago m) {
		this.medioPago = m;

	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}
}
