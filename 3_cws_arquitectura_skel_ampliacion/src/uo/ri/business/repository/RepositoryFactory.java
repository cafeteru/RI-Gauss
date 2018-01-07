package uo.ri.business.repository;

public interface RepositoryFactory {

	TipoVehiculoRepository forTipo();

	IntervencionRepository forIntervencion();

	SustitucionRepository forSustitucion();

	MecanicoRepository forMechanic();

	AveriaRepository forAveria();

	MedioPagoRepository forMedioPago();

	FacturaRepository forFactura();

	ClienteRepository forCliente();

	RepuestoRepository forRepuesto();

	RecomendacionRepository forRecomendacion();

	CargoRepository forCargo();

	VehiculoRepository forVehiculo();

}
