package uo.ri.ui.cash.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import alb.util.console.Console;
import alb.util.date.Dates;
import alb.util.jdbc.Jdbc;
import alb.util.math.Round;
import alb.util.menu.Action;
import uo.ri.business.dto.FacturaDto;
import uo.ri.business.dto.ListaIdsDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.serviceLayer.FacturaService;
import uo.ri.business.serviceLayer.implementation.FacturaServiceImplementation;
import uo.ri.business.transactionScripts.facturasManagement.FacturarReparacionesBusiness;
import uo.ri.conf.ServicesFactory;

public class FacturarReparacionesAction implements Action {
	
	@Override
	public void execute() throws BusinessException {
		List<Long> idsAveria = new ArrayList<Long>();
		// pedir las averias a incluir en la factura
		do {
			Long id = Console.readLong("ID de averia");
			idsAveria.add(id);
		} while ( masAverias() );
		
		ListaIdsDto l = new ListaIdsDto();
		l.ids = idsAveria;
		
//		FacturarReparacionesBusiness fact = new FacturarReparacionesBusiness(l);
//		fact.execute();
		
		//FacturaService ms = new FacturaServiceImplementation();
		
		FacturaService ms = ServicesFactory.getFacturaService();
		FacturaDto factura = ms.facturarReparaciones(l);
		
		mostrarFactura(factura.numeroFactura, factura.fechaFactura, factura.totalFactura, factura.iva,
				factura.totalConIva);
	}

	//Action
	private void mostrarFactura(long numeroFactura, Date fechaFactura,
			double totalFactura, double iva, double totalConIva) {
		
		Console.printf("Factura nº: %d\n", numeroFactura);
		Console.printf("\tFecha: %1$td/%1$tm/%1$tY\n", fechaFactura);
		Console.printf("\tTotal: %.2f €\n", totalFactura);
		Console.printf("\tIva: %.1f %% \n", iva);
		Console.printf("\tTotal con IVA: %.2f €\n", totalConIva);
	}

	//Business

	//Action
	private boolean masAverias() {
		return Console.readString("¿Añadir más averias? (s/n) ").equalsIgnoreCase("s");
	}

}
