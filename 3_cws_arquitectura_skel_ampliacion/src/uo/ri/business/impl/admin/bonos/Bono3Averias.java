package uo.ri.business.impl.admin.bonos;

import java.util.Set;

import uo.ri.model.Bono;
import uo.ri.model.Cliente;
import uo.ri.util.exception.BusinessException;

public class Bono3Averias extends BonoAbstract {

	@Override
	protected Set<Bono> cargarBonos(Cliente c) throws BusinessException {
		return c.crearBonoAveria();
	}

}
