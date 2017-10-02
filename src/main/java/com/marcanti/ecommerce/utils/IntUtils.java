package com.marcanti.ecommerce.utils;


public class IntUtils {

	/**
	 * The default {@code int} value used when necessary.
	 */
	public static final int DEFAULT = 0;

	public static int increment( final Integer value) {
		return increment(value, 1);
	}

	public static int increment( final Integer value, int nbre) {
		return (value == null ? DEFAULT + nbre : value + nbre);
	}

}
