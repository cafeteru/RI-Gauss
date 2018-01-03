package uo.ri.ui.admin.action.bonos;

import alb.util.console.Console;
import uo.ri.business.AdminService;
import uo.ri.conf.Factory;
import uo.ri.ui.util.ActionTemplate;
import uo.ri.util.exception.BusinessException;

public class GeneracionBonosAction extends ActionTemplate {

	private AdminService as = Factory.service.forAdmin();
	private int contador = 0;

	@Override
	protected void pedirDatos() {
	}

	@Override
	protected void procesarDatos() throws BusinessException {
		contador = as.generateVouchers();
		// as.ejecutar();
	}

	@Override
	protected void imprimirMensaje() {
		Console.print("Bonos creados: " + contador);
	}
}
