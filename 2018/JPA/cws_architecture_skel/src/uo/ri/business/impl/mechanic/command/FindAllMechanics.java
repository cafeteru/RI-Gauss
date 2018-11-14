package uo.ri.business.impl.mechanic.command;

import java.util.List;

import uo.ri.business.dto.MechanicDto;
import uo.ri.business.impl.Command;
import uo.ri.business.impl.util.DtoAssembler;
import uo.ri.conf.Factory;

public class FindAllMechanics implements Command<List<MechanicDto>> {

	public List<MechanicDto> execute() {
		return DtoAssembler
				.toMechanicDtoList(Factory.repository.forMechanic().findAll());
	}

}
