package uo.ri.ui.admin.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.dto.MechanicDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.serviceLayer.MechanicService;
import uo.ri.conf.ServicesFactory;

public class AddMechanicAction implements Action {

	@Override
	public void execute() throws BusinessException {
		
		// Pedir datos
		String dni = Console.readString("Dni"); 
		String nombre = Console.readString("Nombre"); 
		String apellidos = Console.readString("Apellidos");
		
		// Creamos el Dto
		MechanicDto m = new MechanicDto();
		m.dni = dni;
		m.name = nombre;
		m.surname = apellidos;
		
		//Procesamos la llamada
//		AddMechanicBusiness add = new AddMechanicBusiness(m);
//		add.execute();
		
//		MechanicService ms = new MechanicServiceImplementation();
		
		MechanicService ms = ServicesFactory.getMechanicService();
		ms.addMechanic(m);
		
		// Mostrar resultado
		Console.println("Nuevo mecánico añadido");
	}

}
