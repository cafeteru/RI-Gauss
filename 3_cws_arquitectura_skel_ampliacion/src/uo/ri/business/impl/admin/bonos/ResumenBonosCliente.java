package uo.ri.business.impl.admin.bonos;

import java.util.ArrayList;
import java.util.List;

import uo.ri.business.dto.VoucherSummary;
import uo.ri.business.impl.Command;
import uo.ri.business.repository.ClienteRepository;
import uo.ri.conf.Factory;
import uo.ri.model.Bono;
import uo.ri.model.Cliente;
import uo.ri.model.MedioPago;
import uo.ri.util.exception.BusinessException;

public class ResumenBonosCliente implements Command<List<VoucherSummary>> {
	private ClienteRepository rCliente = Factory.repository.forCliente();

	@Override
	public List<VoucherSummary> execute() throws BusinessException {
		List<Cliente> clientes = rCliente.findAll();
		List<VoucherSummary> lista = new ArrayList<>();
		for (Cliente c : clientes) {
			lista.add(realizarResumen(c));
		}
		return lista;
	}

	public VoucherSummary realizarResumen(Cliente c) {
		VoucherSummary v = new VoucherSummary();
		v.dni = c.getDni();
		v.name = c.getNombre();
		v.surname = c.getApellidos();
		int contador = 0;
		for (MedioPago m : c.getMediosPago()) {
			if (m instanceof Bono) {
				contador++;
				v.available += ((Bono) m).getDisponible();
				v.consumed += m.getAcumulado();
			}
		}
		v.emitted = contador;
		v.totalAmount = v.available + v.consumed;
		return v;
	}

}
