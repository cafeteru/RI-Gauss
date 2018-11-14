package uo.ri.model;

public class Association {

	public static class Poseer {

		public static void link(Cliente cliente, Vehiculo vehiculo) {
			vehiculo._setCliente(cliente);
			cliente._getVehiculos().add(vehiculo);
		}

		public static void unlink(Cliente cliente, Vehiculo vehiculo) {
			cliente._getVehiculos().remove(vehiculo);
			vehiculo._setCliente(null);
		}
	}

	public static class Clasificar {

		public static void link(TipoVehiculo tipoVehiculo, Vehiculo vehiculo) {
			vehiculo._setTipoVehiculo(tipoVehiculo);
			tipoVehiculo._getTiposVehiculo().add(vehiculo);
		}

		public static void unlink(TipoVehiculo tipoVehiculo,
				Vehiculo vehiculo) {
			tipoVehiculo._getTiposVehiculo().remove(vehiculo);
			vehiculo._setTipoVehiculo(null);
		}
	}

	public static class Pagar {
		public static void link(MedioPago mp, Cliente cliente) {
			mp._setCliente(cliente);
			cliente._getMedioPago().add(mp);
		}

		public static void link(Cliente cliente, MedioPago mp) {
			link(mp, cliente);
		}

		public static void unlink(MedioPago mp, Cliente cliente) {
			cliente._getMedioPago().remove(mp);
			mp._setCliente(null);
		}

		public static void unlink(Cliente cliente, MedioPago mp) {
			unlink(mp, cliente);
		}
	}

	public static class Averiar {

		public static void link(Vehiculo vehiculo, Averia averia) {
			averia._setVehiculo(vehiculo);
			vehiculo._getAverias().add(averia);

		}

		public static void unlink(Vehiculo vehiculo, Averia averia) {
			vehiculo._getAverias().remove(averia);
			averia._setVehiculo(null);

		}
	}

	public static class Facturar {

		public static void link(Factura factura, Averia averia) {
			averia._setFactura(factura);
			factura._getAverias().add(averia);
		}

		public static void unlink(Factura factura, Averia averia) {
			factura._getAverias().remove(averia);
			averia._setFactura(null);
		}

	}

	public static class Cargar {
		public static void link(Factura factura, Cargo cargo,
				MedioPago medioPago) {
			cargo._setFactura(factura);
			cargo._setMedioPago(medioPago);

			factura._getCargos().add(cargo);
			medioPago._getCargos().add(cargo);

		}

		public static void unlink(Cargo cargo) {
			cargo.getFactura()._getCargos().remove(cargo);
			cargo.getMedioPago()._getCargos().remove(cargo);

			cargo._setFactura(null);
			cargo._setMedioPago(null);

		}
	}

	public static class Asignar {

		public static void link(Mecanico mecanico, Averia averia) {
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
				Mecanico mecanico) {
			intervencion._setAveria(averia);
			intervencion._setMecanico(mecanico);

			averia._getIntervenciones().add(intervencion);
			mecanico._getIntervenciones().add(intervencion);

		}

		public static void unlink(Intervencion intervencion) {

			Averia averia = intervencion.getAveria();
			Mecanico mecanico = intervencion.getMecanico();

			averia._getIntervenciones().remove(intervencion);
			mecanico._getIntervenciones().remove(intervencion);

			intervencion._setAveria(null);
			intervencion._setMecanico(null);

		}
	}

	public static class Sustituir {

		public static void link(Intervencion intervencion,
				Sustitucion sustitucion, Repuesto repuesto) {
			sustitucion._setIntervencion(intervencion);
			sustitucion._setRepuesto(repuesto);

			repuesto._getSustituciones().add(sustitucion);
			intervencion._getSustituciones().add(sustitucion);
		}

		public static void unlink(Sustitucion sustitucion) {
			sustitucion.getIntervencion()._getSustituciones()
					.remove(sustitucion);
			sustitucion.getRepuesto()._getSustituciones().remove(sustitucion);

			sustitucion._setIntervencion(null);
			sustitucion._setRepuesto(null);
		}
	}

	public static class Perceive {

		public static void link(Contract contract, Payroll payroll) {
			contract._getPayrolls().add(payroll);
			payroll._setContract(contract);
		}

		public static void unlink(Contract contract, Payroll payroll) {
			contract._getPayrolls().remove(payroll);
			payroll._setContract(null);
		}
	}

	public static class Link {

		public static void link(Mecanico mecanico, Contract contract) {
			mecanico._getContracts().add(contract);
			contract._setMechanic(mecanico);
		}

		public static void unlink(Mecanico mecanico, Contract contract) {
			mecanico._getContracts().remove(contract);
			contract._setMechanic(null);
		}
	}

	public static class Categorize {

		public static void link(Contract contract,
				ContractCategory contractCategory) {
			contract._setContractCategory(contractCategory);
			contractCategory._getContracts().add(contract);
		}

		public static void unlink(Contract contract,
				ContractCategory contractCategory) {
			contract._setContractCategory(null);
			contractCategory._getContracts().remove(contract);
		}
	}

	public static class Typefy {

		public static void link(Contract contract, ContractType contractType) {
			contract._setContractType(contractType);
			contractType._getContract().add(contract);
		}

		public static void unlink(Contract contract,
				ContractType contractType) {
			contract._setContractType(null);
			contractType._getContract().remove(contract);
		}
	}

}
