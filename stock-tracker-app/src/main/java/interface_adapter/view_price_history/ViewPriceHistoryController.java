package interface_adapter.view_price_history;

import use_case.view_price_history.ViewPriceHistoryInputBoundary;

public class ViewPriceHistoryController {
    final ViewPriceHistoryInputBoundary viewPriceHistoryInteractor;

    public ViewPriceHistoryController(ViewPriceHistoryInputBoundary viewPriceHistoryInteractor) {
        this.viewPriceHistoryInteractor = viewPriceHistoryInteractor;
    }

    public void execute(String portfolioName, String assetName) {
        viewPriceHistoryInteractor.execute(portfolioName, assetName);
    }
}
