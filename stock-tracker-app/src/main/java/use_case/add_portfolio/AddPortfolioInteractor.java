package use_case.add_portfolio;

import use_case.FileDataAccessInterface;
import entity.Portfolio;
import entity.Tradeable;

import java.util.List;
import java.util.Date;
import java.util.TreeMap;
import java.time.LocalDate;
import java.time.ZoneId;

public class AddPortfolioInteractor implements AddPortfolioInputBoundary {
    private final FileDataAccessInterface fileDataAccessObject;
    private AddPortfolioOutputBoundary presenter;

    public AddPortfolioInteractor(FileDataAccessInterface fileDataAccessObject, AddPortfolioOutputBoundary addPortfolioPresenter) {
        this.fileDataAccessObject = fileDataAccessObject;
        this.presenter = addPortfolioPresenter;
    }

    public void execute(String portfolioName, String defaultCurrency) {
        List<Portfolio> portfolios = fileDataAccessObject.loadPortfolios();
        for (Portfolio ptf : portfolios) {
            if (portfolioName.equals(ptf.getName())) {
                throw new RuntimeException("Portfolio name already used. Please try another name");
            }
        }

        // getting price history for default currency (all 1.0)
        Tradeable currency = new Tradeable("currency", defaultCurrency);
        TreeMap<Date, Double> quotes = new TreeMap<>();
        LocalDate date = LocalDate.of(1900, 1, 1);
        while (date.isBefore(LocalDate.now())) {
            quotes.put(Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()), 1.0);
            date = date.plusDays(1);
        }

        Portfolio portfolio = new Portfolio(portfolioName, currency);
        portfolio.addAsset(currency.getSymbol());
        portfolio.getHoldings().get(currency.getSymbol()).setPriceHistory(quotes);
        fileDataAccessObject.savePortfolio(portfolio);
        presenter.prepareSuccessView(portfolio.getName());
    }
}
