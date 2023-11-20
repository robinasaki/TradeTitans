package data_access;

import entity.Portfolio;
import data_access.FileDataAccessObject;
import org.junit.Test;

import java.util.List;

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
        Portfolio loadedPortfolio1 = fileDao.getPortfolio("Portfolio1");
        Portfolio loadedPortfolio2 = fileDao.getPortfolio("Portfolio2");

        // Asserting that the loaded portfolios contain the saved portfolios
        assert(portfolio1.equals(loadedPortfolio1));
        assert(portfolio2.equals(loadedPortfolio2));
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
        assert(testPortfolio.equals(retrievedPortfolio));
    }
}
