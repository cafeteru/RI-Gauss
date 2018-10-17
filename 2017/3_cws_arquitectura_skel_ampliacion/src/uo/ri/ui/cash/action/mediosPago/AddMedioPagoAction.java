package uo.ri.ui.cash.action.mediosPago;

import alb.util.console.Console;
import uo.ri.business.CashService;
import uo.ri.conf.Factory;
import uo.ri.ui.util.ActionTemplate;
import uo.ri.util.exception.BusinessException;

public class AddMedioPagoAction extends ActionTemplate {

	protected Long id;
	private Integer tipo;
	protected CashService as = Factory.service.forCash();

	@Override
	protected void pedirDatos() {
		id = Console.readLong("Id del cliente");
		Console.println("1 - Bono");
		Console.println("2 - Tarjeta de credito");
		tipo = Console.readInt("Seleccione tipo del método de pago");
	}

	@Override
	protected void procesarDatos() throws BusinessException {
		switch (tipo) {
		case 1:
			new AddVoucherAction(id).execute();
			break;
		case 2:
			new AddCardAction(id).execute();
			break;
		default:
			throw new BusinessException("Tipo no valido");
		}
	}

	@Override
	protected void imprimirMensaje() {
		Console.println("Nuevo metodo de pago añadido");
	}

	protected void masDatos() {

	}
}
