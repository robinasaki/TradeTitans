package interface_adapter.trade;

public class TradeState {
    private String portfolioName;
    private String defaultCurrency;

    public String getPortfolioName() {
        return portfolioName;
    }

    public String getDefaultCurrency() {
        return defaultCurrency;
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    public void setDefaultCurrency(String defaultCurrency) {
        this.defaultCurrency = defaultCurrency;
    }

}
