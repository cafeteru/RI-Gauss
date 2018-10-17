package uo.ri.ui.foreman.action.clientes;

import alb.util.console.Console;
import uo.ri.business.ForemanService;
import uo.ri.business.dto.ClientDto;
import uo.ri.conf.Factory;
import uo.ri.ui.util.ActionTemplate;
import uo.ri.ui.util.Printer;
import uo.ri.util.exception.BusinessException;

public class DetailsClienteAction extends ActionTemplate {
	private ForemanService as = Factory.service.forForeman();
	private Long id;
	private ClientDto dto;

	@Override
	protected void pedirDatos() {
		id = Console.readLong("Id del cliente");
	}

	@Override
	protected void procesarDatos() throws BusinessException {
		dto = as.findClientById(id);
	}

	@Override
	protected void imprimirMensaje() {
		Printer.printCliente(dto);
	}

}
