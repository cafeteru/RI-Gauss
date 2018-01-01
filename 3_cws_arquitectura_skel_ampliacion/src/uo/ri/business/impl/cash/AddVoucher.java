package uo.ri.business.impl.cash;

import java.util.List;

import uo.ri.business.dto.VoucherDto;
import uo.ri.business.impl.Command;
import uo.ri.business.repository.ClienteRepository;
import uo.ri.business.repository.MedioPagoRepository;
import uo.ri.conf.Factory;
import uo.ri.model.*;
import uo.ri.util.exception.BusinessException;

public class AddVoucher implements Command<Void> {

	private MedioPagoRepository rMedios = Factory.repository.forMedioPago();
	private ClienteRepository rCliente = Factory.repository.forCliente();
	private VoucherDto dto;

	public AddVoucher(VoucherDto dto) {
		this.dto = dto;
	}

	public Void execute() throws BusinessException {
		Cliente cliente = rCliente.findById(dto.clientId);
		assertNotRepeatedMedioPago(cliente.getId());
		Bono m = new Bono(cliente, dto.code);
		m.setDisponible(dto.available);
		m.setDescripcion(dto.description);
		rMedios.add(m);
		return null;
	}

	private void assertNotRepeatedMedioPago(Long id) throws BusinessException {
		List<MedioPago> m = rMedios.findByClientId(id);
		for (MedioPago p1 : m) {
			if (p1 instanceof Bono && ((Bono) p1).getCodigo().equals(dto.code))
				throw new BusinessException("Ya existe ese medio de pago");
		}
	}

}
