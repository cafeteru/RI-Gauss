package uo.ri.ui.cash;

import uo.ri.ui.cash.action.mediosPago.AddMedioPagoAction;
import uo.ri.ui.cash.action.mediosPago.DeleteMedioPagoAction;
import uo.ri.ui.cash.action.mediosPago.ListMedioPagoAction;
import uo.ri.ui.util.ExceptionMenu;

public class MediosPagoMenu extends ExceptionMenu {

	public MediosPagoMenu() {
		menuOptions = new Object[][] {
				{ "Caja de Taller > Gestión de medios de pago", null },
				{ "Añadir medio de pago", AddMedioPagoAction.class },
				{ "Eliminar medio de pago", DeleteMedioPagoAction.class },
				{ "Listar medios de pago", ListMedioPagoAction.class }, };
	}
}
