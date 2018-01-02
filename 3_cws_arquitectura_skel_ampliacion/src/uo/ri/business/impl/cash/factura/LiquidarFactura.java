package uo.ri.business.impl.cash.factura;

import java.util.Map;

import uo.ri.business.dto.InvoiceDto;
import uo.ri.business.impl.Command;
import uo.ri.business.repository.CargoRepository;
import uo.ri.business.repository.FacturaRepository;
import uo.ri.conf.Factory;
import uo.ri.model.Cargo;
import uo.ri.util.exception.BusinessException;

public class LiquidarFactura implements Command<InvoiceDto> {

	private FacturaRepository rFactory = Factory.repository.forFactura();
	private CargoRepository rCargo = Factory.repository.forCargo();
	private Long idFactura;
	private Map<Long, Double> cargos;

	public LiquidarFactura(Long idFactura, Map<Long, Double> cargos) {
		this.idFactura = idFactura;
		this.cargos = cargos;
	}

	@Override
	public InvoiceDto execute() throws BusinessException {
		// TODO Auto-generated method stub
		// for (Cargo c : cargos) {
		// rCargo.add(c);
		// }
		return null;
	}

}
