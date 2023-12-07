package use_case.add_portfolio;

public class AddPortfolioOutputData {
    private boolean useCaseFailed;
    private boolean portfolioCreated;

    public AddPortfolioOutputData(boolean useCaseFailed, boolean portfolioCreated) {
        this.useCaseFailed = useCaseFailed;
        this.portfolioCreated = portfolioCreated;
    }

    public boolean getSuccessfulCreation() {return portfolioCreated;}

    public boolean getUseCaseFailed() {
        return useCaseFailed;
    }
}
