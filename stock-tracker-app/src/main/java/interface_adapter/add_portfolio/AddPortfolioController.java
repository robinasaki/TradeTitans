package interface_adapter.add_portfolio;

import use_case.add_portfolio.AddPortfolioInputBoundary;
import use_case.add_portfolio.AddPortfolioInputData;

public class AddPortfolioController {
    private final AddPortfolioInputBoundary addPortfolioInteractor;

    public AddPortfolioController(AddPortfolioInputBoundary addPortfolioInteractor) {
        this.addPortfolioInteractor = addPortfolioInteractor;
    }

    public void execute(String portfolioName, String defaultCurrency) {
        addPortfolioInteractor.execute(portfolioName, defaultCurrency);
    }
}
