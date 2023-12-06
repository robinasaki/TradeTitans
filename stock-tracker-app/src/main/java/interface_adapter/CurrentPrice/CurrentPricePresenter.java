package interface_adapter.CurrentPrice;

import interface_adapter.ViewManagerModel;
import interface_adapter.trade.TradeViewModel;
import use_case.CurrentPrice.CurrentPriceOutputBoundary;
import use_case.CurrentPrice.CurrentPriceOutputData;

public class CurrentPricePresenter implements CurrentPriceOutputBoundary {

    private final TradeViewModel tradeViewModel;
    private final ViewManagerModel viewManagerModel;

    public CurrentPricePresenter(TradeViewModel tradeViewModel,
                                 ViewManagerModel viewManagerModel) {
        this.tradeViewModel = tradeViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void PrepareSuccessView(double rate) {
        tradeViewModel.getState().setRate(rate);
        viewManagerModel.firePropertyChanged();
    }
}
