package fr.hamzaessid.bankaccountkata.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import fr.hamzaessid.bankaccountkata.entity.Account;
import fr.hamzaessid.bankaccountkata.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents an operation transaction.
 *
 * @author ESSID, Hamza
 * @since 0.0.1
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
@SuppressWarnings("serial")
public class History implements Serializable {

	private BigDecimal amount;
	private OperationType operationType;
	private LocalDateTime operationTime;
	private Account account;
	private Customer customer;

}
