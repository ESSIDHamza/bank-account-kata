package fr.hamzaessid.bankaccountkata.service;

import java.math.BigDecimal;

import fr.hamzaessid.bankaccountkata.entity.Account;
import fr.hamzaessid.bankaccountkata.entity.Customer;
import fr.hamzaessid.bankaccountkata.exception.AccountInTheRedException;
import fr.hamzaessid.bankaccountkata.exception.NegativeAmountException;

/**
 * This interface represents the business logic.
 *
 * @author ESSID, Hamza
 * @since 0.0.1
 */
public interface BankAccountService {

	void deposit(final Customer customer, final Account account, final BigDecimal amount)
			throws NegativeAmountException;

	void withdraw(final Customer customer, final Account account, final BigDecimal amount)
			throws NegativeAmountException, AccountInTheRedException;

}
