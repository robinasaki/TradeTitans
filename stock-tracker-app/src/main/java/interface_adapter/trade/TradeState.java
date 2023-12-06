package interface_adapter.trade;

public class TradeState {
    private String portfolioName;
    private String defaultCurrency;

    private double rate;

    public String getPortfolioName() {
        return portfolioName;
    }
    public void setRate(double rate){
        this.rate = rate;
    }

    public double getRate(){
        return rate;
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
