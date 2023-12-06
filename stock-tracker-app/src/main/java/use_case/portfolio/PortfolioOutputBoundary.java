package use_case.portfolio;

public interface PortfolioOutputBoundary {
    void prepareSuccessView(PortfolioOutputData portfolioName);
    void prepareFailView(String error);
}
