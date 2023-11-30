package interface_adapter.trade;

import interface_adapter.ViewManagerModel;
import use_case.trade.TradeOutputBoundary;
import use_case.trade.TradeOutputData;
import interface_adapter.holdings.HoldingsViewModel;

public class TradePresenter implements TradeOutputBoundary {
    private final HoldingsViewModel holdingsViewModel;
    private final ViewManagerModel viewManagerModel;

    public TradePresenter(ViewManagerModel viewManagerModel, HoldingsViewModel holdingsViewModel) {
        this.holdingsViewModel = holdingsViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void present() {
        // TODO: could be nice to update holdingsState here
        viewManagerModel.setActiveView("holdings");
    }

    @Override
    public void prepareFailView(String error) {
        //TradeState tradeState = tradeViewModel.getState();
        //tradeState.getNotTradeableError();
        //tradeViewModel.firePropertyChanged();
    }
}
