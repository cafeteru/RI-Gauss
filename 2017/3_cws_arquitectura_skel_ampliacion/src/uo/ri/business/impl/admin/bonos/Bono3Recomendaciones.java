package uo.ri.business.impl.admin.bonos;

import java.util.Set;

import uo.ri.model.Bono;
import uo.ri.model.Cliente;
import uo.ri.util.exception.BusinessException;

/**
 * Clase que crear los bonos automaticamente por haber recomendados a 3 o más
 * clientes.
 * 
 * @author Iván González Mahagamage
 *
 */
public class Bono3Recomendaciones extends BonoAbstract {

	@Override
	protected Set<Bono> cargarBonos(Cliente c) throws BusinessException {
		return c.crearBono3Recomendacion();
	}

}
