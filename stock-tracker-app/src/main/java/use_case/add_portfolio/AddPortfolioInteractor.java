package use_case.add_portfolio;

import entity.Portfolio;
import data_access.FileDataAccessObject;
import entity.Tradeable;

import java.util.List;

public class AddPortfolioInteractor {
    private final FileDataAccessObject fileDataAccessObject;

    public AddPortfolioInteractor(FileDataAccessObject fileDataAccessObject) {
        this.fileDataAccessObject = fileDataAccessObject;
    }

    public void execute(String portfolioName, Tradeable defaultCurrency) {
        List<Portfolio> portfolios = fileDataAccessObject.loadPortfolios();
        for (Portfolio ptf : portfolios) {
            if (portfolioName.equals(ptf.getName())) {
                throw new IllegalArgumentException("Portfolio name already used. Please try another name");
            }
        }

        Portfolio portfolio = new Portfolio(portfolioName, defaultCurrency);
        fileDataAccessObject.savePortfolio(portfolio);
    }
}
