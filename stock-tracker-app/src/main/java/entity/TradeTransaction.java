package entity;
import java.io.Serializable;

public class TradeTransaction extends Transaction implements Serializable {
    private Tradeable assetIn;
    private Tradeable assetOut;
    private double amountIn;
    private double amountOut;

    public TradeTransaction(Tradeable assetIn, Tradeable assetOut, double amountIn, double amountOut, double tradingFee) {
        super(tradingFee);
        this.assetIn = assetIn;
        this.assetOut = assetOut;
        this.amountIn = amountIn;
        this.amountOut = amountOut;
    }

    public Tradeable getAssetIn() {
        return assetIn;
    }

    public Tradeable getAssetOut() {
        return assetOut;
    }

    public double getAmountIn() {
        return amountIn;
    }

    public double getAmountOut() {
        return amountOut;
    }
}
