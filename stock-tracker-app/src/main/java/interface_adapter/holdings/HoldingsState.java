package interface_adapter.holdings;

import java.util.ArrayList;

public class HoldingsState {
    private String portfolioName;
    private String defaultCurrency;
    private ArrayList<String> symbols;
    private ArrayList<Double> quotes;
    private ArrayList<Double> shares;
    private ArrayList<Double> values;
    private ArrayList<Double> changes;
    private ArrayList<Double> changePercents;

    public HoldingsState(HoldingsState copy) {
        this.portfolioName = copy.portfolioName;
        this.defaultCurrency = copy.defaultCurrency;
        this.symbols = copy.symbols;
        this.quotes = copy.quotes;
        this.shares = copy.shares;
        this.values = copy.values;
        this.changes = copy.changes;
        this.changePercents = copy.changePercents;
    }

    public HoldingsState() {
        this.portfolioName = "";
        this.defaultCurrency = "";
        this.symbols = new ArrayList<String>();
        this.quotes = new ArrayList<Double>();
        this.shares = new ArrayList<Double>();
        this.values = new ArrayList<Double>();
        this.changes = new ArrayList<Double>();
        this.changePercents = new ArrayList<Double>();
    }

    public String getPortfolioName() {
        return portfolioName;
    }

    public String getDefaultCurrency() {
        return defaultCurrency;
    }

    public ArrayList<String> getSymbols() {
        return symbols;
    }

    public ArrayList<Double> getQuotes() {
        return quotes;
    }

    public ArrayList<Double> getShares() {
        return shares;
    }

    public ArrayList<Double> getValues() {
        return values;
    }

    public ArrayList<Double> getChanges() {
        return changes;
    }

    public ArrayList<Double> getChangePercents() {
        return changePercents;
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    public void setDefaultCurrency(String defaultCurrency) {
        this.defaultCurrency = defaultCurrency;
    }

    public void setSymbols(ArrayList<String> symbols) {
        this.symbols = symbols;
    }

    public void setPrices(ArrayList<Double> quotes) {
        this.quotes = quotes;
    }

    public void setShares(ArrayList<Double> shares) {
        this.shares = shares;
    }

    public void setValues(ArrayList<Double> values) {
        this.values = values;
    }

    public void setChanges(ArrayList<Double> changes) {
        this.changes = changes;
    }

    public void setChangePercents(ArrayList<Double> changePercents) {
        this.changePercents = changePercents;
    }
}
