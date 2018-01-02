package uo.ri.business.impl.admin.bonos;

import uo.ri.business.impl.Command;
import uo.ri.business.repository.MedioPagoRepository;
import uo.ri.conf.Factory;
import uo.ri.util.exception.BusinessException;

public class BonoFacturaSuperior500 implements Command<Integer> {
	private MedioPagoRepository rMedios = Factory.repository.forMedioPago();
	@Override
	public Integer execute() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
