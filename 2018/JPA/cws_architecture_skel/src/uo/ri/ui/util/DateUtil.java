package uo.ri.ui.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {
	public static int getNumMonths(Date startDate, Date endDate) {
		Calendar fin = new GregorianCalendar();
		Calendar inicio = new GregorianCalendar();
		fin.setTime(endDate);
		inicio.setTime(startDate);
		int difA = fin.get(Calendar.YEAR) - inicio.get(Calendar.YEAR);
		return difA * 12 + fin.get(Calendar.MONTH) - inicio.get(Calendar.MONTH);
	}
}
