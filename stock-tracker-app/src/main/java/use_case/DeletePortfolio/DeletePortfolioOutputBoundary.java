package use_case.DeletePortfolio;

import entity.Portfolio;

public interface DeletePortfolioOutputBoundary {
    void prepareSuccessView(String PortfolioName);
}
