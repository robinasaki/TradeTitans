package interface_adapter.view_price_history;

import java.util.Date;
import java.util.TreeMap;

public class PriceHistoryState {
    private String portfolioName;
    private String assetName;
    private String defaultCurrency;
    private TreeMap<Date, Double> priceHistory;

    public PriceHistoryState() {
        this.portfolioName = "";
        this.assetName = "";
        this.defaultCurrency = "";
        this.priceHistory = new TreeMap<Date, Double>();
    }

    public String getPortfolioName() {
        return this.portfolioName;
    }

    public String getAssetName() {
        return this.assetName;
    }

    public String getDefaultCurrency() {
        return this.defaultCurrency;
    }

    public TreeMap<Date, Double> getPriceHistory() {
        return this.priceHistory;
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public void setDefaultCurrency(String defaultCurrency) {
        this.defaultCurrency = defaultCurrency;
    }

    public void setPriceHistory(TreeMap<Date, Double> priceHistory) {
        this.priceHistory = priceHistory;
    }
}
