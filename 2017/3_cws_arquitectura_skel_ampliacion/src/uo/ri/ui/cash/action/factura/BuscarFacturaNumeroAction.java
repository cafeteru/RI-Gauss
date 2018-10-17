package uo.ri.ui.cash.action.factura;

import alb.util.console.Console;
import uo.ri.business.CashService;
import uo.ri.business.dto.InvoiceDto;
import uo.ri.conf.Factory;
import uo.ri.ui.util.ActionTemplate;
import uo.ri.ui.util.Printer;
import uo.ri.util.exception.BusinessException;

public class BuscarFacturaNumeroAction extends ActionTemplate {

	private CashService cs = Factory.service.forCash();

	private Long numero;
	private InvoiceDto dto;

	@Override
	protected void pedirDatos() {
		numero = Console.readLong("Número de la factura");
	}

	@Override
	protected void procesarDatos() throws BusinessException {
		dto = cs.findInvoiceByNumber(numero);
	}

	@Override
	protected void imprimirMensaje() {
		Printer.printInvoice(dto);
	}

}
