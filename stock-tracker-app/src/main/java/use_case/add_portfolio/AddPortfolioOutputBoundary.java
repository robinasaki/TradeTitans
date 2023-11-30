package use_case.add_portfolio;

public interface AddPortfolioOutputBoundary {
    void prepareSuccessView(String portfolioName);

    void prepareFailView(String error);
}
