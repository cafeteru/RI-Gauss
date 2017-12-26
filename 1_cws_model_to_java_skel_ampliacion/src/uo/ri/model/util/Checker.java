package uo.ri.model.util;

import java.util.Date;

import uo.ri.model.exception.BusinessException;

public class Checker {
	public static String checkString(String cadena, String parametro)
			throws BusinessException {
		if (cadena != null && "" != cadena)
			return cadena;
		throw new BusinessException(parametro + " no valido.");
	}

	public static Date notNullDate(Date date) throws BusinessException {
		if (date != null)
			return date;
		throw new BusinessException("Fecha nula");
	}

	public static void notNull(Object object) throws BusinessException {
		if (object == null)
			throw new BusinessException("Parámetro nulo");
	}

	public static Long positiveNumber(Long numero) throws BusinessException {
		if (numero >= 0)
			return numero;
		throw new BusinessException("Número no válido");
	}

}
