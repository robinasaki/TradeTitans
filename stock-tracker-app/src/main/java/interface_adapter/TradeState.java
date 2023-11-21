package interface_adapter;

//TODO: This class may not fit the 'tradable' schema. Need tests to make sure this is correct.
public class TradeState {
    private double tradeValue;
    private String notTradeableError = null;

    public TradeState(TradeState copy) {
        this.tradeValue = copy.tradeValue;
        this.notTradeableError = copy.notTradeableError;
    }

    public TradeState() {}

    public void setTradeValue(double tradeValue) {
        this.tradeValue = tradeValue;
    }

    public double getTradeValue() {
        return this.tradeValue;
    }

    public  String getNotTradeableError() {
        return this.notTradeableError;
    }
}
