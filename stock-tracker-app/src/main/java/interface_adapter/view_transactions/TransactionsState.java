package interface_adapter.view_transactions;

import java.util.ArrayList;
import java.util.Date;

public class TransactionsState {
    private String portfolioName;
    private String defaultCurrency;
    private ArrayList<String> tradeTypes;
    private ArrayList<String> symbols;
    private ArrayList<String> amounts;
    private ArrayList<Date> dates;

    public TransactionsState() {
        this.portfolioName = "";
        this.defaultCurrency = "";
        this.tradeTypes = new ArrayList<String>();
        this.symbols = new ArrayList<String>();
        this.amounts = new ArrayList<String>();
        this.dates = new ArrayList<Date>();
    }

    public String getPortfolioName() {
        return this.portfolioName;
    }

    public String getDefaultCurrency() {
        return this.defaultCurrency;
    }

    public ArrayList<String> getTradeTypes() {
        return this.tradeTypes;
    }

    public ArrayList<String> getSymbols() {
        return this.symbols;
    }

    public ArrayList<String> getAmounts() {
        return this.amounts;
    }

    public ArrayList<Date> getDates() {
        return this.dates;
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    public void setDefaultCurrency(String defaultCurrency) {
        this.defaultCurrency = defaultCurrency;
    }

    public void setTradeTypes(ArrayList<String> tradeTypes) {
        this.tradeTypes = tradeTypes;
    }

    public void setSymbols(ArrayList<String> symbols) {
        this.symbols = symbols;
    }

    public void setAmounts(ArrayList<String> amounts) {
        this.amounts = amounts;
    }

    public void setDates(ArrayList<Date> dates) {
        this.dates = dates;
    }
}
