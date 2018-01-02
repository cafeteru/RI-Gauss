package uo.ri.ui.admin;

import uo.ri.business.impl.BusinessServiceFactory;
import uo.ri.conf.Factory;
import uo.ri.persistence.jpa.JpaRepositoryFactory;
import uo.ri.persistence.jpa.executor.JpaExecutorFactory;
import uo.ri.ui.admin.action.bonos.GeneracionBonosAction;
import uo.ri.ui.util.ExceptionMenu;

public class MainMenu extends ExceptionMenu {

	public MainMenu() {
		menuOptions = new Object[][] { { "Administrador", null },
				{ "Gestión de mecánicos", MecanicosMenu.class },
				{ "Gestión de repuestos", RepuestosMenu.class },
				{ "Gestión de tipos de vehículo", TiposVehiculoMenu.class },
				{ "Generación automática de bonos",
						GeneracionBonosAction.class }, };
	}

	public static void main(String[] args) {
		new MainMenu().configure().execute();
	}

	/**
	 * Configures the main components of the application
	 * 
	 * @return this
	 */
	private MainMenu configure() {
		Factory.service = new BusinessServiceFactory();
		Factory.repository = new JpaRepositoryFactory();
		Factory.executor = new JpaExecutorFactory();

		return this;
	}

}
