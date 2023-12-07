package use_case.view_transactions;

import java.util.ArrayList;
import java.util.Date;

public class ViewTransactionsOutputData {
    private String portfolioName;
    private String defaultCurrency;
    private ArrayList<String> assetsIn;
    private ArrayList<String> assetsOut;
    private ArrayList<Double> amountsIn;
    private ArrayList<Double> amountsOut;
    private ArrayList<Date> dates;

    public ViewTransactionsOutputData(String portfolioName, String defaultCurrency, ArrayList<String> assetsIn, ArrayList<String> assetsOut, ArrayList<Double> amountsIn, ArrayList<Double> amountsOut, ArrayList<Date> dates) {
        this.portfolioName = portfolioName;
        this.defaultCurrency = defaultCurrency;
        this.assetsIn = assetsIn;
        this.assetsOut = assetsOut;
        this.amountsIn = amountsIn;
        this.amountsOut = amountsOut;
        this.dates = dates;
    }

    public String getPortfolioName() {
        return portfolioName;
    }

    public String getDefaultCurrency() {
        return defaultCurrency;
    }

    public ArrayList<String> getAssetsIn() {
        return assetsIn;
    }

    public ArrayList<String> getAssetsOut() {
        return assetsOut;
    }

    public ArrayList<Double> getAmountsIn() {
        return amountsIn;
    }

    public ArrayList<Double> getAmountsOut() {
        return amountsOut;
    }

    public ArrayList<Date> getDates() {
        return dates;
    }
}
