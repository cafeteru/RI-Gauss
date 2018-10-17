package uo.ri.business.serviceLayer;

import java.util.List;

import uo.ri.business.dto.MechanicDto;
import uo.ri.business.exception.BusinessException;

public interface MechanicService {

	public void addMechanic(MechanicDto m) throws BusinessException;
	public void deleteMechanic(MechanicDto m) throws BusinessException;
	public void updateMechanic(MechanicDto m) throws BusinessException;
	public List<MechanicDto> listMechanic() throws BusinessException;
	
}
