package uo.ri.business.impl.admin.bonos;

import java.util.ArrayList;
import java.util.List;

import uo.ri.business.dto.VoucherSummary;
import uo.ri.business.impl.Command;
import uo.ri.business.impl.util.DtoAssembler;
import uo.ri.business.repository.ClienteRepository;
import uo.ri.conf.Factory;
import uo.ri.model.Cliente;
import uo.ri.util.exception.BusinessException;

public class ResumenBonosCliente implements Command<List<VoucherSummary>> {
	private ClienteRepository rCliente = Factory.repository.forCliente();

	@Override
	public List<VoucherSummary> execute() throws BusinessException {
		List<Cliente> clientes = rCliente.findAll();
		List<VoucherSummary> lista = new ArrayList<>();
		for (Cliente c : clientes) {
			lista.add(DtoAssembler.calculateSumary(c));
		}
		return lista;
	}

}
