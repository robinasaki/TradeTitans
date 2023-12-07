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
        String symbol = "MBG.DEX";
        String currency = "$USD";

        Map<Date, Double> historicalQuotes = DAO.getHistoricalQuotes(symbol, currency);

        Assertions.assertNotNull(historicalQuotes);
        Assertions.assertFalse(historicalQuotes.isEmpty());

        // Manual entered these from values on yahoo finance, then converted from euro to us dollar
        // making sure we are not off by more than 0.01
        Assertions.assertEquals(59.14, historicalQuotes.get(new Date(123, 10, 1)), 0.01);
        Assertions.assertEquals(61.09, historicalQuotes.get(new Date(123, 10, 2)), 0.01);
        Assertions.assertEquals(62.13, historicalQuotes.get(new Date(123, 10, 3)), 0.01);

        // These are weekends, so there should be no data
        assert(historicalQuotes.get(new Date(123, 10, 4)) == null);
        assert(historicalQuotes.get(new Date(123, 10, 5)) == null);
    }

    @Test
    public void testGetHistoricalCryptoQuotes() {
        /**
         * Test getHistoricalCryptoQuotes()
         */
        setUp();
        String symbol = "#BTC";
        String currency = "$CNY";

        // the alpha vantage demo returns the data in $CNY
        Map<Date, Double> historicalQuotesCrypto = DAO.getHistoricalQuotes(symbol,currency);

        Assertions.assertNotNull(historicalQuotesCrypto);
        Assertions.assertFalse(historicalQuotesCrypto.isEmpty());

        // manually input data from the API demo
        // as the demo API price output is inconsistent
        Assertions.assertEquals(270726.380895, historicalQuotesCrypto.get(new Date(123, 10, 28)), 3000);

        assert (historicalQuotesCrypto.get(new Date(99999, 10, 5)) == null);
    }
}
