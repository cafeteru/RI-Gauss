package uo.ri.business.impl.admin.bonos;

import java.util.List;

import uo.ri.business.dto.VoucherDto;
import uo.ri.business.impl.Command;
import uo.ri.business.impl.util.DtoAssembler;
import uo.ri.business.repository.MedioPagoRepository;
import uo.ri.conf.Factory;
import uo.ri.model.Bono;
import uo.ri.util.exception.BusinessException;

public class ListadoBonoCliente implements Command<List<VoucherDto>> {
	private MedioPagoRepository rMedios = Factory.repository.forMedioPago();
	private Long id;

	public ListadoBonoCliente(Long id) {
		this.id = id;
	}

	@Override
	public List<VoucherDto> execute() throws BusinessException {
		List<Bono> lista = rMedios.findVouchersByClientId(id);
		if (lista != null)
			return DtoAssembler.toVoucherDtoList(lista);
		return null;
	}

}
