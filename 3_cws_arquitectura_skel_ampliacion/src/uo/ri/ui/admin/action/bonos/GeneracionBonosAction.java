package uo.ri.ui.admin.action.bonos;

import alb.util.console.Console;
import uo.ri.business.AdminService;
import uo.ri.conf.Factory;
import uo.ri.ui.util.ActionTemplate;
import uo.ri.util.exception.BusinessException;

public class GeneracionBonosAction extends ActionTemplate {

	private AdminService as = Factory.service.forAdmin();

	@Override
	protected void pedirDatos() {
	}

	@Override
	protected void procesarDatos() throws BusinessException {
		as.generateVouchers();
	}

	@Override
	protected void imprimirMensaje() {
		Console.print("Bonos creados");
	}
}
