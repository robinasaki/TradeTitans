package interface_adapter.holdings;

import use_case.update_prices.UpdatePricesOutputBoundary;
import use_case.update_prices.UpdatePricesOutputData;
import interface_adapter.ViewManagerModel;

public class UpdatePricesPresenter implements UpdatePricesOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final HoldingsViewModel holdingsViewModel;

    public UpdatePricesPresenter(ViewManagerModel viewManagerModel, HoldingsViewModel holdingsViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.holdingsViewModel = holdingsViewModel;
    }

    public void present(UpdatePricesOutputData outputData) {
        
        HoldingsState state = new HoldingsState();
        state.setPortfolioName(outputData.getPortfolioName());
        state.setDefaultCurrency(outputData.getDefaultCurrency());
        state.setSymbols(outputData.getSymbols());
        state.setPrices(outputData.getPrices());
        state.setShares(outputData.getShares());
        state.setValues(outputData.getValues());
        state.setChanges(outputData.getChanges());
        state.setChangePercents(outputData.getChangePercents());
        holdingsViewModel.setState(state);
        viewManagerModel.setActiveView("holdings");
    }
}
