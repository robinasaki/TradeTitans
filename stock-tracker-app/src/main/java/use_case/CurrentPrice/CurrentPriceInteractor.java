package use_case.CurrentPrice;

import data_access.APIDataAccessObject;
import data_access.FileDataAccessObject;

public class CurrentPriceInteractor implements CurrentpriceInputBoundary{

    private final APIDataAccessObject apiDataAccessObject;

    public CurrentPriceInteractor (APIDataAccessObject apiDataAccessObject){
        this.apiDataAccessObject = apiDataAccessObject;
    }

    @Override
    public void execute(CurrentPriceInputData currentPriceInputData) {

    }
}
