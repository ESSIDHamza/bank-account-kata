package fr.hamzaessid.bankaccountkata.util;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fr.hamzaessid.bankaccountkata.model.History;

public class CsvHistoryHandlerImplTest {

	private HistoryHandler historyHandler;

	@Before
	public void setUp() {
		this.historyHandler = new CsvHistoryHandlerImpl();
	}

	@Test
	public void testFindByCustomerId() {
		// Given a CSV history file with 4 lines of customer #1
		final int expectedSize = 4;
		// When customer #1 wants to retrieve his transactions history
		final List<History> actual = this.historyHandler.findByCustomerId(1L);
		// Then he gets his history (4 lines)
		assertEquals(expectedSize, actual.size());
	}

}
