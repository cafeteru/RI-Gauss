package uo.ri.util.exception;

import java.util.Date;

public class Check {

	public static void isNull(Object o, String errorMsg)
			throws BusinessException {
		isTrue(o == null, errorMsg);
	}

	public static void isNull(Object o) throws BusinessException {
		isTrue(o == null, o.getClass().getName() + " cannot be null here");
	}

	public static void isNotNull(Object o, String errorMsg)
			throws BusinessException {
		isTrue(o != null, errorMsg);
	}

	public static void isNotNull(Object o) throws BusinessException {
		isTrue(o != null, o.getClass().getName() + " cannot be null here");
	}

	public static void isFalse(boolean condition) throws BusinessException {
		isTrue(!condition, "Invalid assertion");
	}

	public static void isFalse(boolean condition, String errorMsg)
			throws BusinessException {
		isTrue(!condition, errorMsg);
	}

	public static void isTrue(boolean condition) throws BusinessException {
		isTrue(condition, "Invalid assertion");
	}

	public static void isTrue(boolean condition, String errorMsg)
			throws BusinessException {
		if (condition == true)
			return;
		throw new BusinessException(errorMsg);
	}

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

	public static void noEsNull(Object object) throws BusinessException {
		if (object == null)
			throw new BusinessException("Parámetro nulo");
	}

	public static void esNull(Object object) throws BusinessException {
		if (object != null)
			throw new BusinessException("Parámetro nulo");
	}

	public static Long positiveNumber(Long numero) throws BusinessException {
		if (numero >= 0)
			return numero;
		throw new BusinessException("Número no válido");
	}
}
