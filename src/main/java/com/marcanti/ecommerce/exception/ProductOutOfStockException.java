package com.marcanti.ecommerce.exception;

public class ProductOutOfStockException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Basic constructor to indicate a simple error message without stacktrace
	 *
	 * @param message
	 *            message to log
	 */
	public ProductOutOfStockException(String message) {
		super(message);
	}

	/**
	 * Constructor used to encapsulate a previously thrown exception. A generic
	 * message is used.
	 *
	 * @param cause
	 *            the originating exception
	 */
	public ProductOutOfStockException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructor used to encapsulate a previously thrown exception but with a
	 * custom meaningful message
	 *
	 * @param message
	 *            the message to log throw threw
	 * @param cause
	 *            the originating exception
	 */
	public ProductOutOfStockException(String message, Throwable cause) {
		super(message, cause);
	}

}
