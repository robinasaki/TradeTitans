package use_case.add_portfolio;

import entity.Portfolio;

import data_access.FileDataAccessObject;

public class AddPortfolioInteractor {
    private final FileDataAccessObject fileDataAccessObject;

    public AddPortfolioInteractor(FileDataAccessObject fileDataAccessObject) {
        this.fileDataAccessObject = fileDataAccessObject;
    }

    public void execute(String portfolioName) {
        // TODO: Handle case where portfolioName is already taken
        Portfolio portfolio = new Portfolio(portfolioName);
        fileDataAccessObject.savePortfolio(portfolio);
    }
}
