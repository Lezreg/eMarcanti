package com.marcanti.ecommerce.exception;

/**
 * 
 * @author rlezreg
 *
 */
public class CommandeGroupeeNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9055720968347434432L;

	/**
	 * Basic constructor to indicate a simple error message without stacktrace
	 *
	 * @param message
	 *            message to log
	 */
	public CommandeGroupeeNotFoundException(String message) {
		super(message);
	}

	/**
	 * Constructor used to encapsulate a previously thrown exception. A generic
	 * message is used.
	 *
	 * @param cause
	 *            the originating exception
	 */
	public CommandeGroupeeNotFoundException(Throwable cause) {
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
	public CommandeGroupeeNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
