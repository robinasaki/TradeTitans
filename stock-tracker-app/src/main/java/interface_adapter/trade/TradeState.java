package interface_adapter.trade;

//TODO: May have too many methods here. Need to double check
public class TradeState {
    private double tradeValue;
    private double tradeValueError;
    private String portfolioName;
    private String portfolioNameError;
    private String assetInSymbol;
    private String assetInSymbolError;
    private String assetOutSymbol;
    private String assetOutSymbolError;
    private double amountIn;
    private double amountInError;
    private double amountOut;
    private double amountOutError;
    private String notTradeableError = null;
    private String message = null;

    public TradeState(TradeState copy) {
        portfolioName = copy.portfolioName;
        portfolioNameError = copy.portfolioNameError;
        assetInSymbol = copy.assetInSymbol;
        assetInSymbolError = copy.assetInSymbolError;
        assetOutSymbol = copy.assetOutSymbol;
        assetOutSymbolError = copy.assetOutSymbolError;
        amountIn = copy.amountIn;
        amountInError = copy.amountInError;
        amountOut = copy.amountOut;
        amountOutError = copy.amountOutError;
        tradeValue = copy.tradeValue;
        tradeValueError = copy.tradeValueError;
        notTradeableError = copy.notTradeableError;
        message = copy.message;
    }

    //TODO: Have not implemented all of the setters yet.
    public String getPortfolioName() {return portfolioName;}

    public String getPortfolioNameError() {return portfolioNameError;}

    public String getAssetInSymbol() {return assetInSymbol;}

    public String getAssetInSymbolError() {return assetInSymbolError;}

    public String getAssetOutSymbol() {return assetOutSymbol;}

    public String getAssetOutSymbolError() {return assetOutSymbolError;}

    public double getAmountIn() {return amountIn;}

    public double getAmountInError() {return amountInError;}

    public double getAmountOut() {return amountOut;}

    public double getAmountOutError() {return amountOutError;}

    public TradeState() {}

    public double getTradeValue() {
        return this.tradeValue;
    }

    public  String getNotTradeableError() {
        return this.notTradeableError;
    }

    public String getClearMessage() {return message;}

    public void setTradeValue(double tradeValue) {this.tradeValue = tradeValue;}
}
