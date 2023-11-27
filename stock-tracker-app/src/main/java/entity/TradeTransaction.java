package entity;
import java.io.Serializable;

public class TradeTransaction extends Transaction implements Serializable {
    private String assetInSymbol;
    private String assetOutSymbol;
    private double amountIn;
    private double amountOut;

    public TradeTransaction(String assetInSymbol, String assetOutSymbol, double amountIn, double amountOut, double tradingFee) {
        super(tradingFee);
        this.assetInSymbol = assetInSymbol;
        this.assetOutSymbol = assetOutSymbol;
        this.amountIn = amountIn;
        this.amountOut = amountOut;
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
