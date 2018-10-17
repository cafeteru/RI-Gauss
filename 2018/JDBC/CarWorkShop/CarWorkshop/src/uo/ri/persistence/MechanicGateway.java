package uo.ri.persistence;

import java.util.List;

import uo.ri.business.dto.MechanicDto;

public interface MechanicGateway {
	
	void create(MechanicDto m);
	List<MechanicDto> read();
	void update(MechanicDto m);
	void delete(MechanicDto m);
}
