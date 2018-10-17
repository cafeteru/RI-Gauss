package uo.ri.persistence;

import java.util.List;

import uo.ri.business.dto.BreakDownDto;

public interface BreakDownGateway {

	public List<BreakDownDto> showAll();
	public BreakDownDto getAveria(Long id);
	public void verificarAverias(List<Long> idsAveria);
	
}
