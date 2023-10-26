package entity;

public class TradeTransaction extends Transaction {
    Tradable assetIn;
    Tradable assetOut;
    Double shares;
    Double sharePrice;

    public TradeTransaction(Double tradingFee, Tradable assetIn, Tradable assetOut, Double shares, Double sharePrice) {
        super(tradingFee);
        this.assetIn = assetIn;
        this.assetOut = assetOut;
        this.shares = shares;
        this.sharePrice = sharePrice;
    }

    private void trade(){
        // #TODO
    }
}
