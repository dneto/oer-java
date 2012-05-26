package org.openexchangerates.oerjava.exceptions;

/**
 * 
 * @author Demétrio Menezes Neto
 */
public class UnavailableExchangeRateException extends Exception {

	private static final long serialVersionUID = 1L;

	public UnavailableExchangeRateException(String msg) {
		super(msg);
	}

}
