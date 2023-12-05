package use_case.delete_portfolio;

public class DeletePortfolioInteractor implements DeletePortfolioInputBoundary{
    private final DeletePortfolioDataAccessInterface filedataaccessinterface;
    private final DeletePortfolioOutputBoundary presenter;

    public DeletePortfolioInteractor(DeletePortfolioDataAccessInterface filedataaccessinterface, DeletePortfolioOutputBoundary presenter) {
        this.filedataaccessinterface = filedataaccessinterface;
        this.presenter = presenter;
    }

    public void execute(String portfolioName){
        filedataaccessinterface.removePortfolio(portfolioName);
        presenter.prepareSuccessView(portfolioName);

    }
}
