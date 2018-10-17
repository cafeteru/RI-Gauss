package uo.ri.ui.admin.action;

import java.util.List;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.dto.MechanicDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.serviceLayer.MechanicService;
import uo.ri.conf.ServicesFactory;
import uo.ri.ui.util.Printer;

public class ListMechanicsAction implements Action {
	
	@Override
	public void execute() throws BusinessException {

		Console.println("\nListado de mecánicos\n");  
		
//		ListMechanicsBusiness listar = new ListMechanicsBusiness();
//		List<MechanicDto> result = listar.execute();
		
//		mostrarMecanicos(result);
		
//		MechanicService ms = new MechanicServiceImplementation();
		
		MechanicService ms = ServicesFactory.getMechanicService();
		mostrarMecanicos(ms.listMechanic());
	}

	private void mostrarMecanicos(List<MechanicDto> result) {
		for(MechanicDto m : result) {
			Printer.printMechanic(m);
		}
	}
}

	