package data_access;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import java.text.SimpleDateFormat;

import data_access.APIDataAccessObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.*;

public class APIDataAccessObjectTest {
    private APIDataAccessObject DAO;

    //    @BeforeEach
    void setUp() {
        DAO = new APIDataAccessObject();
    }

    @Test
    public void testGetHistoricalQuotes() {
        /**
         * Note that if you fail the test, check if you have already uncommented line 46 in the APIDataAccessObject class.
         * We have commented that line to prevent reaching the daily max API call count.
         */
        setUp();
        String symbol = "IBM";
        String currency = "$USD";

        Map<Date, Double> historicalQuotes = DAO.getHistoricalQuotes(symbol, currency);

        Assertions.assertNotNull(historicalQuotes);
        Assertions.assertFalse(historicalQuotes.isEmpty());

        // Manual entered these from values on yahoo finance
        assert(historicalQuotes.get(new Date(123, 10, 1)) == 145.40);
        assert(historicalQuotes.get(new Date(123, 10, 2)) == 147.01);
        assert(historicalQuotes.get(new Date(123, 10, 3)) == 147.90);

        // These are weekends, so there should be no data
        assert(historicalQuotes.get(new Date(123, 10, 4)) == null);
        assert(historicalQuotes.get(new Date(123, 10, 5)) == null);
    }
}
