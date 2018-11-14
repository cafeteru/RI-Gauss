package uo.ri.business.impl;

import uo.ri.business.CloseBreakdownService;
import uo.ri.business.ContractCategoryCrudService;
import uo.ri.business.ContractCrudService;
import uo.ri.business.ContractTypeCrudService;
import uo.ri.business.InvoiceService;
import uo.ri.business.MechanicCrudService;
import uo.ri.business.PayrollService;
import uo.ri.business.ServiceFactory;
import uo.ri.business.VehicleReceptionService;
import uo.ri.business.impl.invoice.InvoiceServiceImpl;
import uo.ri.business.impl.mechanic.MechanicCrudServiceImpl;

public class BusinessFactory implements ServiceFactory {

	@Override
	public MechanicCrudService forMechanicCrudService() {
		return new MechanicCrudServiceImpl();
	}

	@Override
	public InvoiceService forInvoice() {
		return new InvoiceServiceImpl();
	}

	@Override
	public VehicleReceptionService forVehicleReception() {
		throw new RuntimeException("Not yet implemented");
	}

	@Override
	public CloseBreakdownService forClosingBreakdown() {
		throw new RuntimeException("Not yet implemented");
	}

	@Override
	public ContractCrudService forContractCrud() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ContractTypeCrudService forContractTypeCrud() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ContractCategoryCrudService forContractCategoryCrud() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PayrollService forPayroll() {
		// TODO Auto-generated method stub
		return null;
	}

}
