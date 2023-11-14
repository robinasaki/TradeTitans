package data_access;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.text.SimpleDateFormat;

import data_access.APIDataAccessObject;
import org.junit.Test;
//import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.*;

public class APIDataAccessObjectTest {
    private APIDataAccessObject DAO;

    //    @BeforeEach
    void setUp() {
        DAO = new APIDataAccessObject();
    }

    @Test
    public void testGetHistoricalQuotes() {
        setUp();
        String symbol = "AAPL";

        // November 1st, 2023 to November 5th, 2023
        Date startDate = new Date(123, 10, 1);
        Date endDate = new Date(123, 10, 5);

        Map<Date, Double> historicalQuotes = DAO.getHistoricalQuotes(symbol, startDate, endDate);

        assertNotNull(historicalQuotes);
        assertFalse(historicalQuotes.isEmpty());

        for (Map.Entry<Date, Double> entry : historicalQuotes.entrySet()) {
            System.out.println("Date: " + entry.getKey() + " Price: " + entry.getValue());
        }

        // Manual entered these from values on yahoo finance
        assert(historicalQuotes.get(new Date(123, 10, 1)) == 173.97);
        assert(historicalQuotes.get(new Date(123, 10, 2)) == 177.57);
        assert(historicalQuotes.get(new Date(123, 10, 3)) == 176.65);

        // These are weekends, so there should be no data
        assert(historicalQuotes.get(new Date(123, 10, 4)) == null);
        assert(historicalQuotes.get(new Date(123, 10, 5)) == null);
    }

    @Test
    public void testGetInvalidHistoricalQuotes() {
        /**
         * Test 2
         * Provided input startDate < endDate.
         */
        setUp();
        String symbol = "AAPL";

        // November 3rd, 2023 to November 1st, 2023
        Date startDate = new Date(123, 10, 3);
        Date endDate = new Date(123, 10, 1);
        try {
            Map<Date, Double> historicalQuotes = DAO.getHistoricalQuotes(symbol, startDate, endDate);
            System.out.println("Test2 NOT passed.");
        } catch (Exception e) {
            System.out.println("Test2 passed.");
        }
    }

    @Test
    public void testGetFutureHistoricalQuotes() {
        /**
         * Test 3
         * Provided input endDate > today.
         */
        setUp();
        String symbol = "AAPL";
        Date startDate = new Date(2025, 1, 1);
        Date endDate = new Date(2025, 1, 5);
        try {
            Map<Date, Double> historicalQuotes = DAO.getHistoricalQuotes(symbol, startDate, endDate);
//            LocalDate localDate = LocalDate.now();
//            Date today = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
//            System.out.println(endDate.after(today));
            System.out.println("Test3 NOT passed.");
        } catch (Exception e) {
            System.out.println("Test3 passed.");
        }
    }
}
