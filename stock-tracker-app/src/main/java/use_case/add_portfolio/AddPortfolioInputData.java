package use_case.add_portfolio;

public class AddPortfolioInputData {
    private final String portfolioName;
    private final String defaultCurrency;

    public AddPortfolioInputData(String portfolioName, String defaultCurrency) {
        this.portfolioName = portfolioName;
        this.defaultCurrency = defaultCurrency;
    }

    public String getPortfolioName() {
        return portfolioName;
    }

    public String getDefaultCurrency() {
        return defaultCurrency;
    }
}
