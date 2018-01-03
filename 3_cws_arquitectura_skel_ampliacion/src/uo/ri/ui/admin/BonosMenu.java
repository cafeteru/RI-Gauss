package uo.ri.ui.admin;

import uo.ri.ui.admin.action.bonos.GeneracionBonosAction;
import uo.ri.ui.admin.action.bonos.ListarBonoClienteAction;
import uo.ri.ui.admin.action.bonos.ResumenBonosClienteAction;
import uo.ri.ui.util.ExceptionMenu;

public class BonosMenu extends ExceptionMenu {

	public BonosMenu() {
		menuOptions = new Object[][] {
				{ "Administrador > Gestión de Bonos", null },

				{ "Generación automática de bonos",
						GeneracionBonosAction.class },
				{ "Listado de los bonos de un cliente dado",
						ListarBonoClienteAction.class },
				{ "Listado de información agregada de los bonos de cada cliente",
						ResumenBonosClienteAction.class } };
	}

}
