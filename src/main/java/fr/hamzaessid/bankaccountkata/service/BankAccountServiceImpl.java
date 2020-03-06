package fr.hamzaessid.bankaccountkata.service;

import java.math.BigDecimal;

import fr.hamzaessid.bankaccountkata.entity.Account;
import fr.hamzaessid.bankaccountkata.entity.Customer;
import fr.hamzaessid.bankaccountkata.exception.NegativeAmountException;

public class BankAccountServiceImpl implements BankAccountService {

	@Override
	public void deposit(Customer customer, Account account, BigDecimal amount) throws NegativeAmountException {
	}

}
