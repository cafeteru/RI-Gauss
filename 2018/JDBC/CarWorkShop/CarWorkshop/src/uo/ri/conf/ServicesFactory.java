package uo.ri.conf;

import uo.ri.business.serviceLayer.FacturaService;
import uo.ri.business.serviceLayer.MechanicService;
import uo.ri.business.serviceLayer.implementation.FacturaServiceImplementation;
import uo.ri.business.serviceLayer.implementation.MechanicServiceImplementation;

public class ServicesFactory {
	
	public static MechanicService getMechanicService() {
		return new MechanicServiceImplementation();
	}
	
	public static FacturaService getFacturaService() {
		return new FacturaServiceImplementation();
	}

}
