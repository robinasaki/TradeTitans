package entity;

public class TradeTransaction extends Transaction {
    private Tradeable assetIn;
    private Tradeable assetOut;
    private Double shares_in;
    private Double shares_out;

    public TradeTransaction(Double tradingFee, Tradeable assetIn, Tradeable assetOut, Double shares)
        super(tradingFee);
        this.assetIn = assetIn;
        this.assetOut = assetOut;
        this.shares = shares;
    }

    public Tradeable getAssetIn() {
        return assetIn;
    }

    public Tradeable getAssetOut() {
        return assetOut;
    }

    public Double getShares_in() {
        return shares_in;
    }

    public Double getShares_out() {
        return shares_out;
    }



    private void trade() {
        // #TODO
    }
}
