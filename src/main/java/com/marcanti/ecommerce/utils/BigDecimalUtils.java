package com.marcanti.ecommerce.utils;

import java.math.BigDecimal;

import javax.annotation.Nullable;

public class BigDecimalUtils {

	public static final BigDecimal DEFAULT = BigDecimal.ZERO;

	public static BigDecimal sum(@Nullable final BigDecimal value, BigDecimal nbre) {
		return (value == null ? DEFAULT.add(nbre) : value.add(nbre));
	}

}
