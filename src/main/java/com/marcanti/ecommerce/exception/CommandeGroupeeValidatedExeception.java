package com.marcanti.ecommerce.exception;

public class CommandeGroupeeValidatedExeception extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3618856848558683124L;

	/**
	 * Basic constructor to indicate a simple error message without stacktrace
	 *
	 * @param message
	 *            message to log
	 */
	public CommandeGroupeeValidatedExeception(String message) {
		super(message);
	}

	/**
	 * Constructor used to encapsulate a previously thrown exception. A generic
	 * message is used.
	 *
	 * @param cause
	 *            the originating exception
	 */
	public CommandeGroupeeValidatedExeception(Throwable cause) {
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
	public CommandeGroupeeValidatedExeception(String message, Throwable cause) {
		super(message, cause);
	}
}
