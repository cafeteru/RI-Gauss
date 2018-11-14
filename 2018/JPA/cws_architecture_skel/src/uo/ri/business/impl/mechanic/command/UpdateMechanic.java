package uo.ri.business.impl.mechanic.command;

import uo.ri.business.dto.MechanicDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.impl.Command;
import uo.ri.business.repository.MecanicoRepository;
import uo.ri.conf.Factory;
import uo.ri.model.Mecanico;

public class UpdateMechanic implements Command<Void> {

	private MechanicDto dto;
	private MecanicoRepository mecanicoRepository;

	public UpdateMechanic(MechanicDto dto) {
		this.dto = dto;
		mecanicoRepository = Factory.repository.forMechanic();
	}

	public Void execute() throws BusinessException {
		Mecanico mecanico = mecanicoRepository.findById(dto.id);
		mecanico.setNombre(dto.name);
		mecanico.setApellidos(dto.surname);
		mecanico.setDni(dto.dni);
		mecanicoRepository.add(mecanico);
		return null;
	}

}
