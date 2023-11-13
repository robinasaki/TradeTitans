package entity;

import org.junit.Test;

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
    public void testAddTrade() {
        setup();

        // deposit 5000 USD
        TradeTransaction transaction1 = new TradeTransaction(0,usd, outsidePortfolio, 5000, 0);
        techPortfolio.addTrade(transaction1);

        // buy 10 shares of Apple for 185 USD each or 1850 USD total
        TradeTransaction transaction2 = new TradeTransaction(0,appleStock, usd, 10, 1850);
        techPortfolio.addTrade(transaction2);

        // assert that the portfolio has 10 shares of Apple and 3150 USD
        assert techPortfolio.getHoldings().get("AAPL") == 10;
        assert techPortfolio.getHoldings().get("USD") == 3150;
    }
}
