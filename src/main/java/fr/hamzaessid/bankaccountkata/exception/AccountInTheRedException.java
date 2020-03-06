package fr.hamzaessid.bankaccountkata.exception;

/**
 * This exception is thrown when the customer wants to withdraw a certain amount
 * but lets his account's balance less than what is allowed.
 * 
 * @author ESSID, Hamza
 * @since 0.0.1
 */
@SuppressWarnings("serial")
public class AccountInTheRedException extends Exception {

	public AccountInTheRedException(final String message) {
		super(message);
	}

}
