package use_case.update_prices;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Date;

import entity.Portfolio;
import entity.Tradeable;
import use_case.APIDataAccessInterface;
import use_case.FileDataAccessInterface;


public class UpdatePricesInteractor implements UpdatePricesInputBoundary {
    private final APIDataAccessInterface apiDataAccessObject;
    private final FileDataAccessInterface fileDataAccessObject;
    private final UpdatePricesOutputBoundary presenter;

    public UpdatePricesInteractor(FileDataAccessInterface fileDataAccessObject, APIDataAccessInterface apiDataAccessObject, UpdatePricesOutputBoundary presenter) {
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
        ArrayList<Double> changes = new ArrayList<>();
        ArrayList<Double> changePercents = new ArrayList<>();

        for(String holding : portfolio.getHoldings().keySet()) {
            Tradeable assetTradeable = portfolio.getHoldings().get(holding);
            TreeMap<Date, Double> priceHistory = apiDataAccessObject.getHistoricalQuotes(holding, portfolio.getCurrency().getSymbol());
            assetTradeable.setPriceHistory(priceHistory);

            holdings.add(assetTradeable.getSymbol());
            prices.add(assetTradeable.getCurrentPrice());
            shares.add(assetTradeable.getSharesHeld());
            values.add(assetTradeable.getCurrentValue());
            changes.add(assetTradeable.getCurrentPrice() - assetTradeable.getPreviousPrice());
            changePercents.add((assetTradeable.getCurrentPrice() - assetTradeable.getPreviousPrice()) / assetTradeable.getPreviousPrice() * 100);

        }
        holdings.add("Total");
        prices.add(0.0);
        shares.add(0.0);
        values.add(portfolio.getPortfolioValue());

        double totalChange = 0;
        for (int i = 0; i < changes.size(); i++) {
            totalChange += changes.get(i) * shares.get(i);
        }
        changes.add(totalChange);

        if (portfolio.getPortfolioValue() - totalChange == 0) {
            changePercents.add(0.0);
        }
        else {
            changePercents.add(totalChange / (portfolio.getPortfolioValue() - totalChange) * 100);
        }

        String defaultCurrency = portfolio.getCurrency().getSymbol();

        fileDataAccessObject.savePortfolio(portfolio);
        UpdatePricesOutputData outputData = new UpdatePricesOutputData(portfolioName, defaultCurrency, holdings, prices, shares, values, changes, changePercents);
        presenter.present(outputData);
    }
}
