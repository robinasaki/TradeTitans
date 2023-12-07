package use_case.delete_portfolio;

import use_case.FileDataAccessInterface;

public class DeletePortfolioInteractor implements DeletePortfolioInputBoundary{
    private final FileDataAccessInterface fileDataAccessObject;
    private final DeletePortfolioOutputBoundary presenter;

    public DeletePortfolioInteractor(FileDataAccessInterface fileDataAccessObject, DeletePortfolioOutputBoundary presenter) {
        this.fileDataAccessObject = fileDataAccessObject;
        this.presenter = presenter;
    }

    public void execute(String portfolioName){
        fileDataAccessObject.removePortfolio(portfolioName);
        presenter.prepareSuccessView(portfolioName);

    }
}
