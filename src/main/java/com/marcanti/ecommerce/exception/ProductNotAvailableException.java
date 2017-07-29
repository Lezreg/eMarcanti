package com.marcanti.ecommerce.exception;

public class ProductNotAvailableException extends Exception {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8609862298176962490L;

	/**
	 * Basic constructor to indicate a simple error message without stacktrace
	 *
	 * @param message
	 *            message to log
	 */
	public ProductNotAvailableException(String message) {
		super(message);
	}

	/**
	 * Constructor used to encapsulate a previously thrown exception. A generic
	 * message is used.
	 *
	 * @param cause
	 *            the originating exception
	 */
	public ProductNotAvailableException(Throwable cause) {
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
	public ProductNotAvailableException(String message, Throwable cause) {
		super(message, cause);
	}
}
