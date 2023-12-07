package entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Transaction {
    final double tradingFee;

    public Transaction(Double tradingFee) {
        this.tradingFee = tradingFee;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd, HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
    }
}
