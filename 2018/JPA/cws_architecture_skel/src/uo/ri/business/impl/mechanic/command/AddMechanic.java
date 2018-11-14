package uo.ri.business.impl.mechanic.command;

import uo.ri.business.dto.MechanicDto;
import uo.ri.business.exception.BusinessCheck;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.impl.Command;
import uo.ri.business.impl.util.DtoAssembler;
import uo.ri.business.repository.MecanicoRepository;
import uo.ri.conf.Factory;

public class AddMechanic implements Command<Void> {

	private MechanicDto dto;
	private MecanicoRepository mecanicoRepository;

	public AddMechanic(MechanicDto mecanico) {
		this.dto = mecanico;
		mecanicoRepository = Factory.repository.forMechanic();
	}

	public Void execute() throws BusinessException {
		BusinessCheck.isNull(mecanicoRepository.findByDni(dto.dni));
		mecanicoRepository.add(DtoAssembler.toEntity(dto));
		return null;
	}

}
