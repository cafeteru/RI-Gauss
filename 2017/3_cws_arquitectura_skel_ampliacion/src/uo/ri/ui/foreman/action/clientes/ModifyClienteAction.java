package uo.ri.ui.foreman.action.clientes;

import alb.util.console.Console;
import uo.ri.business.ForemanService;
import uo.ri.business.dto.ClientDto;
import uo.ri.conf.Factory;
import uo.ri.ui.util.ActionTemplate;
import uo.ri.util.exception.BusinessException;

public class ModifyClienteAction extends ActionTemplate {
	private ForemanService as = Factory.service.forForeman();
	private ClientDto dto = new ClientDto();

	@Override
	protected void pedirDatos() {
		dto.dni = Console.readString("Dni del cliente");
		dto.name = Console.readString("Nombre del cliente");
		dto.surname = Console.readString("Apellidos del cliente");
		dto.addressStreet = Console.readString("Calle del cliente");
		dto.addressCity = Console.readString("Ciudad del cliente");
		dto.addressZipcode = Console.readString("Código postal del cliente");
		dto.phone = Console.readString("Telefono del cliente");
		dto.email = Console.readString("Email del cliente");
	}

	@Override
	protected void procesarDatos() throws BusinessException {
		as.updateClient(dto);
	}

	@Override
	protected void imprimirMensaje() {
		Console.print("Cliente modificado");
	}

}
