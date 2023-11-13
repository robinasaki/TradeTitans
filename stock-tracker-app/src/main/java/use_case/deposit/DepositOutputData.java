package use_case.deposit;

import entity.BankingTransaction;

public class DepositOutputData {
    private boolean useCaseFailed;
    private double amountAdded;

    private BankingTransaction bankingTransaction;

    public DepositOutputData(boolean useCaseFailed, double amountAdded, BankingTransaction bankingTransaction) {
        this.useCaseFailed = useCaseFailed;
        this.amountAdded = amountAdded;
        this.bankingTransaction = bankingTransaction;
    }

    public double getAmountAdded() {
        return amountAdded;
    }

    public BankingTransaction getBankingTransaction() {
        return bankingTransaction;
    }
}
