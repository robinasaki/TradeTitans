package entity;

public class BankingTransaction extends Transaction {
    Tradeable asset;
    Boolean deposit;
    Double amount;

    public BankingTransaction(Double tradingFee, Tradeable asset, Boolean deposit, Double amount) {
        super(tradingFee);
        this.asset = asset;
        this.deposit = deposit;
        this.amount = amount;
    }

}
