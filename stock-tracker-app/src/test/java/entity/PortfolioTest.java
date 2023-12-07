package entity;

import entity.Portfolio;
import org.junit.Test;

import javax.sound.sampled.Port;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class PortfolioTest {

    private Portfolio testingPortfolio;

    private Portfolio testingPortfolioFullConstructor;

    public void init() {
        /**
         * Instantiate an empty portfolio with USD
         */
        this.testingPortfolio = new Portfolio("robin", new Tradeable("the US Dollar", "$USD"));
        this.testingPortfolioFullConstructor = new Portfolio("robin", new Tradeable("the US Dollar", "$USD"), new HashMap<>(), new ArrayList<>());
    }

    @Test
    public void testGetMethodsOnEmpty() {
        /**
         * Test all the basic get methods such as getName(),getCurrency, etc.
         * This also uses the equal() methods in Tradeable.
         * Note that two Tradeable objects are equal iff they have the same symbol.
         */
        init();

        // test getCurrency()
        assert testingPortfolio.getCurrency().equals(new Tradeable("the US Dollar", "$USD"));

        // test getName()
        assert testingPortfolio.getName().equals("robin");

        // test getHoldings()
        assert testingPortfolio.getHoldings().isEmpty();

        // test getWatchlist()
        assert testingPortfolio.getWatchlist().isEmpty();

        // test getPortfolioValue()
        assert testingPortfolio.getPortfolioValue() == 0.00;

        // test getTransactions()
        assert testingPortfolio.getTransactions().isEmpty();
    }

    @Test
    public void testGetMethodsOnNonEmpty() {
        /**
         * Testing the basic methods on a non-empty portfolio.
         * Note that the tradingFee is set to be 10.99 in this test.
         */
        init();

        // the user deposits 5000 usd
        testingPortfolio.addTrade(new TradeTransaction("$USD", "", 5000, 0, 10.99, new Date(2023, 9, 11)));

        // the user purchase 1 IBM stock with USD$50
        // we do not want to call the data access object here
        testingPortfolio.addTrade(new TradeTransaction("IBM", "$USD", 1, 50, 10.99, new Date(2023, 9, 11)));

        // the user should have 2 holdings, $USD and IBM
        assert testingPortfolio.getHoldings().size() == 2;

        // there should be 1 deposit transaction and 1 trade transaction
        assert testingPortfolio.getTransactions().size() == 2;

        // suppose we remove the IBM stock
        testingPortfolio.removeAsset("IBM");

        // IBM should be cleared
        assert testingPortfolio.getHoldings().size() == 1;
    }
}