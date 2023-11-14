package data_access;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;
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
        Date startDate = new Date(2023, 11, 1);
        Date endDate = new Date(2023, 11, 13);

        Map<Date, Double> historicalQuotes = DAO.getHistoricalQuotes(symbol, startDate, endDate);

        assertNotNull(historicalQuotes);
        assertFalse(historicalQuotes.isEmpty());

        for (Map.Entry<Date, Double> entry : historicalQuotes.entrySet()) {
            System.out.println("Date: " + entry.getKey() + " Price: " + entry.getValue());
        }
//        Tried date filtering here but not working.
//        String dateString = "Mon Jul 14 00:00:00 EDT 2003";
//        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
//        try {
//            Date date = dateFormat.parse(dateString);
//            System.out.println(historicalQuotes.get(dateFormat));
//        } catch (Exception e) {
//            System.out.println("Not working yet.");
//        }
        System.out.println("Test1 passed.");
    }

    @Test
    public void testGetInvalidHistoricalQuotes() {
        /**
         * Test 2
         * Provided input startDate < endDate.
         */
        setUp();
        String symbol = "AAPL";
        Date startDate = new Date(2023, 10, 3);
        Date endDate = new Date(2023, 10, 1);
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
