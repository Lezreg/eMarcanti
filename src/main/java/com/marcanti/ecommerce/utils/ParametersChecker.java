package com.marcanti.ecommerce.utils;


import com.google.common.base.Strings;

/**
 * Checker for Parameters <br>
 * <br>
 * Can be used for String (testing also emptiness) and for general Object.<br>
 * For null String only, use the special method.
 *
 *
 */
public final class ParametersChecker {

	private ParametersChecker() {
		// empty
	}

	/**
	 * Check if any parameter are null or empty and if so, throw an
	 * IllegalArgumentException
	 *
	 * @param errorMessage
	 * @param parameters
	 * @throws IllegalArgumentException
	 *             if null or empty
	 */
	public static final void checkParameter(String errorMessage, String... parameters) {
		if (parameters == null) {
			throw new IllegalArgumentException(errorMessage);
		}
		for (final String parameter : parameters) {
			if (Strings.isNullOrEmpty(parameter) || parameter.trim().isEmpty()) {
				throw new IllegalArgumentException(errorMessage);
			}
		}
	}

	/**
	 * Check if any parameter are null and if so, throw an
	 * IllegalArgumentException
	 *
	 * @param errorMessage
	 * @param parameters
	 * @throws IllegalArgumentException
	 *             if null
	 */
	public static final void checkParameterNullOnly(String errorMessage, String... parameters) {
		if (parameters == null) {
			throw new IllegalArgumentException(errorMessage);
		}
		for (final String parameter : parameters) {
			if (parameter == null) {
				throw new IllegalArgumentException(errorMessage);
			}
		}
	}

	/**
	 * Check if any parameter are null and if so, throw an
	 * IllegalArgumentException
	 *
	 * @param errorMessage
	 * @param parameters
	 * @throws IllegalArgumentException
	 *             if null
	 */
	public static final void checkParameter(String errorMessage, Object... parameters) {
		if (parameters == null) {
			throw new IllegalArgumentException(errorMessage);
		}
		for (final Object parameter : parameters) {
			if (parameter == null) {
				throw new IllegalArgumentException(errorMessage);
			}
		}
	}

	/**
	 * Check if an integer parameter is greater or equals to minValue
	 *
	 * @param name
	 *            name of the variable
	 * @param variable
	 * @param minValue
	 */
	public static final void checkValue(String name, long variable, long minValue) {
		if (variable < minValue) {
			throw new IllegalArgumentException("Parameter " + name + " is less than " + minValue);
		}
	}
}
