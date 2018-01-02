package uo.ri.business.impl;

import java.util.List;

import uo.ri.business.AdminService;
import uo.ri.business.dto.MechanicDto;
import uo.ri.business.dto.VoucherDto;
import uo.ri.business.dto.VoucherSummary;
import uo.ri.business.impl.admin.bonos.*;
import uo.ri.business.impl.admin.mecanicos.AddMechanic;
import uo.ri.business.impl.admin.mecanicos.DeleteMechanic;
import uo.ri.business.impl.admin.mecanicos.FindAllMechanics;
import uo.ri.business.impl.admin.mecanicos.FindMechanicById;
import uo.ri.business.impl.admin.mecanicos.UpdateMechanic;
import uo.ri.conf.Factory;
import uo.ri.util.exception.BusinessException;

public class AdminServiceImpl implements AdminService {

	// FACHADA

	private CommandExecutor executor = Factory.executor.forExecutor();

	@Override
	public void newMechanic(MechanicDto mecanico) throws BusinessException {
		executor.execute(new AddMechanic(mecanico));
	}

	@Override
	public void updateMechanic(MechanicDto mecanico) throws BusinessException {
		executor.execute(new UpdateMechanic(mecanico));
	}

	@Override
	public void deleteMechanic(Long idMecanico) throws BusinessException {
		executor.execute(new DeleteMechanic(idMecanico));
	}

	@Override
	public List<MechanicDto> findAllMechanics() throws BusinessException {
		return executor.execute(new FindAllMechanics());
	}

	@Override
	public MechanicDto findMechanicById(Long id) throws BusinessException {
		return executor.execute(new FindMechanicById(id));
	}

	@Override
	public int generateVouchers() throws BusinessException {
		int count = 0;
		count += executor.execute(new Bono3Averias());
		count += executor.execute(new BonoFacturaSuperior500());
		count += executor.execute(new Bono3Recomendaciones());
		return count;
	}

	@Override
	public List<VoucherDto> findVouchersByClientId(Long id)
			throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VoucherSummary> getVoucherSummary() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
