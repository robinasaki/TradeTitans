package interface_adapter;

import use_case.deposit.DepositInputBoundary;
import use_case.deposit.DepositInputData;

public class DepositController {
    final DepositInputBoundary depositInteractor;

    public DepositController(DepositInputBoundary depositInteractor){
        this.depositInteractor = depositInteractor;
    }

    public void execute(double amount){
        DepositInputData depositInputData = new DepositInputData(amount);
        depositInteractor.execute(depositInputData);
    }
}
