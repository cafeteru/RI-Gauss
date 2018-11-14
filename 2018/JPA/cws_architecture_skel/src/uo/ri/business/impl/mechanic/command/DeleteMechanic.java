package uo.ri.business.impl.mechanic.command;

import uo.ri.business.exception.BusinessCheck;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.impl.Command;
import uo.ri.business.repository.MecanicoRepository;
import uo.ri.conf.Factory;
import uo.ri.model.Mecanico;

public class DeleteMechanic implements Command<Void> {

	private Long idMecanico;

	private MecanicoRepository mecanicoRepository;

	public DeleteMechanic(Long idMecanico) {
		this.idMecanico = idMecanico;
		mecanicoRepository = Factory.repository.forMechanic();
	}

	public Void execute() throws BusinessException {
		Mecanico mecanico = mecanicoRepository.findById(idMecanico);
		BusinessCheck.isNotNull(mecanico);
		mecanicoRepository.remove(mecanico);
		return null;
	}

}
