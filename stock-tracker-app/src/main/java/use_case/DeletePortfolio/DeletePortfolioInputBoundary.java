package use_case.DeletePortfolio;

import entity.Portfolio;

public interface DeletePortfolioInputBoundary {
    void execute(String portfolioName);
}
