package uo.ri.ui.foreman;

import uo.ri.ui.foreman.action.clientes.*;
import uo.ri.ui.util.ExceptionMenu;

public class ClientesMenu extends ExceptionMenu {

	public ClientesMenu() {
		menuOptions = new Object[][] {
				{ "Jefe de Taller > Gestión de clientes", null },
				{ "Añadir cliente", AddClienteAction.class },
				{ "Eliminar cliente", DeleteClienteAction.class },
				{ "Mostrar detalles del cliente", DetailsClienteAction.class },
				{ "Listar todos los clientes", ListAllClienteAction.class },
				{ "Modificar cliente", ModifyClienteAction.class }, };
	}
}
