package com.marcanti.ecommerce.utils;

import javax.annotation.Nullable;

public class IntUtils {

	/**
	 * The default {@code int} value used when necessary.
	 */
	public static final int DEFAULT = 0;

	public static int increment(@Nullable final Integer value) {
		return increment(value, 1);
	}

	public static int increment(@Nullable final Integer value, int nbre) {
		return (value == null ? DEFAULT + nbre : value + nbre);
	}

}
