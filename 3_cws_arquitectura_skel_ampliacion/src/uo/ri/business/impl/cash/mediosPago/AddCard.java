package uo.ri.business.impl.cash.mediosPago;

import uo.ri.business.dto.CardDto;
import uo.ri.business.impl.Command;
import uo.ri.business.repository.ClienteRepository;
import uo.ri.business.repository.MedioPagoRepository;
import uo.ri.conf.Factory;
import uo.ri.model.Cliente;
import uo.ri.model.TarjetaCredito;
import uo.ri.util.exception.BusinessException;
import uo.ri.util.exception.Check;

public class AddCard implements Command<Void> {

	private MedioPagoRepository rMedios = Factory.repository.forMedioPago();
	private ClienteRepository rCliente = Factory.repository.forCliente();
	private CardDto dto;

	public AddCard(CardDto dto) {
		this.dto = dto;
	}

	public Void execute() throws BusinessException {
		Cliente cliente = rCliente.findById(dto.clientId);
		Check.noEsNull(cliente);
		assertNotRepeatedMedioPago(dto.cardNumber);
		TarjetaCredito card = new TarjetaCredito(cliente, dto.cardNumber);
		card.setTipo(dto.cardType);
		card.setValidez(dto.cardExpiration);
		if (!card.isValidNow()) {
			throw new BusinessException("Validez no valida");
		}
		rMedios.add(card);
		return null;
	}

	private void assertNotRepeatedMedioPago(String number)
			throws BusinessException {
		TarjetaCredito m = rMedios.findCreditCardByNumber(number);
		if (m != null)
			throw new BusinessException("Ya existe ese medio de pago");
	}
}