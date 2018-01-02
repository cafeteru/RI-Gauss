package uo.ri.business.impl.cash.factura;

import uo.ri.business.dto.InvoiceDto;
import uo.ri.business.impl.Command;
import uo.ri.business.impl.util.DtoAssembler;
import uo.ri.business.repository.FacturaRepository;
import uo.ri.conf.Factory;
import uo.ri.model.Factura;
import uo.ri.util.exception.BusinessException;

public class BuscarFacturaNumero implements Command<InvoiceDto> {

	private FacturaRepository r = Factory.repository.forFactura();
	private InvoiceDto dto;

	public BuscarFacturaNumero(Long numeroInvoiceDto) {
		dto = new InvoiceDto();
		dto.number = numeroInvoiceDto;
	}

	@Override
	public InvoiceDto execute() throws BusinessException {
		Factura factura = r.findByNumber(dto.number);
		if (factura == null)
			return null;
		return DtoAssembler.toDto(factura);
	}

}
