package com.marcanti.ecommerce.utils;

import javax.annotation.Nullable;

/**
 * This class provides operations on {@code short} primitives and {@link Short}
 * objects.
 * <p>
 * This class tries to handle {@code null} input gracefully. An exception will
 * not be thrown for a {@code null} input. Each method documents its behavior in
 * more detail.
 * </p>
 * <p>
 * This class also handle the conversion from {@code short} values or
 * {@link Short} objects to common types.
 * </p>
 *
 */
public class ShortUtils {

	/**
	 * The default {@code short} value used when necessary.
	 */
	public static final short DEFAULT = (short) 0;
	public static final short ONE = incrementShort(DEFAULT);

	/**
	 * The maximum value of an unsigned short.
	 */
	public static final int UNSIGNED_MAX = 0xFFFF;

	public static short toPrimitive(@Nullable final Short value) {
		return (value == null ? DEFAULT : value.shortValue());
	}

	public static short toPrimitive(@Nullable final Short value, final short defaultValue) {
		return (value == null ? defaultValue : value.shortValue());
	}

	public static boolean toBoolean(final short value) {
		return (value != 0);
	}

	public static boolean toBoolean(@Nullable final Short value) {
		return (value == null ? false : toBoolean(value.shortValue()));
	}

	public static boolean toBoolean(@Nullable final Short value, final boolean defaultValue) {
		return (value == null ? defaultValue : toBoolean(value.shortValue()));
	}

	public static Boolean toBooleanObject(final short value) {
		return Boolean.valueOf(toBoolean(value));
	}

	public static Boolean toBooleanObject(@Nullable final Short value) {
		return (value == null ? null : toBooleanObject(value.shortValue()));
	}

	public static Boolean toBooleanObject(@Nullable final Short value, @Nullable final Boolean defaultValue) {
		return (value == null ? defaultValue : toBooleanObject(value.shortValue()));
	}

	public static char toChar(final short value) {
		return (char) value;
	}

	public static char toChar(@Nullable final Short value, final char defaultValue) {
		return (value == null ? defaultValue : toChar(value.shortValue()));
	}

	public static Character toCharObject(final short value) {
		return Character.valueOf(toChar(value));
	}

	public static Character toCharObject(@Nullable final Short value) {
		return (value == null ? null : toCharObject(value.shortValue()));
	}

	public static Character toCharObject(@Nullable final Short value, @Nullable final Character defaultValue) {
		return (value == null ? defaultValue : toCharObject(value.shortValue()));
	}

	public static byte toByte(final short value) {
		return (byte) value;
	}



	public static byte toByte(@Nullable final Short value, final byte defaultValue) {
		return (value == null ? defaultValue : toByte(value.shortValue()));
	}

	public static Byte toByteObject(final short value) {
		return Byte.valueOf(toByte(value));
	}

	public static Byte toByteObject(@Nullable final Short value) {
		return (value == null ? null : toByteObject(value.shortValue()));
	}

	public static Byte toByteObject(@Nullable final Short value, @Nullable final Byte defaultValue) {
		return (value == null ? defaultValue : toByteObject(value.shortValue()));
	}

	public static int toInt(final short value) {
		return value;
	}

	public static int toInt(@Nullable final Short value) {
		return (value == null ? 0 : toInt(value.shortValue()));
	}

	public static short incrementShort(@Nullable final Short value) {
		return toShort(increment(value, 1));
	}

	public static short incrementShort(@Nullable final Short value, final int nbre) {
		return toShort(increment(value, nbre));
	}

	public static int increment(@Nullable final Short value) {
		return increment(value, 1);
	}

	public static int increment(@Nullable final Short value, int nbre) {
		return (value == null ? 0 + nbre : toInt(value.shortValue()) + nbre);
	}

	public static int toInt(@Nullable final Short value, final int defaultValue) {
		return (value == null ? defaultValue : toInt(value.shortValue()));
	}

	public static Integer toIntObject(final short value) {
		return Integer.valueOf(toInt(value));
	}

	public static Integer toIntObject(@Nullable final Short value) {
		return (value == null ? null : toIntObject(value.shortValue()));
	}

	public static Integer toIntObject(@Nullable final Short value, @Nullable final Integer defaultValue) {
		return (value == null ? defaultValue : toIntObject(value.shortValue()));
	}

	public static long toLong(final short value) {
		return value;
	}

	public static short toShort(final int value) {
		return (short) value;
	}

	public static short toShort(@Nullable final Integer value) {
		return (value == null ? 0 : toShort(value.intValue()));
	}
	public static short toShort(@Nullable final Integer value, final short defaultValue) {
		return (value == null ? defaultValue : toShort(value.intValue()));
	}

	public static Short toShortObject(final int value) {
		return Short.valueOf(toShort(value));
	}

	public static Short toShortObject(@Nullable final Integer value) {
		return (value == null ? null : toShortObject(value.intValue()));
	}


	public static long toLong(@Nullable final Short value, final long defaultValue) {
		return (value == null ? defaultValue : toLong(value.shortValue()));
	}

	public static Long toLongObject(final short value) {
		return Long.valueOf(toLong(value));
	}

	public static Long toLongObject(@Nullable final Short value) {
		return (value == null ? null : toLongObject(value.shortValue()));
	}

	public static Long toLongObject(@Nullable final Short value, @Nullable final Long defaultValue) {
		return (value == null ? defaultValue : toLongObject(value.shortValue()));
	}

	public static float toFloat(final short value) {
		return value;
	}



	public static float toFloat(@Nullable final Short value, final float defaultValue) {
		return (value == null ? defaultValue : toFloat(value.shortValue()));
	}

	public static Float toFloatObject(final short value) {
		return Float.valueOf(toFloat(value));
	}

	public static Float toFloatObject(@Nullable final Short value) {
		return (value == null ? null : toFloatObject(value.shortValue()));
	}

	public static Float toFloatObject(@Nullable final Short value, @Nullable final Float defaultValue) {
		return (value == null ? defaultValue : toFloatObject(value.shortValue()));
	}

	public static double toDouble(final short value) {
		return value;
	}

	public static double toDouble(@Nullable final Short value) {
		return (value == null ? 0 : toDouble(value.shortValue()));
	}

	public static double toDouble(@Nullable final Short value, final double defaultValue) {
		return (value == null ? defaultValue : toDouble(value.shortValue()));
	}

	public static Double toDoubleObject(final short value) {
		return Double.valueOf(toDouble(value));
	}

	public static Double toDoubleObject(@Nullable final Short value) {
		return (value == null ? null : toDoubleObject(value.shortValue()));
	}

	public static Double toDoubleObject(@Nullable final Short value, @Nullable final Double defaultValue) {
		return (value == null ? defaultValue : toDoubleObject(value.shortValue()));
	}

	public static String toString(final short value) {
		return Integer.toString(value, 10);
	}

	public static String toString(@Nullable final Short value) {
		return (value == null ? null : toString(value.shortValue()));
	}

	public static String toString(@Nullable final Short value, @Nullable final String defaultValue) {
		return (value == null ? defaultValue : toString(value.shortValue()));
	}


}