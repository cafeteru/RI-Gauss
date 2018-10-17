package uo.ri.business.impl.cash.factura;

import java.util.Map;

import uo.ri.business.dto.InvoiceDto;
import uo.ri.business.impl.Command;
import uo.ri.business.impl.util.DtoAssembler;
import uo.ri.business.repository.CargoRepository;
import uo.ri.business.repository.FacturaRepository;
import uo.ri.business.repository.MedioPagoRepository;
import uo.ri.conf.Factory;
import uo.ri.model.Cargo;
import uo.ri.model.Factura;
import uo.ri.model.MedioPago;
import uo.ri.model.types.FacturaStatus;
import uo.ri.util.exception.BusinessException;

public class LiquidarFactura implements Command<InvoiceDto> {

	private FacturaRepository rFactura = Factory.repository.forFactura();
	private MedioPagoRepository rMedio = Factory.repository.forMedioPago();
	private CargoRepository rCargo = Factory.repository.forCargo();
	private Long idFactura;
	private Map<Long, Double> cargos;

	public LiquidarFactura(Long idFactura, Map<Long, Double> cargos) {
		this.idFactura = idFactura;
		this.cargos = cargos;
	}

	@Override
	public InvoiceDto execute() throws BusinessException {
		Factura f = rFactura.findById(idFactura);
		if (f == null)
			throw new BusinessException("No existe la factura");
		if (!f.getStatus().equals(FacturaStatus.ABONADA)) {
			for (Map.Entry<Long, Double> pair : cargos.entrySet()) {
				MedioPago m = rMedio.findById(pair.getKey());
				Cargo c = new Cargo(f, m, pair.getValue());
				rCargo.add(c);
			}
			f.settle();
			return DtoAssembler.toDto(f);
		}
		throw new BusinessException("Ya está abonada");
	}

}
