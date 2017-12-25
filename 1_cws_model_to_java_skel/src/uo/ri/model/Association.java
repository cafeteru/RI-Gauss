package uo.ri.model;

import uo.ri.model.exception.BusinessException;
import uo.ri.model.util.Comprobador;

public class Association {

	public static class Poseer {
		public static void link(Cliente cliente, Vehiculo vehiculo)
				throws BusinessException {
			Comprobador.notNull(cliente);
			Comprobador.notNull(vehiculo);
			vehiculo._setCliente(cliente);
			cliente._getVehiculos().add(vehiculo);
		}

		public static void unlink(Cliente cliente, Vehiculo vehiculo)
				throws BusinessException {
			Comprobador.notNull(cliente);
			Comprobador.notNull(vehiculo);
			cliente._getVehiculos().remove(vehiculo);
			vehiculo._setCliente(null);
		}
	}

	public static class Clasificar {
		public static void link(TipoVehiculo tipoVehiculo, Vehiculo vehiculo)
				throws BusinessException {
			Comprobador.notNull(tipoVehiculo);
			Comprobador.notNull(vehiculo);
			vehiculo._setTipo(tipoVehiculo);
			tipoVehiculo._getVehiculos().add(vehiculo);
		}

		public static void unlink(TipoVehiculo tipoVehiculo, Vehiculo vehiculo)
				throws BusinessException {
			Comprobador.notNull(tipoVehiculo);
			Comprobador.notNull(vehiculo);
			tipoVehiculo._getVehiculos().remove(vehiculo);
			vehiculo._setTipo(null);
		}
	}

	public static class Pagar {
		public static void link(MedioPago medioPago, Cliente cliente)
				throws BusinessException {
			Comprobador.notNull(medioPago);
			Comprobador.notNull(cliente);
			medioPago._setCliente(cliente);
			cliente._getMediosPago().add(medioPago);
		}

		public static void unlink(Cliente cliente, MedioPago medioPago)
				throws BusinessException {
			Comprobador.notNull(medioPago);
			Comprobador.notNull(cliente);
			cliente._getMediosPago().remove(medioPago);
			medioPago._setCliente(null);
		}
	}

	public static class Averiar {
		public static void link(Vehiculo vehiculo, Averia averia)
				throws BusinessException {
			Comprobador.notNull(vehiculo);
			Comprobador.notNull(averia);
			averia._setVehiculo(vehiculo);
			vehiculo._getAverias().add(averia);
		}

		public static void unlink(Vehiculo vehiculo, Averia averia)
				throws BusinessException {
			Comprobador.notNull(vehiculo);
			Comprobador.notNull(averia);
			vehiculo._getAverias().remove(averia);
			averia._setVehiculo(null);
		}
	}

	public static class Facturar {
		public static void link(Factura factura, Averia averia)
				throws BusinessException {
			Comprobador.notNull(factura);
			Comprobador.notNull(averia);
			averia._setFactura(factura);
			factura._getAverias().add(averia);
		}

		public static void unlink(Factura factura, Averia averia)
				throws BusinessException {
			Comprobador.notNull(factura);
			Comprobador.notNull(averia);
			factura._getAverias().remove(averia);
			averia._setFactura(null);
		}
	}

	public static class Cargar {
		public static void link(MedioPago medioPago, Cargo cargo,
				Factura factura) throws BusinessException {
			Comprobador.notNull(factura);
			Comprobador.notNull(cargo);
			Comprobador.notNull(medioPago);
			cargo._setMedioPago(medioPago);
			cargo._setFactura(factura);
			medioPago._getCargos().add(cargo);
			factura._getCargos().add(cargo);
		}

		public static void unlink(Cargo cargo) throws BusinessException {
			Comprobador.notNull(cargo);
			cargo.getFactura()._getCargos().remove(cargo);
			cargo.getMedioPago()._getCargos().remove(cargo);
			cargo._setFactura(null);
			cargo._setMedioPago(null);
		}
	}

	public static class Asignar {
		public static void link(Mecanico mecanico, Averia averia)
				throws BusinessException {
			Comprobador.notNull(mecanico);
			Comprobador.notNull(averia);
			averia._setMecanico(mecanico);
			mecanico._getAverias().add(averia);
		}

		public static void unlink(Mecanico mecanico, Averia averia) {
			mecanico._getAverias().remove(averia);
			averia._setMecanico(null);
		}
	}

	public static class Intervenir {
		public static void link(Averia averia, Intervencion intervencion,
				Mecanico mecanico) throws BusinessException {
			Comprobador.notNull(averia);
			Comprobador.notNull(intervencion);
			Comprobador.notNull(mecanico);
			intervencion._setAveria(averia);
			intervencion._setMecanico(mecanico);
			averia._getIntervenciones().add(intervencion);
			mecanico._getIntervenciones().add(intervencion);
		}

		public static void unlink(Intervencion intervencion)
				throws BusinessException {
			Comprobador.notNull(intervencion);
			intervencion.getAveria()._getIntervenciones().remove(intervencion);
			intervencion.getMecanico()._getIntervenciones()
					.remove(intervencion);
			intervencion._setAveria(null);
			intervencion._setMecanico(null);
		}
	}

	public static class Sustituir {
		public static void link(Repuesto repuesto, Sustitucion sustitucion,
				Intervencion intervencion) throws BusinessException {
			Comprobador.notNull(repuesto);
			Comprobador.notNull(intervencion);
			Comprobador.notNull(sustitucion);
			sustitucion._setRepuesto(repuesto);
			sustitucion._setIntervencion(intervencion);
			repuesto._getSustituciones().add(sustitucion);
			intervencion._getSustituciones().add(sustitucion);
		}

		public static void unlink(Sustitucion sustitucion)
				throws BusinessException {
			Comprobador.notNull(sustitucion);
			sustitucion.getRepuesto()._getSustituciones().remove(sustitucion);
			sustitucion.getIntervencion()._getSustituciones()
					.remove(sustitucion);
			sustitucion._setRepuesto(null);
			sustitucion._setIntervencion(null);
		}
	}

}
