package fr.hamzaessid.bankaccountkata.util;

import java.util.List;

import fr.hamzaessid.bankaccountkata.model.History;

public interface HistoryHandler {

	boolean save(final History history);

	List<History> findByCustomerId(final Long customerId);

}
