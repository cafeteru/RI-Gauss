package uo.ri.business.impl;

import java.util.List;

import uo.ri.business.AdminService;
import uo.ri.business.dto.MechanicDto;
import uo.ri.business.impl.admin.AddMechanic;
import uo.ri.business.impl.admin.DeleteMechanic;
import uo.ri.business.impl.admin.FindAllMechanics;
import uo.ri.business.impl.admin.FindMechanicById;
import uo.ri.business.impl.admin.UpdateMechanic;
import uo.ri.conf.Factory;
import uo.ri.util.exception.BusinessException;

public class AdminServiceImpl implements AdminService {

	private CommandExecutor executor = Factory.executor.forExecutor();

	@Override
	public void newMechanic(MechanicDto mecanico) throws BusinessException {
		new AddMechanic(mecanico).execute();
	}

	@Override
	public void updateMechanic(MechanicDto mecanico) throws BusinessException {
		new UpdateMechanic(mecanico).execute();
	}

	@Override
	public void deleteMechanic(Long idMecanico) throws BusinessException {
		new DeleteMechanic(idMecanico).execute();
	}

	@Override
	public List<MechanicDto> findAllMechanics() throws BusinessException {
		return new FindAllMechanics().execute();
	}

	@Override
	public MechanicDto findMechanicById(Long id) throws BusinessException {
		return new FindMechanicById(id).execute();
	}

}
