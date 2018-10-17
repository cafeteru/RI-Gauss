package uo.ri.persistence.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.BreakDownDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.conf.Conf;
import uo.ri.persistence.BreakDownGateway;

public class BreakDownGatewayImplementation implements BreakDownGateway{

	@Override
	public List<BreakDownDto> showAll() {
		Connection connection = null;
		try {
			connection = Jdbc.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
		Statement pst = null;
		ResultSet rs = null;
		List<BreakDownDto> lista = new ArrayList<BreakDownDto>();

		try {
			pst = connection.createStatement();
			rs = pst.executeQuery(Conf.getInstance().getProperty("SQL_LIST_BREAKDOWN"));
			if (rs.next() == false) {
				throw new BusinessException("No hay averias");
			}
			while(rs.next()) {
				BreakDownDto bd = new BreakDownDto();
				bd.id = rs.getLong(1);
				bd.status = rs.getString(2);
				lista.add(bd);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Jdbc.close(rs, pst);
		}
		
		return lista;
	}

	@Override
	public BreakDownDto getAveria(Long id) {
		Connection connection = null;
		try {
			connection = Jdbc.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
		PreparedStatement pst = null;
		ResultSet rs = null;
		BreakDownDto bd = null;

		try {
			pst = connection.prepareStatement(Conf.getInstance().getProperty("SQL_GET_BREAKDOWN"));
			pst.setLong(1, id);
			rs = pst.executeQuery();
			if (rs.next() == false) {
				throw new BusinessException("No existe esa averias");
			}
			while(rs.next()) {
				bd = new BreakDownDto();
				bd.id = rs.getLong(1);
				bd.status = rs.getString(2);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Jdbc.close(rs, pst);
		}
		
		return bd;
		}

	@Override
	public void verificarAverias(List<Long> idsAveria) {
		Connection connection = null;
		try {
			connection = Jdbc.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = connection.prepareStatement(Conf.getInstance().getProperty("SQL_VERIFICAR_ESTADO_AVERIA"));
			
			for (Long idAveria : idsAveria) {
				pst.setLong(1, idAveria);
				
				rs = pst.executeQuery();
				if (rs.next() == false) {
					throw new BusinessException("No existe la averia " + idAveria);
				}
				
				String status = rs.getString(1); 
				if (! "TERMINADA".equalsIgnoreCase(status) ) {
					throw new BusinessException("No está terminada la avería " + idAveria);
				}
				
				rs.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Jdbc.close(rs, pst);
		}
	}
	}


