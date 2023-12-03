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
    public void present(TradeOutputData tradeOutputData) {
        holdingsViewModel.getState().setSymbols(tradeOutputData.getSymbols());
        holdingsViewModel.getState().setPrices(tradeOutputData.getPrices());
        holdingsViewModel.getState().setShares(tradeOutputData.getShares());
        holdingsViewModel.getState().setValues(tradeOutputData.getValues());
        holdingsViewModel.getState().setChanges(tradeOutputData.getChanges());
        holdingsViewModel.getState().setChangePercents(tradeOutputData.getChangePercents());

        // TODO: this is a hack to get the holdings view model to update
        // TODO: done by calling setState() which has a side effect of firing a property changed event
        holdingsViewModel.setState(holdingsViewModel.getState());

        viewManagerModel.setActiveView("holdings");
    }

    @Override
    public void prepareFailView(String error) {
        //TradeState tradeState = tradeViewModel.getState();
        //tradeState.getNotTradeableError();
        //tradeViewModel.firePropertyChanged();
    }
}
