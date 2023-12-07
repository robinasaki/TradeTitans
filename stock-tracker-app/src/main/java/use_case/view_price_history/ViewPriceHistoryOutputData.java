package use_case.view_price_history;

import java.util.TreeMap;
import java.util.Date;

public class ViewPriceHistoryOutputData {
    private String portfolioName;
    private String assetName;
    private String defaultCurrency;
    private TreeMap<Date, Double> priceHistory;

    public ViewPriceHistoryOutputData(String portfolioName, String assetName, String defaultCurrency, TreeMap<Date, Double> priceHistory) {
        this.portfolioName = portfolioName;
        this.assetName = assetName;
        this.defaultCurrency = defaultCurrency;
        this.priceHistory = priceHistory;
    }

    public String getPortfolioName() {
        return portfolioName;
    }

    public String getAssetName() {
        return assetName;
    }

    public String getDefaultCurrency() {
        return defaultCurrency;
    }

    public TreeMap<Date, Double> getPriceHistory() {
        return priceHistory;
    }
}
