package com.marcanti.ecommerce.utils;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

	public static String getMonthName(final int month, final Locale locale) {
		return DateFormatSymbols.getInstance(locale).getMonths()[month];
	}

	public static int getMonth(final Date date) {
		final Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.MONTH);
	}

	public static int getDay(final Date date) {
		final Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_MONTH);
	}

	public static String getStringDay(final Date date) {
		SimpleDateFormat formatNowDay = new SimpleDateFormat("dd");
		return formatNowDay.format(date);
	}

	public static int getYear(final Date date) {
		final Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.YEAR);
	}
}
