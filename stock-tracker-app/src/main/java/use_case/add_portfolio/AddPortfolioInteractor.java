package use_case.add_portfolio;

import use_case.add_portfolio.AddPortfolioOutputBoundary;
import entity.Portfolio;
import entity.Tradeable;
import data_access.FileDataAccessObject;

import java.util.List;

public class AddPortfolioInteractor implements AddPortfolioInputBoundary {
    private final FileDataAccessObject fileDataAccessObject;
    private AddPortfolioOutputBoundary presenter;

    public AddPortfolioInteractor(FileDataAccessObject fileDataAccessObject, AddPortfolioOutputBoundary addPortfolioPresenter) {
        this.fileDataAccessObject = fileDataAccessObject;
        this.presenter = addPortfolioPresenter;
    }

    public void execute(String portfolioName, String defaultCurrency) {
        List<Portfolio> portfolios = fileDataAccessObject.loadPortfolios();
        for (Portfolio ptf : portfolios) {
            if (portfolioName.equals(ptf.getName())) {
                throw new IllegalArgumentException("Portfolio name already used. Please try another name");
            }
        }

        Tradeable currency = new Tradeable("currency", defaultCurrency);
        Portfolio portfolio = new Portfolio(portfolioName, currency);
        portfolio.addAsset(currency.getSymbol());
        fileDataAccessObject.savePortfolio(portfolio);
        presenter.prepareSuccessView(portfolio.getName());
    }
}
