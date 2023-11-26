package interface_adapter.holdings;
import java.util.ArrayList;

public class HoldingsState {
    private ArrayList<String> symbols = new ArrayList<String>();
    private ArrayList<Double> quotes = new ArrayList<Double>();
    private ArrayList<Double> shares = new ArrayList<Double>();
    private ArrayList<Double> values = new ArrayList<Double>();

    public HoldingsState(HoldingsState copy) {
        this.symbols = copy.symbols;
        this.quotes = copy.quotes;
        this.shares = copy.shares;
        this.values = copy.values;
    }

    public HoldingsState() {
    }

    public ArrayList<String> getSymbols() {
        return symbols;
    }

    public ArrayList<Double> getQuotes() {
        return quotes;
    }

    public ArrayList<Double> getShares() {
        return shares;
    }

    public ArrayList<Double> getValues() {
        return values;
    }

    public void setSymbols(ArrayList<String> symbols) {
        this.symbols = symbols;
    }

    public void setQuotes(ArrayList<Double> quotes) {
        this.quotes = quotes;
    }

    public void setShares(ArrayList<Double> shares) {
        this.shares = shares;
    }

    public void setValues(ArrayList<Double> values) {
        this.values = values;
    }
}
