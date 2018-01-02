package uo.ri.business.impl.admin.mecanicos;

import uo.ri.business.dto.MechanicDto;
import uo.ri.business.impl.Command;
import uo.ri.business.repository.MecanicoRepository;
import uo.ri.conf.Factory;
import uo.ri.model.Mecanico;
import uo.ri.util.exception.BusinessException;
import uo.ri.util.exception.Check;

public class UpdateMechanic implements Command<Void> {
	// COMANDO
	private MechanicDto dto;

	public UpdateMechanic(MechanicDto dto) {
		this.dto = dto;
	}

	public Void execute() throws BusinessException {
		MecanicoRepository r = Factory.repository.forMechanic();
		Mecanico m = r.findById(dto.id);
		Check.isNotNull(m, "El mecánico no existe");
		m.setNombre(dto.name);
		m.setApellidos(dto.surname);
		return null;
	}

}
