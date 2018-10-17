package uo.ri.business.serviceLayer;

import uo.ri.business.dto.FacturaDto;
import uo.ri.business.dto.ListaIdsDto;
import uo.ri.business.exception.BusinessException;

public interface FacturaService {

	public FacturaDto facturarReparaciones(ListaIdsDto l)throws BusinessException;
	
}
