package entity;

import java.time.LocalDateTime;

public abstract class Transaction {
    final LocalDateTime transactionTime;
    final double tradingFee;

    public Transaction(Double tradingFee) {
        this.transactionTime = LocalDateTime.now();
        this.tradingFee = tradingFee;
    }
}
