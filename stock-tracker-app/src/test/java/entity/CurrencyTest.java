package entity;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.*;

public class CurrencyTest {
    /**
     * Test the entity class Currency.
     *
     * @param test: Currency constructor(1 arg) testing.
     * @param testUSDWithHistory: Currency constructor(3 args) testing.
     */
    private Currency test;
    private Currency testUSDWithHistory;

    public void init() {
        this.test = new Currency("USD");
        TreeMap<Date, Double> hypoHistory = new TreeMap<>();
        hypoHistory.put(new Date(2020, 9, 11), 5.20);
        this.testUSDWithHistory = new Currency("USD", "$USD", hypoHistory);
    }

    @Test
    public void testConstructor() {
        /**
         * Test the class constructor.
         */
        init();
        assert (Objects.equals(this.test.getName(), "USD"));
    }

    @Test
    public void testConstructorThreeArgs() {
        /**
         * Test the class constructor with three args.
         */
        init();
        assert (Objects.equals(this.testUSDWithHistory.getName(), "USD"));
        assert (Objects.equals(this.testUSDWithHistory.getSymbol(), "$USD"));
        System.out.println(this.testUSDWithHistory.getPriceHistory());
    }

}
