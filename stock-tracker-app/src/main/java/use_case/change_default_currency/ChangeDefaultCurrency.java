package use_case.change_default_currency;

import data_access.APIDataAccessObject;
import data_access.FileDataAccessObject;
import entity.Currency;
import entity.Portfolio;

public class ChangeDefaultCurrency {

    private final APIDataAccessObject apiDataAccessObject;
    private final FileDataAccessObject fileDataAccessObject;


    public ChangeDefaultCurrency(APIDataAccessObject apiDataAccessObject, FileDataAccessObject fileDataAccessObject) {
        this.apiDataAccessObject = apiDataAccessObject;
        this.fileDataAccessObject = fileDataAccessObject;
    }

    public void execute (Portfolio portfolio, Currency Newcurrency){
        portfolio.setCurrency(Newcurrency);
    }
}
