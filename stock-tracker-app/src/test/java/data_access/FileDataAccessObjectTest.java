package data_access;

import entity.Portfolio;
import data_access.FileDataAccessObject;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FileDataAccessObjectTest {

    @Test
    public void saveAndLoadPortfoliosTest() {
        FileDataAccessObject fileDao = new FileDataAccessObject();

        // Creating test portfolios
        Portfolio portfolio1 = new Portfolio("Portfolio1");
        Portfolio portfolio2 = new Portfolio("Portfolio2");

        // Saving portfolios
        fileDao.savePortfolio(portfolio1);
        fileDao.savePortfolio(portfolio2);

        // Loading portfolios
        Portfolio loadedPortfolio1 = fileDao.getPortfolio(portfolio1.getName());
        Portfolio loadedPortfolio2 = fileDao.getPortfolio(portfolio2.getName());

        // Asserting that the loaded portfolios contain the saved portfolios
        assertTrue(portfolio1.equals(loadedPortfolio1));
        assertTrue(portfolio2.equals(loadedPortfolio2));
    }

    @Test
    public void getPortfolioTest() {
        FileDataAccessObject fileDao = new FileDataAccessObject();

        // Creating a test portfolio
        Portfolio testPortfolio = new Portfolio("TestPortfolio");

        // Saving the test portfolio
        fileDao.savePortfolio(testPortfolio);

        // Retrieving the portfolio by name
        Portfolio retrievedPortfolio = fileDao.getPortfolio("TestPortfolio");

        // Asserting that the retrieved portfolio is the same as the test portfolio
        assertEquals(testPortfolio, retrievedPortfolio);
    }

    @Test
    public void portfolioNotFoundTest() {
        FileDataAccessObject fileDao = new FileDataAccessObject();

        // Attempting to retrieve a non-existent portfolio
        assertThrows(IllegalArgumentException.class, () -> fileDao.getPortfolio("NonExistentPortfolio"));
    }
}
