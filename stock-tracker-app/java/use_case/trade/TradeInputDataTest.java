package test.java.use_case.trade;

import org.junit.jupiter.api.Test;
import use_case.trade.TradeInputData;

import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

class TradeInputDataTest {

    @Test
    void constructor_ShouldSetValues() {
        // Arrange
        String portfolioName = "TestPortfolio";
        double tradingFee = 0.02;
        String assetInSymbol = "USD";
        String assetOutSymbol = "EUR";
        double amountIn = 100.0;
        double amountOut = 90.0;
        Date date = new Date();

        // Act
        TradeInputData inputData = new TradeInputData(portfolioName, tradingFee, assetInSymbol, assetOutSymbol, amountIn, amountOut, date);

        // Assert
        Assertions.assertEquals(portfolioName, inputData.getPortfolioName());
        Assertions.assertEquals(tradingFee, inputData.getTradingFee());
        Assertions.assertEquals(assetInSymbol, inputData.getAssetIn());
        Assertions.assertEquals(assetOutSymbol, inputData.getAssetOut());
        Assertions.assertEquals(amountIn, inputData.getAmountIn());
        Assertions.assertEquals(amountOut, inputData.getAmountOut());
        Assertions.assertEquals(date, inputData.getDate());
    }

    @Test
    void getters_ShouldReturnCorrectValues() {
        // Arrange
        String portfolioName = "AnotherPortfolio";
        double tradingFee = 0.01;
        String assetInSymbol = "BTC";
        String assetOutSymbol = "ETH";
        double amountIn = 10.0;
        double amountOut = 8.0;
        Date date = new Date();
        TradeInputData inputData = new TradeInputData(portfolioName, tradingFee, assetInSymbol, assetOutSymbol, amountIn, amountOut, date);

        // Act and Assert
        Assertions.assertEquals(portfolioName, inputData.getPortfolioName());
        Assertions.assertEquals(tradingFee, inputData.getTradingFee());
        Assertions.assertEquals(assetInSymbol, inputData.getAssetIn());
        Assertions.assertEquals(assetOutSymbol, inputData.getAssetOut());
        Assertions.assertEquals(amountIn, inputData.getAmountIn());
        Assertions.assertEquals(amountOut, inputData.getAmountOut());
        Assertions.assertEquals(date, inputData.getDate());
    }
}

