package entity;
import java.io.Serializable;
import java.util.Date;

public class TradeTransaction implements Serializable {
    private String assetInSymbol;
    private String assetOutSymbol;
    private double amountIn;
    private double amountOut;
    private double tradingFee;
    private Date date;

    public TradeTransaction(String assetInSymbol, String assetOutSymbol, double amountIn, double amountOut, double tradingFee, Date date) {
        this.assetInSymbol = assetInSymbol;
        this.assetOutSymbol = assetOutSymbol;
        this.amountIn = amountIn;
        this.amountOut = amountOut;
        this.tradingFee = tradingFee;
        this.date = date;
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

    public double getTradingFee() {
        return tradingFee;
    }

    public Date getDate() {
        return date;
    }
}
