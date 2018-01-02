package uo.ri.ui.foreman.action.clientes;

import alb.util.console.Console;
import uo.ri.business.ForemanService;
import uo.ri.business.dto.ClientDto;
import uo.ri.conf.Factory;
import uo.ri.ui.util.ActionTemplate;
import uo.ri.ui.util.Preguntar;
import uo.ri.util.exception.BusinessException;

public class AddClienteAction extends ActionTemplate {
	private ClientDto dto;
	private ForemanService as;
	private Long recomenderId;

	@Override
	protected void pedirDatos() {
		as = Factory.service.forForeman();
		dto = new ClientDto();
		dto.dni = Console.readString("Dni del cliente");
		dto.name = Console.readString("Nombre del cliente");
		dto.surname = Console.readString("Apellidos del cliente");
		dto.addressStreet = Console.readString("Calle del cliente");
		dto.addressCity = Console.readString("Ciudad del cliente");
		dto.addressZipcode = Console.readString("Código postal del cliente");
		dto.phone = Console.readString("Telefono del cliente");
		dto.email = Console.readString("Email del cliente");
		if (Preguntar.hacerPregunta("¿Viene recomendado? (s/n)"))
			recomenderId = Console.readLong("Id del recomendador");
		else
			recomenderId = null;
	}

	@Override
	protected void procesarDatos() throws BusinessException {
		as.addClient(dto, recomenderId);
	}

	@Override
	protected void imprimirMensaje() {
		Console.println("Nuevo cliente de pago añadido");
	}

}
