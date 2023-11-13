package use_case.deposit;

public interface DepositOutputBoundary {
    void prepareSuccessView(DepositOutputData user);

    void prepareFailView(String error);
}
