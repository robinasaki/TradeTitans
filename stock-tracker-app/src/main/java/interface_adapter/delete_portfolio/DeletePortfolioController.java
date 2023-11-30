package interface_adapter.delete_portfolio;

import entity.Portfolio;
import use_case.DeletePortfolio.DeletePortfolioInputBoundary;

public class DeletePortfolioController {
    private final DeletePortfolioInputBoundary deletePortfolioInteractor;

    public DeletePortfolioController(DeletePortfolioInputBoundary deletePortfolioInteractor) {
        this.deletePortfolioInteractor = deletePortfolioInteractor;
    }

    public void execute(String portfolioName){
        deletePortfolioInteractor.execute(portfolioName);
    }
}
