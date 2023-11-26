package use_case.trade;

import entity.Tradeable;

public class TradeInputData {
    private final String portfolioName;
    private final double tradingFee;
    private final String assetInSymbol;
    private final String assetOutSymbol;
    private final double amountIn;
    private final double amountOut;

    public TradeInputData(String portfolioName, double tradingFee, String assetInSymbol, String assetOutSymbol,
                          double amountIn, double amountOut) {
        this.portfolioName = portfolioName;
        this.tradingFee = tradingFee;
        this.assetInSymbol = assetInSymbol;
        this.assetOutSymbol = assetOutSymbol;
        this.amountIn = amountIn;
        this.amountOut = amountOut;
    }

    public String getPortfolioName() {
        return portfolioName;
    }
    public double getTradingFee() {
        return tradingFee;
    }
    public String getAssetIn() {
        return assetInSymbol;
    }
    public String getAssetOut() {
        return assetOutSymbol;
    }
    public double getAmountIn() {
        return amountIn;
    }
    public double getAmountOut() {
        return amountOut;
    }
}
