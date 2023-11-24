package interface_adapter.trade;

import interface_adapter.ViewManagerModel;
import use_case.trade.TradeOutputBoundary;
import use_case.trade.TradeOutputData;

public class TradePresenter implements TradeOutputBoundary {
    private final TradeViewModel tradeViewModel;
    private ViewManagerModel viewManagerModel;

    public TradePresenter(TradeViewModel tradeViewModel, ViewManagerModel viewManagerModel) {
        this.tradeViewModel = tradeViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(TradeOutputData user) {
        TradeState tradeState = tradeViewModel.getState();
        this.tradeViewModel.setState(tradeState);
        viewManagerModel.setActiveView(tradeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        TradeState tradeState = tradeViewModel.getState();
        tradeState.getNotTradeableError();
        tradeViewModel.firePropertyChanged();
    }
}
