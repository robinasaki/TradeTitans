package interface_adapter.trade;

//TODO: This class may not fit the 'tradeable' schema. Need tests to make sure this is correct.
public class TradeState {
    private double tradeValue;
    private String notTradeableError = null;
    private String message = null;

    public TradeState(TradeState copy) {
        this.tradeValue = copy.tradeValue;
        this.notTradeableError = copy.notTradeableError;
        this.message = copy.message;
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

    public String getClearMessage() {return message;}
}
