package uo.ri.business.impl.cash;

import java.util.List;

import uo.ri.business.dto.CardDto;
import uo.ri.business.impl.Command;
import uo.ri.business.repository.ClienteRepository;
import uo.ri.business.repository.MedioPagoRepository;
import uo.ri.conf.Factory;
import uo.ri.model.*;
import uo.ri.util.exception.BusinessException;

public class AddCard implements Command<Void> {

	private MedioPagoRepository rMedios = Factory.repository.forMedioPago();
	private ClienteRepository rCliente = Factory.repository.forCliente();
	private CardDto dto;

	public AddCard(CardDto dto) {
		this.dto = dto;
	}

	public Void execute() throws BusinessException {
		Cliente cliente = rCliente.findById(dto.clientId);
		assertNotRepeatedMedioPago(cliente.getId());
		TarjetaCredito card = new TarjetaCredito(cliente, dto.cardNumber);
		card.setTipo(dto.cardType);
		card.setValidez(dto.cardExpiration);
		if (!card.isValidNow()) {
			throw new BusinessException("Validez no valida");
		}
		rMedios.add(card);
		return null;
	}

	private void assertNotRepeatedMedioPago(Long id) throws BusinessException {
		List<MedioPago> m = rMedios.findPaymentMeansByClientId(id);
		for (MedioPago p1 : m) {
			if (p1 instanceof TarjetaCredito
					&& ((TarjetaCredito) p1).getNumero().equals(dto.cardNumber))
				throw new BusinessException("Ya existe ese medio de pago");
		}
	}
}