package use_case.deposit;

public class DepositOuputData {
    private boolean useCaseFailed;
    private double amountAdded;

    public DepositOuputData(boolean useCaseFailed, double amountAdded){
        this.useCaseFailed = useCaseFailed;
        this.amountAdded = amountAdded;
    }
}
