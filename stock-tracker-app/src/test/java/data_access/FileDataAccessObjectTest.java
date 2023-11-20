package data_access;

import entity.Tradeable;
import entity.Portfolio;
import data_access.FileDataAccessObject;
import org.junit.Test;

import java.util.List;

public class FileDataAccessObjectTest {

    @Test
    public void saveAndLoadPortfoliosTest() {
        FileDataAccessObject fileDao = new FileDataAccessObject();

        // Creating currency
        Tradeable usd = new Tradeable("US Dollar", "USD");

        // Creating test portfolios
        Portfolio portfolio1 = new Portfolio("Portfolio1", usd);
        Portfolio portfolio2 = new Portfolio("Portfolio2", usd);

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
}
