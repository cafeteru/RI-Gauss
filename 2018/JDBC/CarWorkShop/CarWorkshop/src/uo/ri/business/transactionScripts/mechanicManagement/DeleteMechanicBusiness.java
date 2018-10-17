package uo.ri.business.transactionScripts.mechanicManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import alb.util.menu.Action;
import uo.ri.business.dto.MechanicDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.conf.Conf;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.MechanicGateway;

public class DeleteMechanicBusiness implements Action {

	private MechanicDto mechanic;
	
	public DeleteMechanicBusiness(MechanicDto m) {
		this.mechanic = m;
	}

	@Override
	public void execute() throws BusinessException {
		
		MechanicGateway mg = PersistenceFactory.getMechanicGateway();
		mg.delete(mechanic);
		
	}

}
