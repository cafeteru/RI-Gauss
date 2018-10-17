package uo.ri.ui.foreman.action.clientes;

import java.util.List;

import uo.ri.business.ForemanService;
import uo.ri.business.dto.ClientDto;
import uo.ri.conf.Factory;
import uo.ri.ui.util.ActionTemplate;
import uo.ri.ui.util.Printer;
import uo.ri.util.exception.BusinessException;

public class ListAllClienteAction extends ActionTemplate {
	private ForemanService as = Factory.service.forForeman();
	private List<ClientDto> dtos;

	@Override
	protected void pedirDatos() {
	}

	@Override
	protected void procesarDatos() throws BusinessException {
		dtos = as.findAllClients();
	}

	@Override
	protected void imprimirMensaje() {
		for (ClientDto d : dtos)
			Printer.printCliente(d);

	}

}
