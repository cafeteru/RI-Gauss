package uo.ri.ui.cash;

import uo.ri.ui.cash.action.factura.*;
import uo.ri.ui.util.ExceptionMenu;

public class FacturaMenu extends ExceptionMenu {

	public FacturaMenu() {
		menuOptions = new Object[][] { { "Caja de Taller", null },
				{ "Buscar reparaciones no facturadas de un cliente",
						ReparacionesNoFacturadasUnClienteAction.class },
				{ "Buscar reparación por matrícula",
						BuscarFacturaMatriculaAction.class },
				{ "Facturar reparaciones", FacturarReparacionesAction.class },
				{ "Liquidar factura", LiquidarFacturaAction.class },
				{ "Buscar factura por número", BuscarFacturaNumeroAction.class }, };
	}

}