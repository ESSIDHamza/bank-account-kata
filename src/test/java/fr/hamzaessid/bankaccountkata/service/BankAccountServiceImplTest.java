package fr.hamzaessid.bankaccountkata.service;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import fr.hamzaessid.bankaccountkata.entity.Account;
import fr.hamzaessid.bankaccountkata.entity.Customer;
import fr.hamzaessid.bankaccountkata.exception.AccountInTheRedException;
import fr.hamzaessid.bankaccountkata.exception.NegativeAmountException;
import fr.hamzaessid.bankaccountkata.util.HistoryHandler;

public class BankAccountServiceImplTest {

	private BankAccountService bankAccountService;

	private HistoryHandler historyHandler = Mockito.mock(HistoryHandler.class);

	@Before
	public void setUp() {
		// just change this mock bean into the real implementation
		// CsvHistoryHandlerImpl
		// in order to check the lines written in "bank_account_history.csv"
		this.bankAccountService = new BankAccountServiceImpl(this.historyHandler);
	}

	@Test
	public void testDeposit() throws NegativeAmountException {
		final BigDecimal expected = BigDecimal.valueOf(1500);
		when(this.historyHandler.save(any())).thenReturn(true);
		// given an account with a balance equal to 1000
		final Account account = this.mockAccount();
		// when customer deposits 500
		this.bankAccountService.deposit(this.mockCustomer(), account, BigDecimal.valueOf(500));
		// then the current account's balance becomes equal to 1500
		assertEquals(expected, account.getBalance());
		verify(this.historyHandler, times(1)).save(any());
	}

	@Test(expected = NegativeAmountException.class)
	public void testDepositWithNegativeAmount() throws NegativeAmountException {
		// given an account
		// when customer deposits a negative amount
		// then a NegativeAmountException is thrown
		this.bankAccountService.deposit(this.mockCustomer(), this.mockAccount(), BigDecimal.valueOf(-500));
	}

	@Test
	public void testWithdraw() throws NegativeAmountException, AccountInTheRedException {
		final BigDecimal expected = BigDecimal.valueOf(700);
		when(this.historyHandler.save(any())).thenReturn(true);
		// given an account with a balance equal to 1000
		final Account account = this.mockAccount();
		// when customer withdraws 300
		this.bankAccountService.withdraw(this.mockCustomer(), account, BigDecimal.valueOf(300));
		// then the current account's balance becomes equal to 700
		assertEquals(expected, account.getBalance());
		verify(this.historyHandler, times(1)).save(any());
	}

	@Test(expected = NegativeAmountException.class)
	public void testWithdrawWithNegativeAmount() throws NegativeAmountException, AccountInTheRedException {
		// given an account
		// when customer withdraws a negative amount
		// then a NegativeAmountException is thrown
		this.bankAccountService.withdraw(this.mockCustomer(), this.mockAccount(), BigDecimal.valueOf(-300));
	}

	@Test(expected = AccountInTheRedException.class)
	public void testWithdrawWithAccountInTheRed() throws NegativeAmountException, AccountInTheRedException {
		// given an account with a balance equal to 1000
		// when customer withdraws 1200
		// then an AccountInTheRedException is thrown
		this.bankAccountService.withdraw(this.mockCustomer(), this.mockAccount(), BigDecimal.valueOf(1200));
	}

	private Customer mockCustomer() {
		return Customer.builder().customerId(1L).firstName("Hamza").lastName("ESSID")
				.dateOfBirth(LocalDate.of(1993, 12, 22)).job("Software Engineer").build();
	}

	private Account mockAccount() {
		return Account.builder().accountId(1L).balance(BigDecimal.valueOf(1000)).iban("FRXX XXXX ...")
				.holder(this.mockCustomer()).build();
	}

}
