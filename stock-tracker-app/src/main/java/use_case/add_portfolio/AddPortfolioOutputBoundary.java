package use_case.add_portfolio;

public interface AddPortfolioOutputBoundary {
    void prepareSuccessView(AddPortfolioOutputData user);

    void prepareFailView(String error);
}
