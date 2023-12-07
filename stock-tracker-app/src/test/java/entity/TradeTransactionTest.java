package entity;

import org.junit.Test;

import java.util.Date;

public class TradeTransactionTest {
    private TradeTransaction test;

    public void init() {
        this.test = new TradeTransaction("IBM", "$USD", 1, 155.57, 2.22, new Date(2023, 9, 11));
    }

    @Test
    public void constructorTest() {
        init();
        assert (this.test.getAssetIn().equals("IBM"));
        assert (this.test.getAssetOut().equals("$USD"));
        assert (this.test.getAmountIn() == 1);
        assert (this.test.getAmountOut() == 155.57);
        assert (this.test.getTradingFee() == 2.22);
    }
}
