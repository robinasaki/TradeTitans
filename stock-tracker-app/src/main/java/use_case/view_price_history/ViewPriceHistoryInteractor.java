package use_case.view_price_history;

import entity.Portfolio;
import entity.TradeTransaction;
import entity.Tradeable;
import use_case.FileDataAccessInterface;

import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;

public class ViewPriceHistoryInteractor implements ViewPriceHistoryInputBoundary {
    private final FileDataAccessInterface fileDataAccessObject;
    private ViewPriceHistoryOutputBoundary presenter;

    public ViewPriceHistoryInteractor(FileDataAccessInterface fileDataAccessObject, ViewPriceHistoryOutputBoundary presenter) {
        this.fileDataAccessObject = fileDataAccessObject;
        this.presenter = presenter;
    }

    @Override
    public void execute(String portfolioName, String assetName) {
        Portfolio portfolio = fileDataAccessObject.getPortfolio(portfolioName);
        String defaultCurrency = portfolio.getCurrency().getSymbol();
        TreeMap<Date, Double> priceHistory;
        if (assetName == "Total") {
            priceHistory = portfolio.getPriceHistory();
            for (Date date : priceHistory.keySet()) {
                System.out.println(date + " " + priceHistory.get(date));
            }
        } else {
            Tradeable asset = portfolio.getHoldings().get(assetName);
            priceHistory = asset.getPriceHistory();
        }
        ViewPriceHistoryOutputData outputData = new ViewPriceHistoryOutputData(portfolioName, assetName, defaultCurrency, priceHistory);
        presenter.present(outputData); 
    }
}
