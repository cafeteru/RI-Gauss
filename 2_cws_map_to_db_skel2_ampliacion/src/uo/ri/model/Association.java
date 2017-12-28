package uo.ri.model;

import uo.ri.model.exception.BusinessException;
import uo.ri.model.util.Checker;

public class Association {

	public static class Poseer {
		public static void link(Cliente cliente, Vehiculo vehiculo)
				throws BusinessException {
			Checker.notNull(cliente);
			Checker.notNull(vehiculo);
			vehiculo._setCliente(cliente);
			cliente._getVehiculos().add(vehiculo);
		}

		public static void unlink(Cliente cliente, Vehiculo vehiculo)
				throws BusinessException {
			Checker.notNull(cliente);
			Checker.notNull(vehiculo);
			cliente._getVehiculos().remove(vehiculo);
			vehiculo._setCliente(null);
		}
	}

	public static class Clasificar {
		public static void link(TipoVehiculo tipoVehiculo, Vehiculo vehiculo)
				throws BusinessException {
			Checker.notNull(tipoVehiculo);
			Checker.notNull(vehiculo);
			vehiculo._setTipo(tipoVehiculo);
			tipoVehiculo._getVehiculos().add(vehiculo);
		}

		public static void unlink(TipoVehiculo tipoVehiculo, Vehiculo vehiculo)
				throws BusinessException {
			Checker.notNull(tipoVehiculo);
			Checker.notNull(vehiculo);
			tipoVehiculo._getVehiculos().remove(vehiculo);
			vehiculo._setTipo(null);
		}
	}

	public static class Pagar {
		public static void link(Cliente cliente, MedioPago medioPago)
				throws BusinessException {
			Checker.notNull(medioPago);
			Checker.notNull(cliente);
			medioPago._setCliente(cliente);
			cliente._getMediosPago().add(medioPago);
		}

		public static void unlink(Cliente cliente, MedioPago medioPago)
				throws BusinessException {
			Checker.notNull(medioPago);
			Checker.notNull(cliente);
			cliente._getMediosPago().remove(medioPago);
			medioPago._setCliente(null);
		}
	}

	public static class Averiar {
		public static void link(Vehiculo vehiculo, Averia averia)
				throws BusinessException {
			Checker.notNull(vehiculo);
			Checker.notNull(averia);
			averia._setVehiculo(vehiculo);
			vehiculo._getAverias().add(averia);
		}

		public static void unlink(Vehiculo vehiculo, Averia averia)
				throws BusinessException {
			Checker.notNull(vehiculo);
			Checker.notNull(averia);
			vehiculo._getAverias().remove(averia);
			averia._setVehiculo(null);
		}
	}

	public static class Facturar {
		public static void link(Factura factura, Averia averia)
				throws BusinessException {
			Checker.notNull(factura);
			Checker.notNull(averia);
			averia._setFactura(factura);
			factura._getAverias().add(averia);
		}

		public static void unlink(Factura factura, Averia averia)
				throws BusinessException {
			Checker.notNull(factura);
			Checker.notNull(averia);
			factura._getAverias().remove(averia);
			averia._setFactura(null);
		}
	}

	public static class Cargar {
		public static void link(MedioPago medioPago, Cargo cargo,
				Factura factura) throws BusinessException {
			Checker.notNull(factura);
			Checker.notNull(cargo);
			Checker.notNull(medioPago);
			cargo._setMedioPago(medioPago);
			cargo._setFactura(factura);
			medioPago._getCargos().add(cargo);
			factura._getCargos().add(cargo);
		}

		public static void unlink(Cargo cargo) throws BusinessException {
			Checker.notNull(cargo);
			cargo.getFactura()._getCargos().remove(cargo);
			cargo.getMedioPago()._getCargos().remove(cargo);
			cargo._setFactura(null);
			cargo._setMedioPago(null);
		}
	}

	public static class Asignar {
		public static void link(Mecanico mecanico, Averia averia)
				throws BusinessException {
			Checker.notNull(mecanico);
			Checker.notNull(averia);
			averia._setMecanico(mecanico);
			mecanico._getAverias().add(averia);
		}

		public static void unlink(Mecanico mecanico, Averia averia)
				throws BusinessException {
			Checker.notNull(mecanico);
			Checker.notNull(averia);
			mecanico._getAverias().remove(averia);
			averia._setMecanico(null);
		}
	}

	public static class Intervenir {
		public static void link(Averia averia, Intervencion intervencion,
				Mecanico mecanico) throws BusinessException {
			Checker.notNull(averia);
			Checker.notNull(intervencion);
			Checker.notNull(mecanico);
			intervencion._setAveria(averia);
			intervencion._setMecanico(mecanico);
			averia._getIntervenciones().add(intervencion);
			mecanico._getIntervenciones().add(intervencion);
		}

		public static void unlink(Intervencion intervencion)
				throws BusinessException {
			Checker.notNull(intervencion);
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
			Checker.notNull(repuesto);
			Checker.notNull(intervencion);
			Checker.notNull(sustitucion);
			sustitucion._setRepuesto(repuesto);
			sustitucion._setIntervencion(intervencion);
			repuesto._getSustituciones().add(sustitucion);
			intervencion._getSustituciones().add(sustitucion);
		}

		public static void unlink(Sustitucion sustitucion)
				throws BusinessException {
			Checker.notNull(sustitucion);
			sustitucion.getRepuesto()._getSustituciones().remove(sustitucion);
			sustitucion.getIntervencion()._getSustituciones()
					.remove(sustitucion);
			sustitucion._setRepuesto(null);
			sustitucion._setIntervencion(null);
		}
	}

	public static class Recomendar {
		public static void link(Cliente recomendador,
				Recomendacion recomendacion, Cliente recomendado)
				throws BusinessException {
			Checker.notNull(recomendado);
			Checker.notNull(recomendacion);
			Checker.notNull(recomendador);
			recomendacion._setRecomendador(recomendador);
			recomendacion._setRecomendado(recomendado);
			recomendador._getRecomendados().add(recomendacion);
			recomendado._setRecomendador(recomendacion);
		}

		public static void unlink(Recomendacion recomendacion)
				throws BusinessException {
			Checker.notNull(recomendacion);
			recomendacion.getRecomendador()._getRecomendados()
					.remove(recomendacion);
			recomendacion.getRecomendado()._setRecomendador(null);
			recomendacion._setRecomendado(null);
			recomendacion._setRecomendador(null);
		}
	}

}