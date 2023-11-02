package use_case;

import entity.Tradeable;

public class TradeInputData {
    final private double tradingFee;
    final private Tradeable assetIn;
    final private Tradeable assetOut;
    final private double amountIn;
    final private double amountOut;

    public TradeInputData(double tradingFee, Tradeable assetIn, Tradeable assetOut, double amountIn, double amountOut) {
        this.tradingFee = tradingFee;
        this.assetIn = assetIn;
        this.assetOut = assetOut;
        this.amountIn = amountIn;
        this.amountOut = amountOut;
    }

    public double getTradingFee() { return tradingFee; }
    public Tradeable getAssetIn() { return assetIn; }
    public Tradeable getAssetOut() { return assetOut; }
    public double getAmountIn() { return amountIn; }
    public double getAmountOut() { return amountOut; }
}
