package use_case.trade;

import org.junit.Test;

import java.util.Date;

public class TradeInputDataTest {

    private TradeInputData testingTradeInputData;

    @Test
    public void test() {
        // suppose the user sells 1 stock of IBM at the price USD$150 with tradingFee 10.99
        Date hypoDate = new Date(2023, 9, 11);
        this.testingTradeInputData = new TradeInputData("robin", 10.99, "$USD", "IBM", 150, 1, hypoDate);

        assert testingTradeInputData.getPortfolioName().equals("robin");
        assert testingTradeInputData.getTradingFee() == 10.99;
        assert testingTradeInputData.getAssetIn().equals("$USD");
        assert testingTradeInputData.getAmountIn() == 150;
        assert testingTradeInputData.getAssetOut().equals("IBM");
        assert testingTradeInputData.getAmountOut() == 1;
        assert testingTradeInputData.getDate().equals(hypoDate);

    }
}
