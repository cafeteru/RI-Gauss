package uo.ri.business.impl.admin.bonos;

import java.util.Set;

import uo.ri.model.Bono;
import uo.ri.model.Cliente;
import uo.ri.util.exception.BusinessException;

/**
 * Clase que crear los bonos automaticamente por tener facturas con importes
 * superiores a 500 € (IVA incluido).
 * 
 * @author Iván González Mahagamage
 *
 */
public class BonoFacturaSuperior500 extends BonoAbstract {

	@Override
	protected Set<Bono> cargarBonos(Cliente c) throws BusinessException {
		return c.crearBonoFacturas500();
	}

}
