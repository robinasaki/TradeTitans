package use_case.delete_portfolio;

import entity.Portfolio;
import data_access.FileDataAccessObject;

public class DeletePortfolioInteractor implements DeletePortfolioInputBoundary{
    private final FileDataAccessObject fileDataAccessObject;
    private final DeletePortfolioOutputBoundary presenter;

    public DeletePortfolioInteractor(FileDataAccessObject fileDataAccessObject, DeletePortfolioOutputBoundary presenter) {
        this.fileDataAccessObject = fileDataAccessObject;
        this.presenter = presenter;
    }

    public void execute(String portfolioName){
        fileDataAccessObject.removePortfolio(portfolioName);
        presenter.prepareSuccessView(portfolioName);
    }
}
