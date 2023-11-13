package app;

import java.util.Date;
import data_access.APIDataAccessObject;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.*;

public class APIDataAccessObjectTest {
    private APIDataAccessObject DAO;

    @BeforeEach
    void init() {
        DAO = new APIDataAccessObject();
    }

    @Test
    public void testGetHistoricalQuotes(){
        assertNull(DAO.getHistoricalQuotes("APPL", new Date(2023, 9, 10), new Date(2023, 9, 11)));
    }
}
