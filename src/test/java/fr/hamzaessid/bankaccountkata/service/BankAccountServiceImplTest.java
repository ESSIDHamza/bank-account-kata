package fr.hamzaessid.bankaccountkata.service;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import fr.hamzaessid.bankaccountkata.entity.Account;
import fr.hamzaessid.bankaccountkata.entity.Customer;
import fr.hamzaessid.bankaccountkata.exception.NegativeAmountException;

public class BankAccountServiceImplTest {

	private BankAccountService bankAccountService;

	@Before
	public void setUp() {
		this.bankAccountService = new BankAccountServiceImpl();
	}

	@Test
	public void testDeposit() throws NegativeAmountException {
		final BigDecimal expected = BigDecimal.valueOf(1500);
		// given an account with a balance equal to 1000
		final Account account = this.mockAccount();
		// when customer deposits 500
		this.bankAccountService.deposit(this.mockCustomer(), account, BigDecimal.valueOf(500));
		// then the current account's balance becomes equal to 1500
		assertEquals(expected, account.getBalance());
	}

	@Test(expected = NegativeAmountException.class)
	public void testDepositWithNegativeAmount() throws NegativeAmountException {
		// given an account
		// when customer deposits a negative amount
		// then a NegativeAmountException is thrown
		this.bankAccountService.deposit(this.mockCustomer(), this.mockAccount(), BigDecimal.valueOf(-500));
	}

	private Customer mockCustomer() {
		return Customer.builder()
				.customerId(1L)
				.firstName("Hamza")
				.lastName("ESSID")
				.dateOfBirth(LocalDate.of(1993, 12, 22))
				.job("Software Engineer")
				.account(this.mockAccount())
				.build();
	}

	private Account mockAccount() {
		return Account.builder()
				.accountId(1L)
				.balance(BigDecimal.valueOf(1000))
				.iban("FRXX XXXX ...")
				.holder(this.mockCustomer())
				.build();
	}

}
