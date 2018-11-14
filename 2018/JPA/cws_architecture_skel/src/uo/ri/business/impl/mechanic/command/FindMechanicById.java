package uo.ri.business.impl.mechanic.command;

import uo.ri.business.dto.MechanicDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.impl.Command;
import uo.ri.business.impl.util.DtoAssembler;
import uo.ri.conf.Factory;
import uo.ri.model.Mecanico;

public class FindMechanicById implements Command<MechanicDto> {

	private Long id;

	public FindMechanicById(Long id) {
		this.id = id;
	}

	public MechanicDto execute() throws BusinessException {
		Mecanico mecanico = Factory.repository.forMechanic().findById(id);
		return DtoAssembler.toDto(mecanico);
	}

}
