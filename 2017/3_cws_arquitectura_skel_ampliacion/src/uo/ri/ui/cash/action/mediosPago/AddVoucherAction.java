package uo.ri.ui.cash.action.mediosPago;

import alb.util.console.Console;
import alb.util.random.Random;
import uo.ri.business.dto.VoucherDto;
import uo.ri.util.exception.BusinessException;

public class AddVoucherAction extends AddMedioPagoAction {
	private VoucherDto bono;

	public AddVoucherAction(Long id) {
		this.id = id;
		bono = new VoucherDto();
		masDatos();
	}

	@Override
	public void execute() throws BusinessException {
		as.addVoucherPaymentMean(bono);
	}

	@Override
	protected void masDatos() {
		bono.clientId = id;
		bono.code = "B" + Random.string('0', '9', 4);
		bono.available = Console.readDouble("Disponible");
		bono.description = Console.readString("Descripción");
	}

}