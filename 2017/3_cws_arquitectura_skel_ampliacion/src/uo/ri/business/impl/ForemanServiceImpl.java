package uo.ri.business.impl;

import java.util.List;

import uo.ri.business.ForemanService;
import uo.ri.business.dto.ClientDto;
import uo.ri.business.impl.foreman.AddCliente;
import uo.ri.business.impl.foreman.DeleteCliente;
import uo.ri.business.impl.foreman.FindAllClientes;
import uo.ri.business.impl.foreman.FindClienteById;
import uo.ri.business.impl.foreman.ModifyCliente;
import uo.ri.conf.Factory;
import uo.ri.util.exception.BusinessException;

public class ForemanServiceImpl implements ForemanService {
	private CommandExecutor executor = Factory.executor.forExecutor();

	@Override
	public void addClient(ClientDto c, Long recomenderId)
			throws BusinessException {
		executor.execute(new AddCliente(c, recomenderId));
	}

	@Override
	public ClientDto findClientById(Long id) throws BusinessException {
		return executor.execute(new FindClienteById(id));
	}

	@Override
	public void updateClient(ClientDto c) throws BusinessException {
		executor.execute(new ModifyCliente(c));
	}

	@Override
	public void deleteClient(Long id) throws BusinessException {
		executor.execute(new DeleteCliente(id));
	}

	@Override
	public List<ClientDto> findAllClients() throws BusinessException {
		return executor.execute(new FindAllClientes());
	}

	@Override
	public List<ClientDto> findRecomendedClientsByClienteId(Long id)
			throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
