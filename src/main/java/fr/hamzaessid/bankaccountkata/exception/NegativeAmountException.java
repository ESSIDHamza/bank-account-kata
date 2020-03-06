package fr.hamzaessid.bankaccountkata.exception;

/**
 * This exception is thrown when the operation amount is negative.
 * 
 * @author ESSID, Hamza
 * @since 0.0.1
 */
@SuppressWarnings("serial")
public class NegativeAmountException extends Exception {

	public NegativeAmountException(final String message) {
		super(message);
	}

}
