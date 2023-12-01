package use_case.delete_portfolio;

import entity.Portfolio;

public interface DeletePortfolioOutputBoundary {
    void prepareSuccessView(String PortfolioName);
}
