import entity.Portfolio;
import entity.Tradeable;
import org.junit.Test;
package entity;


public class PortfolioTest {
    private Portfolio techPortfolio;
    private Tradeable usd;
    private Tradeable appleStock;
    private Tradeable microsoftStock;
    private Tradeable googleStock;
    private Tradeable outsidePortfolio; // testing whether we really need BankingTransaction or whether we can use this

    public void setup() {
        techPortfolio = new Portfolio("Tech Portfolio");
        usd = new Tradeable("US Dollar", "USD");
        appleStock = new Tradeable("Apple Inc.", "AAPL");
        microsoftStock = new Tradeable("Microsoft", "MSFT");
        googleStock = new Tradeable("Alphabet Inc.", "GOOGL");
        outsidePortfolio = new Tradeable("Outside Portfolio", "");
        
    }

    @Test
    public void testAddTransaction() {
        setup();
        assert(false); // TODO
    }
}
