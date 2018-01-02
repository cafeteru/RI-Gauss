package uo.ri.business.impl.foreman;

import uo.ri.business.dto.ClientDto;
import uo.ri.business.impl.Command;
import uo.ri.business.impl.util.DtoAssembler;
import uo.ri.business.repository.ClienteRepository;
import uo.ri.conf.Factory;
import uo.ri.model.Cliente;
import uo.ri.model.Metalico;
import uo.ri.model.Recomendacion;
import uo.ri.util.exception.BusinessException;
import uo.ri.util.exception.Check;

public class AddCliente implements Command<Void> {

	private ClienteRepository rCliente = Factory.repository.forCliente();
	private ClientDto dto;
	private Long recomenderId;

	public AddCliente(ClientDto dto, Long recomenderId) {
		this.dto = dto;
		this.recomenderId = recomenderId;
	}

	@Override
	public Void execute() throws BusinessException {
		assertNotRepeatedDni(dto.dni);
		Cliente c = DtoAssembler.toEntity(dto);
		rCliente.add(c);

		Metalico m = new Metalico(rCliente.findByDni(c.getDni()));
		Factory.repository.forMedioPago().add(m);

		if (recomenderId != null) {
			Cliente c2 = rCliente.findById(recomenderId);
			Check.isNotNull(c2, "No existe el cliente recomendador");
			Recomendacion r = new Recomendacion(c2, c);
			Factory.repository.forRecomendacion().add(r);
		}
		return null;
	}

	private void assertNotRepeatedDni(String dni) throws BusinessException {
		Cliente m = rCliente.findByDni(dni);
		Check.isNull(m, "Ya existe un cliente con ese dni");
	}

}
