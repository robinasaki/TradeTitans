package use_case.view_transactions;

import entity.Portfolio;

public interface ViewTransactionDataAccessInterface {
    Portfolio getPortfolio(String portfolioName);

}
