package use_case.view_price_history;

import data_access.FileDataAccessObject;
import entity.Portfolio;
import entity.Tradeable;

import java.util.Date;
import java.util.TreeMap;

public class ViewPriceHistoryInteractor implements ViewPriceHistoryInputBoundary {
    private final FileDataAccessObject fileDataAccessObject;
    private ViewPriceHistoryOutputBoundary presenter;

    public ViewPriceHistoryInteractor(FileDataAccessObject fileDataAccessObject, ViewPriceHistoryOutputBoundary presenter) {
        this.fileDataAccessObject = fileDataAccessObject;
        this.presenter = presenter;
    }

    @Override
    public void execute(String portfolioName, String assetName) {
        Portfolio portfolio = fileDataAccessObject.getPortfolio(portfolioName);
        String defaultCurrency = portfolio.getCurrency().getSymbol();
        Tradeable asset = portfolio.getHoldings().get(assetName);
        TreeMap<Date, Double> priceHistory = asset.getPriceHistory();

        ViewPriceHistoryOutputData outputData = new ViewPriceHistoryOutputData(portfolioName, assetName, defaultCurrency, priceHistory);
        presenter.present(outputData);
    }
}
