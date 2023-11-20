package use_case.ChangeDefaultCurrency;

import data_access.APIDataAccessObject;
import data_access.FileDataAccessObject;
import entity.Currency;
import entity.Portfolio;

public class ChangeDefaultCurrency {

    private final FileDataAccessObject fileDataAccessObject;
    private final APIDataAccessObject apiDataAccessObject;

    public ChangeDefaultCurrency(FileDataAccessObject fileDataAccessObject, APIDataAccessObject apiDataAccessObject) {
        this.fileDataAccessObject = fileDataAccessObject;
        this.apiDataAccessObject = apiDataAccessObject;
    }
    public void execute (Portfolio portfolio, Currency NewCurrency){
        portfolio.SetCurrency(NewCurrency);
    }
}
