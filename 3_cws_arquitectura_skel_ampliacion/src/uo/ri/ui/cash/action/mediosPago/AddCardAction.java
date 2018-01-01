package uo.ri.ui.cash.action.mediosPago;

import alb.util.console.Console;
import alb.util.date.DateUtil;
import uo.ri.business.dto.CardDto;
import uo.ri.util.exception.BusinessException;

public class AddCardAction extends AddMedioPagoAction {

	private CardDto p2;

	public AddCardAction(Long id) {
		this.id = id;
		p2 = new CardDto();
		masDatos();
	}

	@Override
	public void execute() throws BusinessException {
		as.addCardPaymentMean(p2);
	}

	@Override
	protected void masDatos() {
		p2.clientId = id;
		p2.cardNumber = Console.readString("Número de la tarjeta");
		Console.println("Fecha de la expiración");
		int dia = Console.readInt("Dia");
		int mes = Console.readInt("Mes");
		int anyo = Console.readInt("Año");
		p2.cardExpiration = DateUtil.fromDdMmYyyy(dia, mes, anyo);
		p2.cardType = Console.readString("Tipo de tarjeta");
	}

}