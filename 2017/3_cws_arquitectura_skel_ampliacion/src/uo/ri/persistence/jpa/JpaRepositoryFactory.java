package uo.ri.persistence.jpa;

import uo.ri.business.repository.AveriaRepository;
import uo.ri.business.repository.CargoRepository;
import uo.ri.business.repository.ClienteRepository;
import uo.ri.business.repository.FacturaRepository;
import uo.ri.business.repository.IntervencionRepository;
import uo.ri.business.repository.MecanicoRepository;
import uo.ri.business.repository.MedioPagoRepository;
import uo.ri.business.repository.RecomendacionRepository;
import uo.ri.business.repository.RepositoryFactory;
import uo.ri.business.repository.RepuestoRepository;
import uo.ri.business.repository.SustitucionRepository;
import uo.ri.business.repository.TipoVehiculoRepository;
import uo.ri.business.repository.VehiculoRepository;

public class JpaRepositoryFactory implements RepositoryFactory {

	@Override
	public VehiculoRepository forVehiculo() {
		return new VehiculoJpaRepository();
	}

	public TipoVehiculoRepository forTipo() {
		return new TipoVehiculoJpaRepository();
	}

	@Override
	public MecanicoRepository forMechanic() {
		return new MechanicJpaRepository();
	}

	@Override
	public AveriaRepository forAveria() {
		return new AveriaJpaRepository();
	}

	@Override
	public MedioPagoRepository forMedioPago() {
		return new MedioPagoJpaRepository();
	}

	@Override
	public FacturaRepository forFactura() {
		return new FacturaJpaRepository();
	}

	@Override
	public ClienteRepository forCliente() {
		return new ClienteJpaRepository();
	}

	@Override
	public RepuestoRepository forRepuesto() {
		return new RepuestoJpaRepository();
	}

	@Override
	public RecomendacionRepository forRecomendacion() {
		return new RecomendacionJpaRepository();
	}

	@Override
	public CargoRepository forCargo() {
		return new CargoJpaRepository();
	}

	public IntervencionRepository forIntervencion() {
		return new IntervencionJpaRepository();
	}

	public SustitucionRepository forSustitucion() {
		return new SustitucionJpaRepository();
	}

}
