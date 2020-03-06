package fr.hamzaessid.bankaccountkata.util;

import java.util.Collections;
import java.util.List;
import fr.hamzaessid.bankaccountkata.model.History;

/**
 * This class mocks a database as well as a DAO repository behaviours.
 *
 * @author ESSID, Hamza
 * @since 0.0.1
 */

public class CsvHistoryHandlerImpl implements HistoryHandler {

	@Override
	public boolean save(History history) {
		return false;
	}

	@Override
	public List<History> findByCustomerId(Long customerId) {
		return Collections.emptyList();
	}

}
