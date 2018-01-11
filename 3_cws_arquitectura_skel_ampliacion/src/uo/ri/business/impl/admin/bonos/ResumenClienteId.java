package uo.ri.business.impl.admin.bonos;

import uo.ri.business.dto.VoucherSummary;
import uo.ri.business.impl.Command;
import uo.ri.business.impl.util.DtoAssembler;
import uo.ri.business.repository.ClienteRepository;
import uo.ri.business.repository.MedioPagoRepository;
import uo.ri.conf.Factory;
import uo.ri.util.exception.BusinessException;

public class ResumenClienteId implements Command<VoucherSummary> {

	private ClienteRepository rCliente = Factory.repository.forCliente();
	private MedioPagoRepository rMedios = Factory.repository.forMedioPago();
	private Long id;

	public ResumenClienteId(Long id) {
		this.id = id;
	}

	@Override
	public VoucherSummary execute() throws BusinessException {
		Object[] resultados = rMedios.findAggregateVoucherDataByClientId(id);
		return DtoAssembler.toDto(rCliente.findById(id), resultados);
	}

}
