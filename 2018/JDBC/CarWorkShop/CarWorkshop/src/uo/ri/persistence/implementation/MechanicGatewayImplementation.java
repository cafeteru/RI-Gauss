package uo.ri.persistence.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.MechanicDto;
import uo.ri.conf.Conf;
import uo.ri.persistence.MechanicGateway;

public class MechanicGatewayImplementation implements MechanicGateway {

	@Override
	public void create(MechanicDto mechanic) {
		// Procesar
				Connection c = null;
				PreparedStatement pst = null;
				ResultSet rs = null;

				try {
					c = Jdbc.getConnection();
					
					pst = c.prepareStatement(Conf.getInstance().getProperty("SQL_INSERT_MECANICO"));
					pst.setString(1, mechanic.dni);
					pst.setString(2, mechanic.name);
					pst.setString(3, mechanic.surname);
					
					pst.executeUpdate();
					
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
				finally {
					Jdbc.close(rs, pst, c);
				}
		
	}

	@Override
	public List<MechanicDto> read() {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<MechanicDto> lista = new ArrayList<MechanicDto>();
		MechanicDto m;

		try {
			c = Jdbc.getConnection();
			
			pst = c.prepareStatement(Conf.getInstance().getProperty("SQL_LIST_MECANICOS"));
			
			rs = pst.executeQuery();
			while(rs.next()) {
				m = new MechanicDto();
				m.id = rs.getLong(1);
				m.dni = rs.getString(2);
				m.name = rs.getString(3);
				m.surname = rs.getString(4);
				lista.add(m);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
		return lista;
	}

	@Override
	public void update(MechanicDto mechanic) {
		
			Connection c = null;
			PreparedStatement pst = null;
			ResultSet rs = null;

			try {
				c = Jdbc.getConnection();
				
				pst = c.prepareStatement(Conf.getInstance().getProperty("SQL_UPDATE_MECANICO"));
				pst.setString(1, mechanic.name);
				pst.setString(2, mechanic.surname);
				pst.setLong(3, mechanic.id);
				
				pst.executeUpdate();
				
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			finally {
				Jdbc.close(rs, pst, c);
			}
		
	}

	@Override
	public void delete(MechanicDto mechanic) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getConnection();
			
			pst = c.prepareStatement(Conf.getInstance().getProperty("SQL_DELETE_MECANICO"));
			pst.setLong(1, mechanic.id);
			
			pst.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
		
	}

}
