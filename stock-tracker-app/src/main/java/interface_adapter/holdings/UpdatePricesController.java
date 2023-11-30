package interface_adapter.holdings;

import use_case.update_prices.UpdatePricesInputBoundary;

public class UpdatePricesController {
    final UpdatePricesInputBoundary updatePricesInteractor;

    public UpdatePricesController(UpdatePricesInputBoundary updatePricesInteractor) {
        this.updatePricesInteractor = updatePricesInteractor;
    }

    public void execute(String portfolioName) {
        updatePricesInteractor.execute(portfolioName);
    }
}
