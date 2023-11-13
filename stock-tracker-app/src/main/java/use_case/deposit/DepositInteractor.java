package use_case.deposit;

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
}
