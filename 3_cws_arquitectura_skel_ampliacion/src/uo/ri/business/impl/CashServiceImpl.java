package uo.ri.business.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceException;

import uo.ri.business.CashService;
import uo.ri.business.dto.*;
import uo.ri.business.impl.cash.factura.BuscarFacturaNumero;
import uo.ri.business.impl.cash.factura.CreateInvoiceFor;
import uo.ri.business.impl.cash.factura.LiquidarFactura;
import uo.ri.business.impl.cash.factura.ListarMediosFactura;
import uo.ri.business.impl.cash.mediosPago.AddCard;
import uo.ri.business.impl.cash.mediosPago.AddVoucher;
import uo.ri.business.impl.cash.mediosPago.DeleteMedioPago;
import uo.ri.business.impl.cash.mediosPago.ListMedios;
import uo.ri.conf.Factory;
import uo.ri.util.exception.BusinessException;

public class CashServiceImpl implements CashService {

	private CommandExecutor executor = Factory.executor.forExecutor();

	@Override
	public InvoiceDto createInvoiceFor(List<Long> idsAveria)
			throws BusinessException {
		return executor.execute(new CreateInvoiceFor(idsAveria));
	}

	@Override
	public List<PaymentMeanDto> findPayMethodsForInvoice(Long idInvoiceDto)
			throws BusinessException {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BreakdownDto> findRepairsByClient(String dni)
			throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InvoiceDto findInvoiceByNumber(Long numeroFactura)
			throws BusinessException {
		return executor.execute(new BuscarFacturaNumero(numeroFactura));
	}

	@Override
	public List<PaymentMeanDto> findPaymentMeansForInvoice(Long idFactura)
			throws BusinessException {
		return executor.execute(new ListarMediosFactura(idFactura));
	}

	@Override
	public InvoiceDto settleInvoice(Long idFactura, Map<Long, Double> cargos)
			throws BusinessException {
		return executor.execute(new LiquidarFactura(idFactura, cargos));
	}

	@Override
	public void addCardPaymentMean(CardDto card) throws BusinessException {
		executor.execute(new AddCard(card));
	}

	@Override
	public void addVoucherPaymentMean(VoucherDto voucher)
			throws BusinessException {
		executor.execute(new AddVoucher(voucher));
	}

	@Override
	public void deletePaymentMean(Long id) throws BusinessException {
		executor.execute(new DeleteMedioPago(id));
	}

	@Override
	public List<PaymentMeanDto> findPaymentMeansByClientId(Long id)
			throws BusinessException {
		try {
			return executor.execute(new ListMedios(id));
		} catch (PersistenceException e) {
			throw new BusinessException("La base de datos animaaaaaaaaaaal");
		}
	}

}
