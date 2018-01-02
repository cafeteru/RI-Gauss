package uo.ri.business.impl.cash.mediosPago;

import java.util.List;

import uo.ri.business.dto.PaymentMeanDto;
import uo.ri.business.impl.Command;
import uo.ri.business.impl.util.DtoAssembler;
import uo.ri.business.repository.MedioPagoRepository;
import uo.ri.conf.Factory;
import uo.ri.model.MedioPago;
import uo.ri.util.exception.BusinessException;

public class ListMedios implements Command<List<PaymentMeanDto>> {
	private Long id;
	private MedioPagoRepository rMedios = Factory.repository.forMedioPago();

	public ListMedios(Long id) {
		this.id = id;
	}

	@Override
	public List<PaymentMeanDto> execute() throws BusinessException {
		List<MedioPago> lista = rMedios.findPaymentMeansByClientId(id);
		return DtoAssembler.toPaymentMeanDtoList(lista);
	}

}
