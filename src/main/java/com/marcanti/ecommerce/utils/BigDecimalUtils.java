package com.marcanti.ecommerce.utils;

import java.math.BigDecimal;

public class BigDecimalUtils {

	public static final BigDecimal DEFAULT = BigDecimal.ZERO;

	public static BigDecimal sum( final BigDecimal value, BigDecimal nbre) {
		return (value == null ? DEFAULT.add(nbre) : value.add(nbre));
	}

}
