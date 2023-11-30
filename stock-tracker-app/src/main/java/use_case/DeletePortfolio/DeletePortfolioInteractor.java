package use_case.DeletePortfolio;

import entity.Portfolio;
import data_access.FileDataAccessObject;

public class DeletePortfolioInteractor {
    private final FileDataAccessObject fileDataAccessObject;

    public DeletePortfolioInteractor(FileDataAccessObject fileDataAccessObject) {
        this.fileDataAccessObject = fileDataAccessObject;
    }

    public void execute(Portfolio portfolio){
        fileDataAccessObject.removePortfolio(portfolio.getName());
    }
}
