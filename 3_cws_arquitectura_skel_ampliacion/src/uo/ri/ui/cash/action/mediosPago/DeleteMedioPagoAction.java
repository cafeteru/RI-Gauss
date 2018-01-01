package uo.ri.ui.cash.action.mediosPago;

import alb.util.console.Console;
import uo.ri.business.CashService;
import uo.ri.conf.Factory;
import uo.ri.ui.util.ActionTemplate;
import uo.ri.util.exception.BusinessException;

public class DeleteMedioPagoAction extends ActionTemplate {

	private Long id;

	protected CashService as = Factory.service.forCash();

	@Override
	protected void pedirDatos() {
		id = Console.readLong("Id del medio de pago");
	}

	@Override
	protected void procesarDatos() throws BusinessException {
		as.deletePaymentMean(id);
	}

	@Override
	protected void imprimirMensaje() {
		Console.println("Metodo de pago eliminado");
	}

}
