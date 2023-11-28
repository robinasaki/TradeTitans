package entity;

public class BankingTransaction extends Transaction {
    private Tradeable asset;
    private boolean deposit;
    private double amount;

    public BankingTransaction(double tradingFee, Tradeable asset, boolean deposit, double amount) {
        super(tradingFee);
        this.asset = asset;
        this.deposit = deposit;
        this.amount = amount;
    }

    public Tradeable getAsset() {
        return asset;
    }

    public double getAmount() {
        return amount;
    }

    public boolean getIfDeposit() {return this.deposit;}

}
