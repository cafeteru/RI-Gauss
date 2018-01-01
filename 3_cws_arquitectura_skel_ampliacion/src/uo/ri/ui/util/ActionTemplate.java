package uo.ri.ui.util;

import uo.ri.util.exception.BusinessException;

public abstract class ActionTemplate implements Action {

	@Override
	public void execute() throws BusinessException {
		pedirDatos();
		procesarDatos();
		imprimirMensaje();
	}

	/**
	 * Método que pide datos al usuario.
	 *
	 */
	protected abstract void pedirDatos();

	/**
	 * Método que procesa los datos obtenidos del usuario.
	 * 
	 * @throws BusinessException
	 *             Excepción ocurrida durante la ejecución.
	 */
	protected abstract void procesarDatos() throws BusinessException;

	/**
	 * Método que un mensaje para el usuario.
	 *
	 */
	protected abstract void imprimirMensaje();
}
