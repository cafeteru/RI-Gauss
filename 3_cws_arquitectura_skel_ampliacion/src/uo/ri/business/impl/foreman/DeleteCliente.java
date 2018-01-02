package uo.ri.business.impl.foreman;

import uo.ri.business.impl.Command;
import uo.ri.business.repository.ClienteRepository;
import uo.ri.conf.Factory;
import uo.ri.model.Cliente;
import uo.ri.util.exception.BusinessException;
import uo.ri.util.exception.Check;

public class DeleteCliente implements Command<Void> {

	private ClienteRepository rCliente = Factory.repository.forCliente();
	private Long id;
	private Cliente c;

	public DeleteCliente(Long id) {
		this.id = id;
	}

	@Override
	public Void execute() throws BusinessException {
		c = rCliente.findById(id);
		Check.isNotNull(c, "El cliente no existe");
		if (c.getVehiculos().isEmpty()) {
			c.eliminarMetalico();
			c.eliminarRecomendaciones();
			rCliente.remove(c);
			return null;
		}
		throw new BusinessException(
				"El cliente no puede ser eliminado al tener vehículos "
						+ "registrados");
	}

}
