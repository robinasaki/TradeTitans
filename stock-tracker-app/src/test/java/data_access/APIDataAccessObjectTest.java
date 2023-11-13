package data_access;

import java.util.Date;
import java.util.Map;
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
    public void testGetHistoricalQuotes(){
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
    }
}
