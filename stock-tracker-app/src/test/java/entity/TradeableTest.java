package entity;


import org.junit.Test;

import java.util.Date;
import java.util.TreeMap;

//import static entity.Tradeable.addTradeable;
//import static entity.Tradeable.getTradeable;

public class TradeableTest {
    private Tradeable test;

    public void init() {
        this.test = new Tradeable("Canadian Dollars", "$CAD");
    }

    @Test
    public void testConstructor() {
        init();
        assert (test.getName().equals("Canadian Dollars"));
        assert (test.getSymbol().equals("$CAD"));
        assert (test.getPriceHistory().isEmpty());
    }

    @Test
    public void testSetPriceHistory() {
        init();
        TreeMap<Date, Double> hypoHistory = new TreeMap<>();
        hypoHistory.put(new Date(2023, 9, 11), 0.73);
        this.test.setPriceHistory(hypoHistory);
        assert (this.test.getPriceHistory().equals(hypoHistory));
    }

    @Test
    public void testGetCurrentPrice() {
        init();
        TreeMap<Date, Double> hypoHistory = new TreeMap<>();
        hypoHistory.put(new Date(2023, 9, 11), 0.73);
        hypoHistory.put(new Date(2023, 9, 12), 0.74);
        this.test.setPriceHistory(hypoHistory);
        assert (this.test.getPriceHistory().equals(hypoHistory));
        assert (this.test.getCurrentPrice() == 0.74);
    }

    @Test
    public void testGetCurrentPriceWithEmptyHistory() {
        /**
         * Test getCurrentPrice() with an empty priceHistory.
         * Should expect a RunTimeException.
         */
        this.test = new Tradeable("Canadian Dollars", "$CAD");
        try {
            System.out.println(this.test.getCurrentPrice());
            assert (false);
        } catch (RuntimeException e) {
            assert (true);
        }

    }
}
