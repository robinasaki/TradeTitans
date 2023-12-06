package use_case.CurrentPrice;

import data_access.APIDataAccessObject;

import interface_adapter.CurrentPrice.CurrentPricePresenter;
import interface_adapter.trade.TradePresenter;

import java.util.Date;
import java.util.TreeMap;

public class CurrentPriceInteractor implements CurrentpriceInputBoundary{

    private final APIDataAccessObject apiDataAccessObject;

    private final CurrentPricePresenter presenter;

    public CurrentPriceInteractor (APIDataAccessObject apiDataAccessObject,
                                   CurrentPricePresenter presenter){
        this.apiDataAccessObject = apiDataAccessObject;
        this.presenter = presenter;
    }

    @Override
    public void execute(CurrentPriceInputData currentPriceInputData) {
        TreeMap<Date, Double> treemap = apiDataAccessObject.getHistoricalQuotes(currentPriceInputData.getAssetInSymbol(), currentPriceInputData.getAssetOutSymbol());
        double rate = treemap.lastEntry().getValue();
        //CurrentPriceOutputData currentPriceOutputData = new CurrentPriceOutputData(rate);
        presenter.PrepareSuccessView(rate);

    }
}
