package use_case.update_prices;

import java.util.ArrayList;

public class UpdatePricesOutputData {
    private String portfolioName;
    private ArrayList<String> symbols;
    private ArrayList<Double> prices;
    private ArrayList<Double> shares;
    private ArrayList<Double> values;
    
    public UpdatePricesOutputData(String portfolioName, ArrayList<String> symbols, ArrayList<Double> prices, ArrayList<Double> shares, ArrayList<Double> values) {
        this.portfolioName = portfolioName;
        this.symbols = symbols;
        this.prices = prices;
        this.shares = shares;
        this.values = values;
    }

    public String getPortfolioName() {
        return portfolioName;
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

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
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
}
