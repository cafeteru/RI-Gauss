package uo.ri.business.transactionScripts.mechanicManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import alb.util.console.Console;
import alb.util.jdbc.Jdbc;
import alb.util.menu.Action;
import uo.ri.business.dto.MechanicDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.conf.Conf;
import uo.ri.persistence.MechanicGateway;
import uo.ri.persistence.implementation.MechanicGatewayImplementation;

public class UpdateMechanicBusiness {
	
	private MechanicDto mechanic;

	public UpdateMechanicBusiness(MechanicDto m) {
		this.mechanic = m;
	}

	public void execute() throws BusinessException {
		
		MechanicGateway mg = new MechanicGatewayImplementation();
		mg.update(mechanic);
	}

}
