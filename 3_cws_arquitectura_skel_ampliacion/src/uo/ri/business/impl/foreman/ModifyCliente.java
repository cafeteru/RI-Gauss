package uo.ri.business.impl.foreman;

import uo.ri.business.dto.ClientDto;
import uo.ri.business.impl.Command;
import uo.ri.business.repository.ClienteRepository;
import uo.ri.conf.Factory;
import uo.ri.model.Cliente;
import uo.ri.model.types.Address;
import uo.ri.util.exception.BusinessException;
import uo.ri.util.exception.Check;

public class ModifyCliente implements Command<Void> {
	private ClienteRepository rCliente = Factory.repository.forCliente();
	private ClientDto dto;

	public ModifyCliente(ClientDto c) {
		this.dto = c;
	}

	@Override
	public Void execute() throws BusinessException {
		Cliente c = rCliente.findById(dto.id);
		Check.isNotNull(c, "El cliente no existe");
		c.setNombre(dto.name);
		c.setApellidos(dto.surname);
		Address a = new Address(dto.addressStreet, dto.addressCity,
				dto.addressZipcode);
		c.setAddress(a);
		c.setPhone(dto.phone);
		c.setEmail(dto.email);
		return null;
	}

}
