package uo.ri.business.serviceLayer.implementation;

import java.util.List;

import uo.ri.business.dto.MechanicDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.serviceLayer.MechanicService;
import uo.ri.business.transactionScripts.mechanicManagement.AddMechanicBusiness;
import uo.ri.business.transactionScripts.mechanicManagement.DeleteMechanicBusiness;
import uo.ri.business.transactionScripts.mechanicManagement.ListMechanicsBusiness;
import uo.ri.business.transactionScripts.mechanicManagement.UpdateMechanicBusiness;

public class MechanicServiceImplementation implements MechanicService{

	@Override
	public void addMechanic(MechanicDto m) throws BusinessException {
		AddMechanicBusiness add = new AddMechanicBusiness(m);
		add.execute();
		
	}

	@Override
	public void deleteMechanic(MechanicDto m) throws BusinessException {
		DeleteMechanicBusiness add = new DeleteMechanicBusiness(m);
		add.execute();
		
	}

	@Override
	public void updateMechanic(MechanicDto m) throws BusinessException {
		UpdateMechanicBusiness update = new UpdateMechanicBusiness(m);
		update.execute();
		
	}

	@Override
	public List<MechanicDto> listMechanic() throws BusinessException {
		ListMechanicsBusiness listar = new ListMechanicsBusiness();
		List<MechanicDto> result = listar.execute();
		return result;
	}

}
