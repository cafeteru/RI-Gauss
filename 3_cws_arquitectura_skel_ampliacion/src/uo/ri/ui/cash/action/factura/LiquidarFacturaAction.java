package uo.ri.ui.cash.action.factura;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import alb.util.console.Console;
import uo.ri.business.CashService;
import uo.ri.business.dto.PaymentMeanDto;
import uo.ri.conf.Factory;
import uo.ri.ui.util.ActionTemplate;
import uo.ri.ui.util.Printer;
import uo.ri.util.exception.BusinessException;

public class LiquidarFacturaAction extends ActionTemplate {

	private CashService r = Factory.service.forCash();
	private Long idFactura;

	@Override
	protected void pedirDatos() {
		idFactura = Console.readLong("Id de la factura");
	}

	@Override
	protected void procesarDatos() throws BusinessException {
		double importe = r.findInvoiceByNumber(idFactura).total;
		List<PaymentMeanDto> m = r.findPaymentMeansForInvoice(idFactura);
		Console.print("El cliente dispone de los siguiente medios de pagos:");
		Printer.printPaymentMeans(m);
		Map<Long, Double> cargos = new HashMap<>();
		Console.print("El importe de la factura es " + importe + "€");
		while (importe > 0) {
			Long id = Console.readLong("Seleccione un método de pago (id)");
			double cantidad = Console
					.readDouble("Seleccione cantidad para pagar con él");
			importe -= cantidad;
			cargos.put(id, cantidad);
		}
		r.settleInvoice(idFactura, cargos);
	}

	@Override
	protected void imprimirMensaje() {
		// TODO Auto-generated method stub

	}

}
