package uo.ri.ui.admin.action.bonos;

import java.util.List;

import uo.ri.business.AdminService;
import uo.ri.business.dto.VoucherSummary;
import uo.ri.conf.Factory;
import uo.ri.ui.util.ActionTemplate;
import uo.ri.ui.util.Printer;
import uo.ri.util.exception.BusinessException;

public class ResumenBonosClienteAction extends ActionTemplate {

	private AdminService as = Factory.service.forAdmin();
	private List<VoucherSummary> lista;

	@Override
	protected void pedirDatos() {
	}

	@Override
	protected void procesarDatos() throws BusinessException {
		lista = as.getVoucherSummary();
	}

	@Override
	protected void imprimirMensaje() {
		Printer.printListVoucherSummary(lista);
	}

}
