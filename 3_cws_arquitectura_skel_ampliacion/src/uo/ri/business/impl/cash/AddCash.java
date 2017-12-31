package uo.ri.business.impl.cash;

import uo.ri.business.dto.CashDto;
import uo.ri.business.dto.InvoiceDto;
import uo.ri.business.dto.MechanicDto;
import uo.ri.business.impl.Command;
import uo.ri.business.impl.util.DtoAssembler;
import uo.ri.business.repository.MecanicoRepository;
import uo.ri.business.repository.MedioPagoRepository;
import uo.ri.conf.Factory;
import uo.ri.model.Mecanico;
import uo.ri.util.exception.BusinessException;
import uo.ri.util.exception.Check;

public class AddCash implements Command<Void> {

	private CashDto dto;
	private MedioPagoRepository r = Factory.repository.forMedioPago();

	public AddCash(CashDto dto) {
		this.dto = dto;
	}

	public Void execute() throws BusinessException {
		assertNotRepeatedDni(dto.dni);

		Mecanico m = DtoAssembler.toEntity(dto);
		r.add(m);
		return null;
	}

	private void assertNotRepeatedDni(String dni) throws BusinessException {
		Mecanico m = r.findByDni(dni);
		Check.isNull(m, "Ya existe ese dni");
	}

}
