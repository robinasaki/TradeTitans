package entity;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Objects;

public class CurrencyTest {
    private Currency test;

    @BeforeEach
    public void init() {
        this.test = new Currency("USD");
    }

    @Test
    public void testConstructor() {
        /**
         * Test the class constructor.
         */
        assert(Objects.equals(this.test.getSymbol(), "USD"));
    }

    // TODO: test Trade()
}
