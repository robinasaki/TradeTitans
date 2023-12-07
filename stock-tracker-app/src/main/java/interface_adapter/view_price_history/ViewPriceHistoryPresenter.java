package interface_adapter.view_price_history;

import interface_adapter.ViewManagerModel;
import use_case.view_price_history.ViewPriceHistoryOutputBoundary;
import use_case.view_price_history.ViewPriceHistoryOutputData;

public class ViewPriceHistoryPresenter implements ViewPriceHistoryOutputBoundary {
    private PriceHistoryViewModel priceHistoryViewModel;
    private ViewManagerModel viewManagerModel;

    public ViewPriceHistoryPresenter(PriceHistoryViewModel priceHistoryViewModel, ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
        this.priceHistoryViewModel = priceHistoryViewModel;
    }

    @Override
    public void present(ViewPriceHistoryOutputData outputData) {
        PriceHistoryState state = new PriceHistoryState();
        state.setPortfolioName(outputData.getPortfolioName());
        state.setAssetName(outputData.getAssetName());
        state.setDefaultCurrency(outputData.getDefaultCurrency());
        state.setPriceHistory(outputData.getPriceHistory());

        priceHistoryViewModel.setState(state);
        viewManagerModel.setActiveView("price_history");
    }
}
