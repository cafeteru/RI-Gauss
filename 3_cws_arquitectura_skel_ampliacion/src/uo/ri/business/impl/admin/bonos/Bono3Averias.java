package uo.ri.business.impl.admin.bonos;

import java.util.List;

import uo.ri.business.impl.Command;
import uo.ri.business.repository.AveriaRepository;
import uo.ri.business.repository.ClienteRepository;
import uo.ri.business.repository.MedioPagoRepository;
import uo.ri.conf.Factory;
import uo.ri.model.Averia;
import uo.ri.model.Bono;
import uo.ri.model.Cliente;
import uo.ri.model.MedioPago;
import uo.ri.util.exception.BusinessException;

public class Bono3Averias implements Command<Integer> {
	private ClienteRepository rCliente = Factory.repository.forCliente();
	private MedioPagoRepository rMedios = Factory.repository.forMedioPago();

	@Override
	public Integer execute() throws BusinessException {
		List<Cliente> clientes = rCliente.findAll();
		int contador = 0;
		for (Cliente c : clientes) {
			List<Bono> lista = c.crearBonoAveria();
			if (lista != null) {
				for (Bono b : lista)
					rMedios.add(b);
				contador += lista.size();
			}
		}
		return contador;
	}

}
