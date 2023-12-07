package interface_adapter.add_portfolio;

import interface_adapter.ViewManagerModel;
import interface_adapter.portfolio_selection.PortfolioSelectionViewModel;
import use_case.add_portfolio.AddPortfolioOutputBoundary;

import java.util.List;

public class AddPortfolioPresenter implements AddPortfolioOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final PortfolioSelectionViewModel portfolioSelectionViewModel;

    public AddPortfolioPresenter(ViewManagerModel viewManagerModel, PortfolioSelectionViewModel portfolioSelectionViewModel) {
        this.portfolioSelectionViewModel = portfolioSelectionViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(String portfolioName) {
        // These would be different if we use a state for portfolio selection
        List<String> portfolioNames = portfolioSelectionViewModel.getPortfolioNames();
        portfolioNames.add(portfolioName);
        portfolioSelectionViewModel.setPortfolioNames(portfolioNames);
        viewManagerModel.setActiveView("portfolio_selection");
    }

    @Override
    public void prepareFailView(String error) {

    }
}
