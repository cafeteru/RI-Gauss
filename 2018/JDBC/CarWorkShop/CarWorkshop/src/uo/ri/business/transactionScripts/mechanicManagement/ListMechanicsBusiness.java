package uo.ri.business.transactionScripts.mechanicManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.MechanicDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.conf.Conf;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.MechanicGateway;

public class ListMechanicsBusiness{
	
	public List<MechanicDto> execute() throws BusinessException {

		MechanicGateway mg = PersistenceFactory.getMechanicGateway();
		List<MechanicDto> list = mg.read();
		return list;
	}
}
