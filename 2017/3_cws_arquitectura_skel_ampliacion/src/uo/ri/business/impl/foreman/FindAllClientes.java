package uo.ri.business.impl.foreman;

import java.util.List;

import uo.ri.business.dto.ClientDto;
import uo.ri.business.impl.Command;
import uo.ri.business.impl.util.DtoAssembler;
import uo.ri.business.repository.ClienteRepository;
import uo.ri.conf.Factory;
import uo.ri.util.exception.BusinessException;

public class FindAllClientes implements Command<List<ClientDto>> {
	private ClienteRepository rCliente = Factory.repository.forCliente();

	@Override
	public List<ClientDto> execute() throws BusinessException {
		return DtoAssembler.toClientDtoList(rCliente.findAll());
	}

}
