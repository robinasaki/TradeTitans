package use_case.trade;

import java.util.ArrayList;

public class TradeOutputData {
    private ArrayList<String> symbols;
    private ArrayList<Double> prices;
    private ArrayList<Double> shares;
    private ArrayList<Double> values;
    private ArrayList<Double> changes;
    private ArrayList<Double> changePercents;

    public TradeOutputData(ArrayList<String> symbols, ArrayList<Double> prices, ArrayList<Double> shares, ArrayList<Double> values, ArrayList<Double> changes, ArrayList<Double> changePercents) {
        this.symbols = symbols;
        this.prices = prices;
        this.shares = shares;
        this.values = values;
        this.changes = changes;
        this.changePercents = changePercents;
    }

    public ArrayList<String> getSymbols() {
        return symbols;
    }

    public ArrayList<Double> getPrices() {
        return prices;
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

    public void setSymbols(ArrayList<String> symbols) {
        this.symbols = symbols;
    }

    public void setPrices(ArrayList<Double> prices) {
        this.prices = prices;
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
