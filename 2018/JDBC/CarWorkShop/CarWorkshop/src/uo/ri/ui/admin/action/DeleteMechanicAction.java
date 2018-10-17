package uo.ri.ui.admin.action;


import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.dto.MechanicDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.serviceLayer.MechanicService;
import uo.ri.conf.ServicesFactory;

public class DeleteMechanicAction implements Action {

	@Override
	public void execute() throws BusinessException {
		Long idMecanico = Console.readLong("Id de mecánico"); 
		
		MechanicDto m = new MechanicDto();
		m.id = idMecanico;
		
		//Procesamos la llamada
//		DeleteMechanicBusiness add = new DeleteMechanicBusiness(m);
//		add.execute();
		
//		MechanicService ms = new MechanicServiceImplementation();
		
		MechanicService ms = ServicesFactory.getMechanicService();
		ms.deleteMechanic(m);
		
		Console.println("Se ha eliminado el mecánico");
	}

}
