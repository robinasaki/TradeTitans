package interface_adapter.add_portfolio;

import interface_adapter.ViewManagerModel;
import interface_adapter.trade.TradePresenter;
import use_case.add_portfolio.AddPortfolioOutputBoundary;
import use_case.add_portfolio.AddPortfolioOutputData;

public class AddPortfolioPresenter implements AddPortfolioOutputBoundary {
    private final AddPortfolioViewModel addPortfolioViewModel;
    private ViewManagerModel viewManagerModel;

    public AddPortfolioPresenter(AddPortfolioViewModel addPortfolioViewModel, ViewManagerModel viewManagerModel) {
        this.addPortfolioViewModel = addPortfolioViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(AddPortfolioOutputData user) {
        AddPortfolioState addPortfolioState = addPortfolioViewModel.getState();
        this.addPortfolioViewModel.setState(addPortfolioState);
        viewManagerModel.setActiveView(addPortfolioViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        AddPortfolioState addPortfolioState = addPortfolioViewModel.getState();
        // TODO: COMPLETE THIS -> addPortfolioState.
        addPortfolioViewModel.firePropertyChanged();
    }
}
