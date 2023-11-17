package entity;

import org.junit.Assert.*;
import entity.Portfolio;
import entity.Tradeable;
import org.junit.Test;
import entity.Transaction;
import entity.TradeTransaction;

public class PortfolioTest {
    private Portfolio techPortfolio;
    private Tradeable usd;
    private Tradeable ibm;
    private Tradeable shop;
    private Tradeable outsidePortfolio; // testing whether we really need BankingTransaction or whether we can use this

    public void setup() {
        techPortfolio = new Portfolio("Tech Portfolio");
        usd = new Tradeable("US Dollar", "USD");
        ibm = new Tradeable("International Business Machines Corporation", "IBM");
        shop = new Tradeable("Shopify Inc.", "SHOP");
        outsidePortfolio = new Tradeable("Outside Portfolio", "");
        
    }

    @Test
    public void testAddTrade() {
        setup();

        // assert that the portfolio is empty
        assert techPortfolio.getHoldings().size() == 0;

        // deposit 5000 USD
        TradeTransaction transaction1 = new TradeTransaction(usd, outsidePortfolio, 5000, 0, 0);
        techPortfolio.addTrade(transaction1);
        assert techPortfolio.getHoldings().get(usd) == 5000;

        // buy 10 shares of IBM for 185 USD each or 1850 USD total, then assert that the portfolio has 10 shares of IBM and 3150 USD
        TradeTransaction transaction2 = new TradeTransaction(ibm, usd, 10, 1850, 0);
        techPortfolio.addTrade(transaction2);
        assert techPortfolio.getHoldings().get(ibm) == 10;
        assert techPortfolio.getHoldings().get(usd) == 3150;

        // sell 5 shares of IBM for 200 USD each or 1000 USD total, then assert that the portfolio has 5 shares of IBM and 4150 USD
        TradeTransaction transaction3 = new TradeTransaction(usd, ibm, 5, 0, 1000);
        techPortfolio.addTrade(transaction3);
        assert techPortfolio.getHoldings().get(ibm) == 5;
        assert techPortfolio.getHoldings().get(usd) == 4150;
    }

    @Test
    public void testGetWatchlist() {
        setup();

        // assert that the watchlist is empty
        assert(techPortfolio.getWatchlist().size() == 0);

        // deposit 5000 USD and now watchlist should have 1 item
        TradeTransaction transaction1 = new TradeTransaction(usd, outsidePortfolio, 5000, 0, 0);
        techPortfolio.addTrade(transaction1);
        assert(techPortfolio.getWatchlist().size() == 1);
        assert(techPortfolio.getWatchlist().contains(usd));

        // buy 10 shares of IBM for 185 USD each or 1850 USD total and now watchlist should have 2 items
        TradeTransaction transaction2 = new TradeTransaction(ibm, usd, 10, 1850, 0);
        techPortfolio.addTrade(transaction2);
        assert(techPortfolio.getWatchlist().size() == 2);
        assert(techPortfolio.getWatchlist().contains(usd));
        assert(techPortfolio.getWatchlist().contains(ibm));
    }
}
