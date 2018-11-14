package uo.ri.persistence.jpa;

import java.util.List;

import uo.ri.business.repository.MecanicoRepository;
import uo.ri.model.Mecanico;
import uo.ri.persistence.jpa.util.Jpa;

public class MechanicJpaRepository implements MecanicoRepository {

	@Override
	public void add(Mecanico t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(Mecanico t) {
		// TODO Auto-generated method stub

	}

	@Override
	public Mecanico findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Mecanico> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mecanico findByDni(String dni) {
		return Jpa.getManager()
				.createNamedQuery("Mecanico.findByDni", Mecanico.class)
				.setParameter(1, dni).getResultList().stream().findFirst()
				.orElse(null);
	}

}
