package use_case.DeletePortfolio;

import entity.Portfolio;
import data_access.FileDataAccessObject;

public class DeletePortfolioInteractor {
    private final FileDataAccessObject fileDataAccessObject;

    public DeletePortfolioInteractor(FileDataAccessObject fileDataAccessObject) {
        this.fileDataAccessObject = fileDataAccessObject;
    }

    public void execute(String portfolioName){

        fileDataAccessObject.removePortfolio(portfolioName);
    }
}
