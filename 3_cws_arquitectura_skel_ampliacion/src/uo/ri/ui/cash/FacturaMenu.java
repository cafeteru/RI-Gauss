package uo.ri.ui.cash;

import alb.util.menu.NotYetImplementedAction;
import uo.ri.ui.cash.action.factura.FacturarReparacionesAction;
import uo.ri.ui.cash.action.factura.ReparacionesNoFacturadasUnClienteAction;
import uo.ri.ui.util.ExceptionMenu;

public class FacturaMenu extends ExceptionMenu {

	public FacturaMenu() {
		menuOptions = new Object[][] { { "Caja de Taller", null },
				{ "Buscar reparaciones no facturadas de un cliente",
						ReparacionesNoFacturadasUnClienteAction.class },
				{ "Buscar reparación por matrícula",
						NotYetImplementedAction.class },
				{ "Facturar reparaciones", FacturarReparacionesAction.class },
				{ "Liquidar factura", NotYetImplementedAction.class }, };
	}

}