package uo.ri.ui.foreman.action.clientes;

import alb.util.console.Console;
import uo.ri.business.ForemanService;
import uo.ri.conf.Factory;
import uo.ri.ui.util.ActionTemplate;
import uo.ri.util.exception.BusinessException;

public class DeleteClienteAction extends ActionTemplate {
	private ForemanService as = Factory.service.forForeman();
	private Long id;

	@Override
	protected void pedirDatos() {
		id = Console.readLong("Id del cliente");
	}

	@Override
	protected void procesarDatos() throws BusinessException {
		as.deleteClient(id);
	}

	@Override
	protected void imprimirMensaje() {
		Console.print("Cliente borrado correctamente");

	}

}
