package entity;
import java.io.Serializable;

public class TradeTransaction extends Transaction implements Serializable {
    private String assetIn;
    private String assetOut;
    private double amountIn;
    private double amountOut;

    public TradeTransaction(String assetIn, String assetOut, double amountIn, double amountOut, double tradingFee) {
        super(tradingFee);
        this.assetIn = assetIn;
        this.assetOut = assetOut;
        this.amountIn = amountIn;
        this.amountOut = amountOut;
    }

    public String getAssetIn() {
        return assetIn;
    }

    public String getAssetOut() {
        return assetOut;
    }

    public double getAmountIn() {
        return amountIn;
    }

    public double getAmountOut() {
        return amountOut;
    }
}
