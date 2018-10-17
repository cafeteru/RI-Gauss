package uo.ri.ui.foreman;

import uo.ri.ui.foreman.action.clientes.AddClienteAction;
import uo.ri.ui.foreman.action.clientes.DeleteClienteAction;
import uo.ri.ui.foreman.action.clientes.DetailsClienteAction;
import uo.ri.ui.foreman.action.clientes.ListAllClienteAction;
import uo.ri.ui.foreman.action.clientes.ModifyClienteAction;
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
