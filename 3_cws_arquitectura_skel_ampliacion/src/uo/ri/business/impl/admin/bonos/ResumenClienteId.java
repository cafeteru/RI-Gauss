package uo.ri.business.impl.admin.bonos;

import uo.ri.business.dto.VoucherSummary;
import uo.ri.business.impl.Command;
import uo.ri.business.impl.util.DtoAssembler;
import uo.ri.business.repository.MedioPagoRepository;
import uo.ri.persistence.jpa.MedioPagoJpaRepository;
import uo.ri.util.exception.BusinessException;

public class ResumenClienteId implements Command<VoucherSummary> {

	private MedioPagoRepository rMedios = new MedioPagoJpaRepository();
	private Long id;

	public ResumenClienteId(Long id) {
		this.id = id;
	}

	@Override
	public VoucherSummary execute() throws BusinessException {
		Object[] resultados = rMedios.findAggregateVoucherDataByClientId(id);
		return DtoAssembler.toDto(resultados);
	}

}
