package com.pinguela.retroworld.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {
	
	public static final Date getDate(int ano, int mes, int dia) {
		Calendar c = new GregorianCalendar(ano, mes, dia);
		return c.getTime();
	}

}
