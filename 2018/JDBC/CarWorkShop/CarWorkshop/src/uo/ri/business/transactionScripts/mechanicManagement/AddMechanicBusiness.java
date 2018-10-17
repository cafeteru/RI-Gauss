package uo.ri.business.transactionScripts.mechanicManagement;

import alb.util.menu.Action;
import uo.ri.business.dto.MechanicDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.MechanicGateway;

public class AddMechanicBusiness implements Action {

	private MechanicDto mechanic;
	
	public AddMechanicBusiness(MechanicDto m) {
		this.mechanic = m;
	}
	
	@Override
	public void execute() throws BusinessException {
		
		MechanicGateway mg = PersistenceFactory.getMechanicGateway();
		mg.create(mechanic);
	}

}
