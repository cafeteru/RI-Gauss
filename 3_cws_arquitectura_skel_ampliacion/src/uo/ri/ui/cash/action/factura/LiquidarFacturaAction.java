package uo.ri.ui.cash.action.factura;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import alb.util.console.Console;
import uo.ri.business.CashService;
import uo.ri.business.dto.InvoiceDto;
import uo.ri.business.dto.PaymentMeanDto;
import uo.ri.conf.Factory;
import uo.ri.ui.util.ActionTemplate;
import uo.ri.ui.util.Printer;
import uo.ri.util.exception.BusinessException;

public class LiquidarFacturaAction extends ActionTemplate {

	private CashService r = Factory.service.forCash();
	private Long idFactura;
	private Map<Long, Double> cargos = new HashMap<>();
	private List<PaymentMeanDto> m;

	@Override
	protected void pedirDatos() {
		idFactura = Console.readLong("Id de la factura");
	}

	@Override
	protected void procesarDatos() throws BusinessException {
		InvoiceDto factura = r.findInvoiceByNumber(idFactura);
		cargarMetodosPagoFactura();
		calcularCargos(factura);
		r.settleInvoice(idFactura, cargos);
	}

	@Override
	protected void imprimirMensaje() {
		Console.print("Factura liquidada correctamente.");
	}

	private void cargarMetodosPagoFactura() throws BusinessException {
		m = r.findPaymentMeansForInvoice(idFactura);
		Console.print("El cliente dispone de los siguiente medios de pagos:");
		Printer.printPaymentMeans(m);
	}

	private void calcularCargos(InvoiceDto factura) {
		Console.print("El importe de la factura es " + factura.total + "€");
		while (factura.total > 0) {
			Long id = Console.readLong("Seleccione un método de pago (id)");
			double cantidad = Console
					.readDouble("Seleccione cantidad para pagar con él");
			factura.total -= cantidad;
			cargos.put(id, cantidad);
		}
	}

}
