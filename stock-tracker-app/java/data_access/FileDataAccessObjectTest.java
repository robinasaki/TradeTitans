package data_access;

import entity.Tradeable;
import entity.Portfolio;
import data_access.FileDataAccessObject;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class FileDataAccessObjectTest {

    @Test
    public void saveAndLoadPortfoliosTest() {
        FileDataAccessObject fileDao = new FileDataAccessObject();

        // Creating currency
        Tradeable usd = new Tradeable("US Dollar", "$USD");
        Tradeable cad = new Tradeable("Canadian Dollar", "$CAD");

        // Creating test portfolios
        Portfolio portfolio1 = new Portfolio("Portfolio1", usd);
        Portfolio portfolio2 = new Portfolio("Portfolio2", cad);

        // Saving portfolios
        fileDao.savePortfolio(portfolio1);
        fileDao.savePortfolio(portfolio2);

        // Loading portfolios
        Portfolio loadedPortfolio1 = fileDao.getPortfolio("Portfolio1");
        Portfolio loadedPortfolio2 = fileDao.getPortfolio("Portfolio2");

        // Asserting that the loaded portfolios contain the saved portfolios
        assertEquals(loadedPortfolio1.getName(), "Portfolio1");
        assertEquals(loadedPortfolio1.getCurrency().getSymbol(), "$USD");
        assertEquals(loadedPortfolio2.getName(), "Portfolio2");
        assertEquals(loadedPortfolio2.getCurrency().getSymbol(), "$CAD");

        fileDao.removePortfolio("Portfolio1");
        fileDao.removePortfolio("Portfolio2");
    }
}
