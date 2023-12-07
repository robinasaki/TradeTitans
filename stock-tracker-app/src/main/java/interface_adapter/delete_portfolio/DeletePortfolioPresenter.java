package interface_adapter.delete_portfolio;

import interface_adapter.ViewManagerModel;
import interface_adapter.portfolio_selection.PortfolioSelectionViewModel;
import use_case.delete_portfolio.DeletePortfolioOutputBoundary;

import java.util.List;

public class DeletePortfolioPresenter implements DeletePortfolioOutputBoundary {

    private ViewManagerModel viewManagerModel;
    private PortfolioSelectionViewModel portfolioSelectionViewModel;

    public DeletePortfolioPresenter(ViewManagerModel viewManagerModel,
                                    PortfolioSelectionViewModel portfolioSelectionViewModel){
        this.portfolioSelectionViewModel = portfolioSelectionViewModel;
        this.viewManagerModel = viewManagerModel;
    }
    @Override
    public void prepareSuccessView(String PortfolioName) {
        List<String> portfolioNames = portfolioSelectionViewModel.getPortfolioNames();
        portfolioNames.remove(PortfolioName);
        portfolioSelectionViewModel.setPortfolioNames(portfolioNames);
        portfolioSelectionViewModel.firePropertyChanged();

        viewManagerModel.setActiveView("portfolio_selection");

    }

}
