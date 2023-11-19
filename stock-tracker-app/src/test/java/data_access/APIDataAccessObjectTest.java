package data_access;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
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
        String symbol = "IBM";

        // November 1st, 2023 to November 5th, 2023
        Date startDate = new Date(123, 10, 1);
        Date endDate = new Date(123, 10, 5);

        Map<Date, Double> historicalQuotes = DAO.getHistoricalQuotes(symbol);

        assertNotNull(historicalQuotes);
        assertFalse(historicalQuotes.isEmpty());

        // Manual entered these from values on yahoo finance
        assert(historicalQuotes.get(new Date(123, 10, 1)) == 145.40);
        assert(historicalQuotes.get(new Date(123, 10, 2)) == 147.01);
        assert(historicalQuotes.get(new Date(123, 10, 3)) == 147.90);

        // These are weekends, so there should be no data
        assert(historicalQuotes.get(new Date(123, 10, 4)) == null);
        assert(historicalQuotes.get(new Date(123, 10, 5)) == null);
    }

/*
    @Test
    public void testGetInvalidHistoricalQuotes() {
        
         // Test 2
         // Provided input startDate is after endDate.
         //
        setUp();
        String symbol = "IBM";

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
        
         // Test 3
         // Provided input endDate is after today.
         
        setUp();
        String symbol = "IBM";

        // January 1st, 2025 to January 5th, 2025
        Date startDate = new Date(125, 0, 1);
        Date endDate = new Date(125, 0, 5);
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
*/
}
