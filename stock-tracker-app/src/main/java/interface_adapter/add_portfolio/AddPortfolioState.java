package interface_adapter.add_portfolio;

public class AddPortfolioState {
    private String portfolioName;
    private String portfolioNameError;
    private String defaultCurrency;
    private String defaultCurrencyError;
    private String portfolioCreationError = null;
    private String message = null;

    public AddPortfolioState(AddPortfolioState copy) {
        portfolioName = copy.portfolioName;
        portfolioNameError = copy.portfolioNameError;;
        defaultCurrency = copy.defaultCurrency;
        defaultCurrencyError = copy.defaultCurrencyError;
        message = copy.message;
    }

    public String getPortfolioName() {return portfolioName;}

    public String getPortfolioNameError() {return portfolioNameError;}

    public String getDefaultCurrency() {return defaultCurrency;}

    public String getDefaultCurrencyError() {return defaultCurrencyError;}

    public String getPortfolioCreationError() {
        return this.portfolioCreationError;
    }

    public String getClearMessage() {return message;}

    public AddPortfolioState() {}

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }
}
