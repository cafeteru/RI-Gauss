package uo.ri.ui.cash.action.mediosPago;

import java.util.Date;

import alb.util.console.Console;
import alb.util.date.DateUtil;
import uo.ri.business.CashService;
import uo.ri.business.dto.CardDto;
import uo.ri.business.dto.CashDto;
import uo.ri.business.dto.PaymentMeanDto;
import uo.ri.business.dto.VoucherDto;
import uo.ri.conf.Factory;
import uo.ri.ui.util.Action;
import uo.ri.util.exception.BusinessException;

public class AddMedioPagoAction implements Action {

	@Override
	public void execute() throws BusinessException {
		CashService as = Factory.service.forCash();

		// Pedir datos

		Console.println("1 - Métalico");
		Console.println("2 - Bono");
		Console.println("3 - Tarjeta de credito");
		int tipo = Console.readInt("Seleccione tipo del método de pago");

		switch (tipo) {
		case 1:
			CashDto p = new CashDto();
			as.addCash(p);
			break;
		case 2:
			VoucherDto p1 = new VoucherDto();
			p1.code = Console.readString("Codigo");
			p1.available = Console.readDouble("Disponible");
			p1.description = Console.readString("Descripción");
			as.addVoucherPaymentMean(p1);
			break;
		case 3:
			CardDto p2 = new CardDto();
			p2.cardNumber = Console.readString("Número de la tarjeta");
			Console.println("Fecha de la expiración");
			int dia = Console.readInt("Dia");
			int mes = Console.readInt("mes");
			int anyo = Console.readInt("Año");
			p2.cardExpiration = DateUtil.fromDdMmYyyy(dia, mes, anyo);
			p2.cardType = Console.readString();
			as.addCardPaymentMean(p2);
			break;
		default:
			throw new BusinessException("Tipo no valido");
		}

		// MechanicDto m = new MechanicDto();
		// m.dni = Console.readString("Dni");
		// m.name = Console.readString("Nombre");
		// m.surname = Console.readString("Apellidos");
		//
		// // Procesar
		// AdminService as = Factory.service.forAdmin();
		// as.newMechanic(m);
		//
		// // Mostrar resultado
		// Console.println("Nuevo mecánico añadido");
	}

}
