package use_case.deposit;

import entity.BankingTransaction;
import entity.UserFactory;

public class DepositInteractor implements DepositInputBoundary {
    final DepositDataAccessInterface dataAccessObject;
    final DepositOutputBoundary userPresenter;
    final UserFactory userFactory;

    public DepositInteractor(DepositDataAccessInterface dataAccessObject, DepositOutputBoundary userPresenter, UserFactory userFactory) {
        this.dataAccessObject = dataAccessObject;
        this.userPresenter = userPresenter;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(DepositInputData depositInputData) {
        if (dataAccessObject.negativeDeposit()) {
            userPresenter.prepareFailView("Negative deposit not allowed.");
        } else {
            BankingTransaction bankingTransaction = new BankingTransaction(0, null, true, depositInputData.getAmount());
            // #TODO: fix trading fee
            DepositOutputData depositOutputData = new DepositOutputData(false, depositInputData.getAmount(), bankingTransaction);
            userPresenter.prepareSuccessView(depositOutputData);
        }
    }
}
