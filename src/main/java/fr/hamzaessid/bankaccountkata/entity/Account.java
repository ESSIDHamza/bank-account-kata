package fr.hamzaessid.bankaccountkata.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents a real-world account in a bank.
 *
 * @author ESSID, Hamza
 * @since 0.0.1
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@SuppressWarnings("serial")
public class Account implements Serializable {

	private Long accountId;
	private BigDecimal balance;
	private String iban;

	private Customer holder;

}
