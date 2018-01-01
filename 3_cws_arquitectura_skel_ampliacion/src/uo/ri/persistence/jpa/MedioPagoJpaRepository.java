package uo.ri.persistence.jpa;

import java.util.List;

import uo.ri.business.repository.MedioPagoRepository;
import uo.ri.model.*;
import uo.ri.persistence.jpa.util.BaseRepository;
import uo.ri.persistence.jpa.util.Jpa;

public class MedioPagoJpaRepository extends BaseRepository<MedioPago>
		implements MedioPagoRepository {

	@Override
	public List<MedioPago> findByClientId(Long id) {
		return findPaymentMeansByClientId(id);
	}

	@Override
	public List<MedioPago> findPaymentMeansByClientId(Long id) {
		return Jpa.getManager()
				.createNamedQuery("MedioPago.findById", MedioPago.class)
				.setParameter(1, id).getResultList();
	}

	@Override
	public List<MedioPago> findPaymentMeansByInvoiceId(Long idFactura) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] findAggregateVoucherDataByClientId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TarjetaCredito findCreditCardByNumber(String pan) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Bono> findVouchersByClientId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bono findVoucherByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

}
