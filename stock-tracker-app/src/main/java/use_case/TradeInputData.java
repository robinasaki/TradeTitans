package use_case;

public class TradeInputData {
    final private String tradingFee;
    final private String assetIn;
    final private String assetOut;
    final private String amountIn;
    final private String amountOut;

    public TradeInputData(String tradingFee, String assetIn, String assetOut, String amountIn, String amountOut) {
        this.tradingFee = tradingFee;
        this.assetIn = assetIn;
        this.assetOut = assetOut;
        this.amountIn = amountIn;
        this.amountOut = amountOut;
    }

    String getTradingFee() { return tradingFee; }
    String getAssetIn() { return assetIn; }
    String getAssetOut() { return assetOut; }
    String getAmountIn() { return amountIn; }
    String getAmountOut() { return amountOut; }
}
