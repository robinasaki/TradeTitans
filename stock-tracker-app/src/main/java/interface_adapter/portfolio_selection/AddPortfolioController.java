package interface_adapter.portfolio_selection;

import use_case.add_portfolio.AddPortfolioInputBoundary;

public class AddPortfolioController {
    private final AddPortfolioInputBoundary addPortfolioInteractor;

    public AddPortfolioController(AddPortfolioInputBoundary addPortfolioInteractor) {
        this.addPortfolioInteractor = addPortfolioInteractor;
    }

    public void execute(String portfolioName, String defaultCurrency) {
        addPortfolioInteractor.execute(portfolioName, defaultCurrency);
    }
}
