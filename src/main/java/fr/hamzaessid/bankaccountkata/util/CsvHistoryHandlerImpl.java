package fr.hamzaessid.bankaccountkata.util;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import fr.hamzaessid.bankaccountkata.entity.Account;
import fr.hamzaessid.bankaccountkata.entity.Customer;
import fr.hamzaessid.bankaccountkata.model.History;
import fr.hamzaessid.bankaccountkata.model.OperationType;
import lombok.extern.slf4j.Slf4j;

/**
 * This class mocks a database as well as a DAO repository behaviours.
 *
 * @author ESSID, Hamza
 * @since 0.0.1
 */
@Slf4j
public class CsvHistoryHandlerImpl implements HistoryHandler {

	private static final String HISTORY_FILENAME = "bank_account_history.csv";
	private static final String CSV_SEPARATOR = ";";

	@Override
	public boolean save(History history) {
		final Path path = Paths.get(HISTORY_FILENAME);
		try {
			Files.write(path, Arrays.asList(this.mapHistoryToCsvLine(history)), StandardCharsets.UTF_8,
					StandardOpenOption.APPEND);
			return true;
		} catch (IOException e) {
			log.error(String.format("Error occurred when trying to save history! %s", e.getMessage()));
			return false;
		}
	}

	@Override
	public List<History> findByCustomerId(Long customerId) {
		final Path path = Paths.get(HISTORY_FILENAME);
		List<History> histories = Collections.emptyList();
		try {
			histories = Files.readAllLines(path).stream()
					.filter(line -> Long.parseLong(line.split(Pattern.quote(CSV_SEPARATOR))[0]) == customerId)
					.map(this::mapCsvLineToHistory).collect(Collectors.toList());
		} catch (Exception e) {
			log.error(String.format("Error occurred while retrieving histories! %s", e.getMessage()));
		}
		return histories;
	}

	private String mapHistoryToCsvLine(final History history) {
		return String.join(CSV_SEPARATOR, history.getCustomer().getCustomerId().toString(),
				history.getOperationType().toString(),
				history.getOperationTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")),
				history.getAmount().toString(), history.getAccount().getBalance().toString(),
				history.getAccount().getAccountId().toString());
	}

	private History mapCsvLineToHistory(final String csvLine) {
		final String[] lineData = csvLine.split(Pattern.quote(CSV_SEPARATOR));

		return History.builder().amount(new BigDecimal(lineData[3]))
				.operationType(OperationType.valueOf(lineData[1].toUpperCase()))
				.operationTime(LocalDateTime.parse(lineData[2], DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")))
				.account(Account.builder().accountId(Long.parseLong(lineData[5])).balance(new BigDecimal(lineData[4]))
						.build())
				.customer(Customer.builder().customerId(Long.parseLong(lineData[0])).build()).build();
	}

}
