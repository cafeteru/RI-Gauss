package uo.ri.persistence.jpa;

import java.util.List;

import uo.ri.business.repository.ClienteRepository;
import uo.ri.model.Cliente;
import uo.ri.persistence.jpa.util.BaseRepository;

public class ClienteJpaRepository extends BaseRepository<Cliente>
		implements ClienteRepository {

	@Override
	public Cliente findByDni(String dni) {
		// TODO Auto-generated method stub
		return null;
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
