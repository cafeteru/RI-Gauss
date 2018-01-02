package uo.ri.model;

import uo.ri.util.exception.BusinessException;
import uo.ri.util.exception.Check;

public class Association {

	public static class Poseer {
		public static void link(Cliente cliente, Vehiculo vehiculo)
				throws BusinessException {
			Check.noEsNull(cliente);
			Check.noEsNull(vehiculo);
			vehiculo._setCliente(cliente);
			cliente._getVehiculos().add(vehiculo);
		}

		public static void unlink(Cliente cliente, Vehiculo vehiculo)
				throws BusinessException {
			Check.noEsNull(cliente);
			Check.noEsNull(vehiculo);
			cliente._getVehiculos().remove(vehiculo);
			vehiculo._setCliente(null);
		}
	}

	public static class Clasificar {
		public static void link(TipoVehiculo tipoVehiculo, Vehiculo vehiculo)
				throws BusinessException {
			Check.noEsNull(tipoVehiculo);
			Check.noEsNull(vehiculo);
			vehiculo._setTipo(tipoVehiculo);
			tipoVehiculo._getVehiculos().add(vehiculo);
		}

		public static void unlink(TipoVehiculo tipoVehiculo, Vehiculo vehiculo)
				throws BusinessException {
			Check.noEsNull(tipoVehiculo);
			Check.noEsNull(vehiculo);
			tipoVehiculo._getVehiculos().remove(vehiculo);
			vehiculo._setTipo(null);
		}
	}

	public static class Pagar {
		public static void link(Cliente cliente, MedioPago medioPago)
				throws BusinessException {
			Check.noEsNull(medioPago);
			Check.noEsNull(cliente);
			medioPago._setCliente(cliente);
			cliente._getMediosPago().add(medioPago);
		}

		public static void unlink(Cliente cliente, MedioPago medioPago)
				throws BusinessException {
			Check.noEsNull(medioPago);
			Check.noEsNull(cliente);
			cliente._getMediosPago().remove(medioPago);
			medioPago._setCliente(null);
		}
	}

	public static class Averiar {
		public static void link(Vehiculo vehiculo, Averia averia)
				throws BusinessException {
			Check.noEsNull(vehiculo);
			Check.noEsNull(averia);
			averia._setVehiculo(vehiculo);
			vehiculo._getAverias().add(averia);
		}

		public static void unlink(Vehiculo vehiculo, Averia averia)
				throws BusinessException {
			Check.noEsNull(vehiculo);
			Check.noEsNull(averia);
			vehiculo._getAverias().remove(averia);
			averia._setVehiculo(null);
		}
	}

	public static class Facturar {
		public static void link(Factura factura, Averia averia)
				throws BusinessException {
			Check.noEsNull(factura);
			Check.noEsNull(averia);
			averia._setFactura(factura);
			factura._getAverias().add(averia);
		}

		public static void unlink(Factura factura, Averia averia)
				throws BusinessException {
			Check.noEsNull(factura);
			Check.noEsNull(averia);
			factura._getAverias().remove(averia);
			averia._setFactura(null);
		}
	}

	public static class Cargar {
		public static void link(MedioPago medioPago, Cargo cargo,
				Factura factura) throws BusinessException {
			Check.noEsNull(factura);
			Check.noEsNull(cargo);
			Check.noEsNull(medioPago);
			cargo._setMedioPago(medioPago);
			cargo._setFactura(factura);
			medioPago._getCargos().add(cargo);
			factura._getCargos().add(cargo);
		}

		public static void unlink(Cargo cargo) throws BusinessException {
			Check.noEsNull(cargo);
			cargo.getFactura()._getCargos().remove(cargo);
			cargo.getMedioPago()._getCargos().remove(cargo);
			cargo._setFactura(null);
			cargo._setMedioPago(null);
		}
	}

	public static class Asignar {
		public static void link(Mecanico mecanico, Averia averia)
				throws BusinessException {
			Check.noEsNull(mecanico);
			Check.noEsNull(averia);
			averia._setMecanico(mecanico);
			mecanico._getAverias().add(averia);
		}

		public static void unlink(Mecanico mecanico, Averia averia)
				throws BusinessException {
			Check.noEsNull(mecanico);
			Check.noEsNull(averia);
			mecanico._getAverias().remove(averia);
			averia._setMecanico(null);
		}
	}

	public static class Intervenir {
		public static void link(Averia averia, Intervencion intervencion,
				Mecanico mecanico) throws BusinessException {
			Check.noEsNull(averia);
			Check.noEsNull(intervencion);
			Check.noEsNull(mecanico);
			intervencion._setAveria(averia);
			intervencion._setMecanico(mecanico);
			averia._getIntervenciones().add(intervencion);
			mecanico._getIntervenciones().add(intervencion);
		}

		public static void unlink(Intervencion intervencion)
				throws BusinessException {
			Check.noEsNull(intervencion);
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
			Check.noEsNull(repuesto);
			Check.noEsNull(intervencion);
			Check.noEsNull(sustitucion);
			sustitucion._setRepuesto(repuesto);
			sustitucion._setIntervencion(intervencion);
			repuesto._getSustituciones().add(sustitucion);
			intervencion._getSustituciones().add(sustitucion);
		}

		public static void unlink(Sustitucion sustitucion)
				throws BusinessException {
			Check.noEsNull(sustitucion);
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
			Check.noEsNull(recomendado);
			Check.noEsNull(recomendacion);
			Check.noEsNull(recomendador);
			if (recomendado.equals(recomendador))
				throw new BusinessException("No pueden ser iguales");
			if (recomendado.getRecomendacionRecibida() != null)
				throw new BusinessException(
						"El cliente " + recomendado + " ya esta recomendado");
			recomendacion._setRecomendador(recomendador);
			recomendacion._setRecomendado(recomendado);
			recomendador._getRecomendados().add(recomendacion);
			recomendado._setRecomendador(recomendacion);
		}

		public static void unlink(Recomendacion recomendacion)
				throws BusinessException {
			Check.noEsNull(recomendacion);
			recomendacion.getRecomendador()._getRecomendados()
					.remove(recomendacion);
			recomendacion.getRecomendado()._setRecomendador(null);
			recomendacion._setRecomendado(null);
			recomendacion._setRecomendador(null);
		}
	}

}