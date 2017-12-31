package uo.ri.business.impl;

import java.util.List;
import java.util.Map;

import uo.ri.business.CashService;
import uo.ri.business.dto.BreakdownDto;
import uo.ri.business.dto.CardDto;
import uo.ri.business.dto.CashDto;
import uo.ri.business.dto.InvoiceDto;
import uo.ri.business.dto.PaymentMeanDto;
import uo.ri.business.dto.VoucherDto;
import uo.ri.business.impl.cash.AddCash;
import uo.ri.business.impl.cash.CreateInvoiceFor;
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
	public InvoiceDto findInvoice(Long numeroInvoiceDto)
			throws BusinessException {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PaymentMeanDto> findPaymentMeansForInvoice(Long idFactura)
			throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InvoiceDto settleInvoice(Long idFactura, Map<Long, Double> cargos)
			throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addCash(CashDto p) {
		return executor.execute(new AddCash(p));
	}

	@Override
	public void addCardPaymentMean(CardDto card) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void addVoucherPaymentMean(VoucherDto voucher)
			throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deletePaymentMean(Long id) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<PaymentMeanDto> findPaymentMeansByClientId(Long id)
			throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
