package uo.ri.business.impl.cash;

import uo.ri.business.impl.Command;
import uo.ri.business.repository.MedioPagoRepository;
import uo.ri.conf.Factory;
import uo.ri.model.MedioPago;
import uo.ri.model.Metalico;
import uo.ri.util.exception.BusinessException;
import uo.ri.util.exception.Check;

public class DeleteMedioPago implements Command<Void> {

	private MedioPagoRepository rMedios = Factory.repository.forMedioPago();
	private Long id;

	public DeleteMedioPago(Long id) {
		this.id = id;
	}

	@Override
	public Void execute() throws BusinessException {
		MedioPago m = rMedios.findById(id);
		comprobarMetalico(m);
		comprobarCargos(m);
		rMedios.remove(m);
		return null;
	}

	private void comprobarMetalico(MedioPago m) throws BusinessException {
		if (m instanceof Metalico)
			throw new BusinessException("No se puede borrar metalico");
		Check.isNotNull(m, "El medio de pago no existe");
	}

	private void comprobarCargos(MedioPago m) throws BusinessException {
		int cargos = m.getCargos().size();
		if (cargos > 0)
			throw new BusinessException("No se puede borrar metalico");
	}

}
