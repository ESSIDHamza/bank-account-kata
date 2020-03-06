package fr.hamzaessid.bankaccountkata.entity;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents a bank customer.
 *
 * @author ESSID, Hamza
 * @since 0.0.1
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
@SuppressWarnings("serial")
public class Customer implements Serializable {

	private Long customerId;
	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;
	private String job;

	private Account account;

}
