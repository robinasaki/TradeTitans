package use_case.trade;

import entity.Tradeable;

public class TradeInputData {
    private final String portfolioName;
    private final double tradingFee;
    private final Tradeable assetIn;
    private final Tradeable assetOut;
    private final double amountIn;
    private final double amountOut;
    // TODO: add date

    public TradeInputData(String portfolioName, double tradingFee, Tradeable assetIn, Tradeable assetOut, double amountIn, double amountOut) {
        this.portfolioName = portfolioName;
        this.tradingFee = tradingFee;
        this.assetIn = assetIn;
        this.assetOut = assetOut;
        this.amountIn = amountIn;
        this.amountOut = amountOut;
    }

    public String getPortfolioName() { return portfolioName; }
    public double getTradingFee() { return tradingFee; }
    public Tradeable getAssetIn() { return assetIn; }
    public Tradeable getAssetOut() { return assetOut; }
    public double getAmountIn() { return amountIn; }
    public double getAmountOut() { return amountOut; }
}
