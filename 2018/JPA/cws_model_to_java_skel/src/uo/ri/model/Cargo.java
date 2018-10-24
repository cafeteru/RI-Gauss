package uo.ri.model;

public class Cargo {

	private Factura factura;
	private MedioPago medioPago;
	private double importe = 0.0;

	public Cargo(Factura factura, MedioPago medioPago, double importe) {
		// incrementar el importe en el acumulado del medio de pago
		// guardar el importe
		// enlazar (link) factura, este cargo y medioDePago
//		if (medioPago instanceof Bono
//				&& ((Bono) medioPago).getDisponible() < importe) {
//			throw new IllegalStateException("No hay dinero suficiente el bono");
//		} else if (medioPago instanceof TarjetaCredito && Dates.isAfter(
//				Dates.today(), ((TarjetaCredito) medioPago).getValidez())) {
//			throw new IllegalStateException("Tarjeta de credito expirada");
//		}
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
	}

	public Factura getFactura() {
		// TODO Auto-generated method stub
		return factura;
	}

	public MedioPago getMedioPago() {
		// TODO Auto-generated method stub
		return medioPago;
	}

	Factura _getFactura() {
		// TODO Auto-generated method stub
		return factura;
	}

	MedioPago _getMedioPago() {
		// TODO Auto-generated method stub
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
