package uo.ri.ui.admin;

import alb.util.menu.BaseMenu;
import uo.ri.ui.admin.action.bonos.GeneracionBonosAction;
import uo.ri.ui.admin.action.mecanicos.DeleteMechanicAction;
import uo.ri.ui.admin.action.mecanicos.ListMechanicsAction;
import uo.ri.ui.admin.action.mecanicos.UpdateMechanicAction;

public class BonosMenu extends BaseMenu {

	public BonosMenu() {
		menuOptions = new Object[][] {
				{ "Administrador > Gestión de Bonos", null },

				{ "Generación automática de bonos",
						GeneracionBonosAction.class },
				{ "Listado de bonos ", UpdateMechanicAction.class },
				{ "Eliminar mecánico", DeleteMechanicAction.class },
				{ "Listar mecánicos", ListMechanicsAction.class }, };
	}

}
