package entity;

import org.junit.Assert.*;
import entity.Portfolio;
import entity.Tradeable;
import org.junit.jupiter.api.Test;
import entity.Transaction;
import entity.TradeTransaction;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;

public class PortfolioTest {
    private Portfolio techPortfolio;
    private Tradeable usd;
    private Tradeable ibm;
    private Tradeable shop;
    private Tradeable outsidePortfolio; // testing whether we really need BankingTransaction or whether we can use this

    public void setup() {
        usd = new Tradeable("US Dollar", "$USD");
        techPortfolio = new Portfolio("Tech Portfolio", usd);
        ibm = new Tradeable("International Business Machines Corporation", "IBM");
        shop = new Tradeable("Shopify Inc.", "SHOP.TRT");
        outsidePortfolio = new Tradeable("Outside Portfolio", "");

    }

    @Test
    public void testAddTrade() {
        setup();

        // assert that the portfolio is empty
        assert techPortfolio.getHoldings().isEmpty();

        // deposit 5000 USD
        TradeTransaction transaction1 = new TradeTransaction("$USD", "", 5000, 0, 0);
        techPortfolio.addTrade(transaction1);
        assert techPortfolio.getHoldings().get("$USD") == 5000;

        // buy 10 shares of IBM for 185 USD each or 1850 USD total, then assert that the portfolio has 10 shares of IBM and 3150 USD
        TradeTransaction transaction2 = new TradeTransaction("IBM", "$USD", 10, 1850, 0);
        techPortfolio.addTrade(transaction2);
        assert techPortfolio.getHoldings().get("IBM") == 10;
        assert techPortfolio.getHoldings().get("$USD") == 3150;

        // sell 5 shares of IBM for 200 USD each or 1000 USD total, then assert that the portfolio has 5 shares of IBM and 4150 USD
        TradeTransaction transaction3 = new TradeTransaction("$USD", "IBM", 1000, 5, 0);
        techPortfolio.addTrade(transaction3);
        assert techPortfolio.getHoldings().get("IBM") == 5;
        assert techPortfolio.getHoldings().get("$USD") == 4150;
    }

    @Test
    public void testGetWatchlist() {
        setup();

        // assert that the watchlist is empty
        assert (techPortfolio.getWatchlist().isEmpty());

        // deposit 5000 USD and now watchlist should have 1 item
        TradeTransaction transaction1 = new TradeTransaction("$USD", "", 5000, 0, 0);
        techPortfolio.addTrade(transaction1);
        assert (techPortfolio.getWatchlist().size() == 1);
        assert (techPortfolio.getWatchlist().contains("$USD"));

        // buy 10 shares of IBM for 185 USD each or 1850 USD total and now watchlist should have 2 items
        TradeTransaction transaction2 = new TradeTransaction("IBM", "$USD", 10, 1850, 0);
        techPortfolio.addTrade(transaction2);
        assert (techPortfolio.getWatchlist().size() == 2);
        assert (techPortfolio.getWatchlist().contains("$USD"));
        assert (techPortfolio.getWatchlist().contains("IBM"));
    }

    @Test
    public void constructorTestLong() {
        /**
         * Tests on the longer constructor.
         */
        HashMap<String, Double> hypoHoldings = new HashMap<>();
        hypoHoldings.put("SHOP.TRT", 5.00);
        ArrayList<Transaction> hypoTransaction = new ArrayList<>();
        hypoTransaction.add(new TradeTransaction("$CAD", "IBM", 155, 1, 2));
        Portfolio test = new Portfolio("robin", new Tradeable("Canadian Dollars", "$CAD"), hypoHoldings, hypoTransaction);
    }

    @Test
    public void constructorTestShort() {
        /**
         * Test on the shortened constructor.
         */
        Portfolio testShort = new Portfolio("robin", new Tradeable("Canadian Dollars", "$CAD"));
    }

    @Test
    public void basicGetsTests() {
        /**
         * Tests on getCurrency(), getName(), and all the basic get methods.
         */
        HashMap<String, Double> hypoHoldings = new HashMap<>();
        hypoHoldings.put("SHOP.TRT", 5.00);
        ArrayList<Transaction> hypoTransaction = new ArrayList<>();
        hypoTransaction.add(new TradeTransaction("$CAD", "IBM", 155, 1, 2));
        Portfolio test = new Portfolio("robin", new Tradeable("Canadian Dollars", "$CAD"), hypoHoldings, hypoTransaction);
        assert (test.getName().equals("robin"));
        assert (test.getCurrency().equals(new Tradeable("Canadian Dollars", "$CAD")));
        test.setCurrency(new Tradeable("US Dollars", "$USD"));
        assert (test.getCurrency().equals(new Tradeable("US Dollars", "$USD")));
        assert (test.getHoldings().equals(hypoHoldings));
        assert (test.getTransactions().equals(hypoTransaction));
        assert (test.getPortfolioId() == 1);
    }

    @Test
    public void assetModificationTest() {
        /**
         * Tests on methods such as addAsset(), removeAsset(), etc.
         */
        HashMap<String, Double> hypoHoldings = new HashMap<>();
        hypoHoldings.put("SHOP.TRT", 5.00);
        ArrayList<Transaction> hypoTransaction = new ArrayList<>();
        hypoTransaction.add(new TradeTransaction("$CAD", "IBM", 155, 1, 2));
        Portfolio test = new Portfolio("robin", new Tradeable("Canadian Dollars", "$CAD"), hypoHoldings, hypoTransaction);
        // TODO: do more coverage
    }

    @Test
    public void getPortfolioValueTest() {
        // relevant tradeables
        Tradeable.addTradeable("$CAD");
        Tradeable cad = Tradeable.getTradeable("$CAD");

        // initialize portfolio
        Portfolio robinsPortfolio = new Portfolio("robin", cad);

        // deposit 5000 CAD
        TradeTransaction deposit = new TradeTransaction("$CAD", "", 5000, 0, 0);
        robinsPortfolio.addTrade(deposit);

        // buy 10 shares of IBM for 155 CAD each or 1550 CAD total
        TradeTransaction buyIBM = new TradeTransaction("IBM", "$CAD", 10, 1550, 0);
        robinsPortfolio.addTrade(buyIBM);

        // setting price historys
        Tradeable.getTradeable("$CAD").setPriceHistory(new TreeMap<Date, Double>() {{
            put(new Date(123, 10, 27), 1.0);
            put(new Date(123, 10, 28), 1.0);
        }});
        Tradeable.getTradeable("IBM").setPriceHistory(new TreeMap<Date, Double>() {{
            put(new Date(123, 10, 27), 190.0);
            put(new Date(123, 10, 28), 200.0);
        }});

        // Portfolio value should be (5000 - 1550 = 3450) + (10 * 200 = 2000) = 5450 CAD
        double expectedPortfolioValue = 5450;
        System.out.println(robinsPortfolio.getPortfolioValue());
        assert (robinsPortfolio.getPortfolioValue() == expectedPortfolioValue);
    }
}
