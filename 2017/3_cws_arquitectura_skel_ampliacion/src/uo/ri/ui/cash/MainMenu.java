package uo.ri.ui.cash;

import uo.ri.business.impl.BusinessServiceFactory;
import uo.ri.conf.Factory;
import uo.ri.persistence.jpa.JpaRepositoryFactory;
import uo.ri.persistence.jpa.executor.JpaExecutorFactory;
import uo.ri.ui.util.ExceptionMenu;

public class MainMenu extends ExceptionMenu {

	public MainMenu() {
		menuOptions = new Object[][] { { "Caja de Taller", null },
				{ "Gestión de facturas", FacturaMenu.class },
				{ "Gestión de medios de pago", MediosPagoMenu.class }, };
	}

	public static void main(String[] args) {
		new MainMenu().config().execute();
	}

	/**
	 * Configures the main components of the application
	 * 
	 * @return this
	 */
	private MainMenu config() {
		Factory.service = new BusinessServiceFactory();
		Factory.repository = new JpaRepositoryFactory();
		Factory.executor = new JpaExecutorFactory();

		return this;
	}

}
