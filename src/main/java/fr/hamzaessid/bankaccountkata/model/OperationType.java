package fr.hamzaessid.bankaccountkata.model;

/**
 * This enumeration holds all operation types possible on an account.
 *
 * @author ESSID, Hamza
 * @since 0.0.1
 */
public enum OperationType {

	DEPOSIT("Deposit"), WITHDRAW("Withdraw");

	private String operationType;

	OperationType(final String operationType) {
		this.operationType = operationType;
	}

	@Override
	public String toString() {
		return this.operationType;
	}

}
