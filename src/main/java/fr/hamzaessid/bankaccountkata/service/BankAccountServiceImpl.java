package fr.hamzaessid.bankaccountkata.service;

import java.math.BigDecimal;
import java.util.Objects;

import fr.hamzaessid.bankaccountkata.entity.Account;
import fr.hamzaessid.bankaccountkata.entity.Customer;
import fr.hamzaessid.bankaccountkata.exception.AccountInTheRedException;
import fr.hamzaessid.bankaccountkata.exception.NegativeAmountException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BankAccountServiceImpl implements BankAccountService {

	public static final BigDecimal MINIMUM_BALANCE_WHEN_ACCOUNT_IN_THE_RED = BigDecimal.valueOf(-150);

	@Override
	public void deposit(Customer customer, Account account, BigDecimal amount) throws NegativeAmountException {
		if (amount.compareTo(BigDecimal.ZERO) == -1) {
			throw new NegativeAmountException("The amount cannot be negative");
		}

		if (Objects.isNull(account.getBalance())) {
			account.setBalance(amount);
		} else {
			account.setBalance(account.getBalance().add(amount));
		}
		/**
		 * equivalent in Java 9 or higher to:
		 * Optional.ofNullable(account.getBalance()).ifPresentOrElse( balance ->
		 * account.setBalance(balance.add(amount)), () ->
		 * account.setBalance(amount) );
		 */

		log.info("Customer #{} has deposited {} into his account #{}", customer.getCustomerId(), amount,
				account.getAccountId());
	}

	@Override
	public void withdraw(Customer customer, Account account, BigDecimal amount)
			throws NegativeAmountException, AccountInTheRedException {
	}

}
