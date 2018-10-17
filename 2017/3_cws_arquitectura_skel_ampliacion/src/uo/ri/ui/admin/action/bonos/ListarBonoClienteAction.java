package uo.ri.ui.admin.action.bonos;

import java.util.Iterator;
import java.util.List;

import alb.util.console.Console;
import uo.ri.business.AdminService;
import uo.ri.business.dto.VoucherDto;
import uo.ri.business.dto.VoucherSummary;
import uo.ri.conf.Factory;
import uo.ri.ui.util.ActionTemplate;
import uo.ri.ui.util.Printer;
import uo.ri.util.exception.BusinessException;

public class ListarBonoClienteAction extends ActionTemplate {
	private AdminService as = Factory.service.forAdmin();
	private Long id;
	private List<VoucherDto> dtos;
	private VoucherSummary dto;

	@Override
	protected void pedirDatos() {
		id = Console.readLong("Id del cliente");
	}

	@Override
	protected void procesarDatos() throws BusinessException {
		dtos = as.findVouchersByClientId(id);
		Iterator<VoucherSummary> i = as.getVoucherSummary().iterator();
		while (i.hasNext()) {
			VoucherSummary resumen = i.next();
			if (resumen.id.equals(id)) {
				dto = resumen;
				break;
			}
		}
	}

	@Override
	protected void imprimirMensaje() {
		Printer.printVoucherDtos(dtos);
		Printer.printVoucherSummary(dto);
	}

}
