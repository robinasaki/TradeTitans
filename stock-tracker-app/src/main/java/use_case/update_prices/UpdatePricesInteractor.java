package use_case.update_prices;

import data_access.APIDataAccessObject;
import data_access.FileDataAccessObject;
import java.util.List;
import java.util.ArrayList;

import entity.Portfolio;
import entity.Tradeable;


public class UpdatePricesInteractor implements UpdatePricesInputBoundary {
    private final FileDataAccessObject fileDataAccessObject;
    private final APIDataAccessObject apiDataAccessObject;
    private final UpdatePricesOutputBoundary presenter;

    public UpdatePricesInteractor(FileDataAccessObject fileDataAccessObject, APIDataAccessObject apiDataAccessObject, UpdatePricesOutputBoundary presenter) {
        this.fileDataAccessObject = fileDataAccessObject;
        this.apiDataAccessObject = apiDataAccessObject;
        this.presenter = presenter;
    }


    // Note that this only updates prices for one portfolio
     public void execute(String portfolioName) {
        Portfolio portfolio = fileDataAccessObject.getPortfolio(portfolioName);
        ArrayList<String> holdings = new ArrayList<>();
        ArrayList<Double> prices = new ArrayList<>();
        ArrayList<Double> shares = new ArrayList<>();
        ArrayList<Double> values = new ArrayList<>();

        for(String holding : portfolio.getHoldings().keySet()) {
            Tradeable assetTradeable = Tradeable.getTradeable(holding);
            assetTradeable.setPriceHistory(apiDataAccessObject.getHistoricalQuotes(holding, portfolio.getCurrency().getSymbol()));
            holdings.add(holding);
            prices.add(assetTradeable.getCurrentPrice());
            shares.add(portfolio.getHoldings().get(holding));
            values.add(assetTradeable.getCurrentPrice() * portfolio.getHoldings().get(holding));
        }
        holdings.add("Total");
        values.add(portfolio.getPortfolioValue());

        fileDataAccessObject.savePortfolio(portfolio);
        UpdatePricesOutputData outputData = new UpdatePricesOutputData(holdings, prices, shares, values);
        presenter.present(outputData);
    }
}
