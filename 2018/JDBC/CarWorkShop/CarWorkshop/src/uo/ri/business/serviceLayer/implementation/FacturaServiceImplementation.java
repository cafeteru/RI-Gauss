package uo.ri.business.serviceLayer.implementation;

import uo.ri.business.dto.FacturaDto;
import uo.ri.business.dto.ListaIdsDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.serviceLayer.FacturaService;
import uo.ri.business.transactionScripts.facturasManagement.FacturarReparacionesBusiness;

public class FacturaServiceImplementation implements FacturaService{

	public FacturaDto facturarReparaciones(ListaIdsDto l) throws BusinessException {
		FacturarReparacionesBusiness fact = new FacturarReparacionesBusiness(l);
		fact.execute();
		return fact.getFactura();
	}
}
