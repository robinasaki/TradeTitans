package interface_adapter;

public class DepositState {
    private double amount = 0.00;
    private String negativeDepositError = null;

    public DepositState(DepositState copy) {
        this.amount = copy.amount;
        this.negativeDepositError = copy.negativeDepositError;
    }

    public DepositState() {
    }

    public double getAmount() {
        return this.amount;
    }

    public String getNegativeDepositError() {
        return this.negativeDepositError;
    }
}
