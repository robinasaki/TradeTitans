package use_case.update_prices;

import data_access.APIDataAccessObject;
import data_access.FileDataAccessObject;
import java.util.Date;

import entity.Portfolio;
import entity.Tradeable;


public class UpdatePricesInteractor {
    private final FileDataAccessObject fileDataAccessObject;
    private final APIDataAccessObject apiDataAccessObject;

    public UpdatePricesInteractor(FileDataAccessObject fileDataAccessObject, APIDataAccessObject apiDataAccessObject) {
        this.fileDataAccessObject = fileDataAccessObject;
        this.apiDataAccessObject = apiDataAccessObject;
    }


    // Note that this only updates prices for one portfolio
     public void execute(String portfolioName) {
        Portfolio portfolio = fileDataAccessObject.getPortfolio(portfolioName);

        for(String holding : portfolio.getHoldings().keySet()) {
            // TODO: fix the code below
            holding.setPriceHistory(apiDataAccessObject.getHistoricalQuotes(holding, portfolio.getCurrency().getSymbol()));
        }

        fileDataAccessObject.savePortfolio(portfolio);
    }
}
