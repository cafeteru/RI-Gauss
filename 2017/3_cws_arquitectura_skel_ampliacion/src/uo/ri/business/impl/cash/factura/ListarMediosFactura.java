package uo.ri.business.impl.cash.factura;

import java.util.ArrayList;
import java.util.List;

import uo.ri.business.dto.PaymentMeanDto;
import uo.ri.business.impl.Command;
import uo.ri.business.impl.util.DtoAssembler;
import uo.ri.business.repository.FacturaRepository;
import uo.ri.business.repository.MedioPagoRepository;
import uo.ri.conf.Factory;
import uo.ri.model.Cliente;
import uo.ri.model.Factura;
import uo.ri.model.MedioPago;
import uo.ri.util.exception.BusinessException;

public class ListarMediosFactura implements Command<List<PaymentMeanDto>> {

	private MedioPagoRepository rMedios = Factory.repository.forMedioPago();
	private FacturaRepository rFactura = Factory.repository.forFactura();
	private Long idFactura;

	public ListarMediosFactura(Long idFactura) {
		this.idFactura = idFactura;
	}

	@Override
	public List<PaymentMeanDto> execute() throws BusinessException {
		Factura f = rFactura.findByNumber(idFactura);
		Cliente c = f.getAverias().iterator().next().getVehiculo().getCliente();
		List<MedioPago> medios = rMedios.findByClientId(c.getId());
		List<PaymentMeanDto> dtos = new ArrayList<>();
		for (MedioPago m : medios) {
			dtos.add(DtoAssembler.toDto(m));
		}
		return dtos;
	}

}
