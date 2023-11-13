import org.junit.Assert.*;
import java.util.Date;
import entity.Portfolio;
import entity.Tradeable;
import org.junit.Test;

public class PortfolioTest {
    private Portfolio techPortfolio;
    private Tradeable usd;
    private Tradeable appleStock;
    private Tradeable microsoftStock;
    private Tradeable googleStock;

    public void setup() {
        techPortfolio = new Portfolio("Tech Portfolio");
        usd = new Tradeable("US Dollar", "USD");
        appleStock = new Tradeable("Apple Inc.", "AAPL");
        microsoftStock = new Tradeable("Microsoft", "MSFT");
        googleStock = new Tradeable("Alphabet Inc.", "GOOGL");
    }

    @Test
    public void testAddTransaction() {
        setup();
        assert(false); // TODO
    }
}
