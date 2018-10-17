package uo.ri.ui.cash.action.mediosPago;

import java.util.List;

import alb.util.console.Console;
import uo.ri.business.CashService;
import uo.ri.business.dto.PaymentMeanDto;
import uo.ri.conf.Factory;
import uo.ri.ui.util.ActionTemplate;
import uo.ri.ui.util.Printer;
import uo.ri.util.exception.BusinessException;

public class ListMedioPagoAction extends ActionTemplate {

	protected Long id;
	protected CashService as = Factory.service.forCash();
	private List<PaymentMeanDto> lista;

	@Override
	protected void pedirDatos() {
		id = Console.readLong("Id del cliente");
	}

	@Override
	protected void procesarDatos() throws BusinessException {
		lista = as.findPaymentMeansByClientId(id);

	}

	@Override
	protected void imprimirMensaje() {
		Printer.printPaymentMeans(lista);
	}

}
