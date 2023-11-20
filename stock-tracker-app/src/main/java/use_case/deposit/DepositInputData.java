package use_case.deposit;

import entity.Currency;

public class DepositInputData {
    private final double amount;

    public DepositInputData(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return this.amount;
    }
}
