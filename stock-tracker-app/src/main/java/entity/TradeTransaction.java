package entity;

public class TradeTransaction extends Transaction {
    Tradeable assetIn;
    Tradeable assetOut;
    Double shares;
    Double sharePrice;

    public TradeTransaction(Double tradingFee, Tradeable assetIn, Tradeable assetOut, Double shares, Double sharePrice) {
        super(tradingFee);
        this.assetIn = assetIn;
        this.assetOut = assetOut;
        this.shares = shares;
        this.sharePrice = sharePrice;
    }

    private void trade() {
        // #TODO
    }
}
