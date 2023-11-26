package use_case.portfolio;

public interface portfolioOutputBoundary {
    void prepareSuccessView(portfolioOutputData portfolioName);
    void prepareFailView(String error);
}
