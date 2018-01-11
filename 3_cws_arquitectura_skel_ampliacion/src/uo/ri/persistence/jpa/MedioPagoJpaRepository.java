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
		return null;
	}

	@Override
	public Object[] findAggregateVoucherDataByClientId(Long id) {
		return (Object[]) Jpa.getManager()
				.createNamedQuery("Bono.resumenByClienteId", Object.class)
				.setParameter(1, id).getSingleResult();
	}

	@Override
	public TarjetaCredito findCreditCardByNumber(String pan) {
		return Jpa.getManager()
				.createNamedQuery("Tarjeta.findByNumber", TarjetaCredito.class)
				.setParameter(1, pan).getResultList().stream().findFirst()
				.orElse(null);
	}

	@Override
	public List<Bono> findVouchersByClientId(Long id) {
		return Jpa.getManager().createNamedQuery("Bono.findById", Bono.class)
				.setParameter(1, id).getResultList();
	}

	@Override
	public Bono findVoucherByCode(String code) {
		return Jpa.getManager().createNamedQuery("Bono.findByCode", Bono.class)
				.setParameter(1, code).getResultList().stream().findFirst()
				.orElse(null);
	}

}
