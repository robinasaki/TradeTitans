package use_case.delete_portfolio;

import entity.Portfolio;

public interface DeletePortfolioInputBoundary {
    void execute(String portfolioName);
}
