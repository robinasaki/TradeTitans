package use_case.view_transactions;

import data_access.FileDataAccessObject;
import entity.Portfolio;
import entity.TradeTransaction;
import use_case.FileDataAccessInterface;

import java.util.ArrayList;
import java.util.Date;

public class ViewTransactionsInteractor implements ViewTransactionsInputBoundary {
    private final FileDataAccessInterface fileDataAccessObject;
    private ViewTransactionsOutputBoundary presenter;

    public ViewTransactionsInteractor(FileDataAccessInterface fileDataAccessObject, ViewTransactionsOutputBoundary presenter) {
        this.fileDataAccessObject = fileDataAccessObject;
        this.presenter = presenter;
    }

    @Override
    public void execute(String portfolioName) {
        Portfolio portfolio = fileDataAccessObject.getPortfolio(portfolioName);
        String defaultCurrency = portfolio.getCurrency().getSymbol();
        ArrayList<TradeTransaction> transactions = portfolio.getTransactions();

        ArrayList<String> assetsIn = new ArrayList<String>();
        ArrayList<String> assetsOut = new ArrayList<String>();
        ArrayList<Double> amountsIn = new ArrayList<Double>();
        ArrayList<Double> amountsOut = new ArrayList<Double>();
        ArrayList<Date> dates = new ArrayList<Date>();

        for(TradeTransaction transaction : transactions) {
            assetsIn.add(transaction.getAssetIn());
            assetsOut.add(transaction.getAssetOut());
            amountsIn.add(transaction.getAmountIn());
            amountsOut.add(transaction.getAmountOut());
            dates.add(transaction.getDate());
        }

        ViewTransactionsOutputData outputData = new ViewTransactionsOutputData(portfolioName, defaultCurrency, assetsIn, assetsOut, amountsIn, amountsOut, dates);
        presenter.present(outputData);
    }
}
