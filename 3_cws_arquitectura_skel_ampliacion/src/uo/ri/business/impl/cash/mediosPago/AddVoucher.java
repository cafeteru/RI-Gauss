package uo.ri.business.impl.cash.mediosPago;

import uo.ri.business.dto.VoucherDto;
import uo.ri.business.impl.Command;
import uo.ri.business.repository.ClienteRepository;
import uo.ri.business.repository.MedioPagoRepository;
import uo.ri.conf.Factory;
import uo.ri.model.*;
import uo.ri.util.exception.BusinessException;
import uo.ri.util.exception.Check;

public class AddVoucher implements Command<Void> {

	private MedioPagoRepository rMedios = Factory.repository.forMedioPago();
	private ClienteRepository rCliente = Factory.repository.forCliente();
	private VoucherDto dto;

	public AddVoucher(VoucherDto dto) {
		this.dto = dto;
	}

	public Void execute() throws BusinessException {
		Cliente cliente = rCliente.findById(dto.clientId);
		Check.noEsNull(cliente);
		assertNotRepeatedMedioPago(dto.code);
		Bono m = new Bono(cliente, dto.code);
		m.setDisponible(dto.available);
		m.setDescripcion(dto.description);
		rMedios.add(m);
		return null;
	}

	private void assertNotRepeatedMedioPago(String code)
			throws BusinessException {
		Bono m = rMedios.findVoucherByCode(code);
		Check.esNull(m);
	}

}
