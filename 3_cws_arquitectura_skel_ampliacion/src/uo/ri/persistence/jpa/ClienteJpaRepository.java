package uo.ri.persistence.jpa;

import java.util.List;

import uo.ri.business.repository.ClienteRepository;
import uo.ri.model.Cliente;
import uo.ri.persistence.jpa.util.BaseRepository;
import uo.ri.persistence.jpa.util.Jpa;

public class ClienteJpaRepository extends BaseRepository<Cliente>
		implements ClienteRepository {

	@Override
	public Cliente findByDni(String dni) {
		return Jpa.getManager()
				.createNamedQuery("Cliente.findByDni", Cliente.class)
				.setParameter(1, dni).getSingleResult();
	}

	@Override
	public List<Cliente> findWithRecomendations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cliente> findWithThreeUnusedBreakdowns() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cliente> findRecomendedBy(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
