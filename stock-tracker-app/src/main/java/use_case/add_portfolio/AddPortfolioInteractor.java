package use_case.add_portfolio;

import entity.Portfolio;
import data_access.FileDataAccessObject;
import java.util.List;

public class AddPortfolioInteractor {
    private final FileDataAccessObject fileDataAccessObject;

    public AddPortfolioInteractor(FileDataAccessObject fileDataAccessObject) {
        this.fileDataAccessObject = fileDataAccessObject;
    }

    public void execute(String portfolioName) {
        // TODO: Handle case where portfolioName is already taken
        List<Portfolio> portfolios = fileDataAccessObject.loadPortfolios();
        for (Portfolio ptf : portfolios) {
            if (portfolioName.equals(ptf.getName())) {
                throw new IllegalArgumentException("Portfolio name already used");
            }
        }

        Portfolio portfolio = new Portfolio(portfolioName);
        fileDataAccessObject.savePortfolio(portfolio);
    }
}
