package interface_adapter.holdings;
import java.util.ArrayList;

public class HoldingsState {
    private ArrayList<String> symbols;
    private ArrayList<Double> quotes;
    private ArrayList<Double> shares;
    private ArrayList<Double> values;

    public HoldingsState(HoldingsState copy) {
        this.symbols = copy.symbols;
        this.quotes = copy.quotes;
        this.shares = copy.shares;
        this.values = copy.values;
    }

    public HoldingsState() {
        this.symbols = new ArrayList<String>();
        this.symbols.add("Total");
        this.quotes = new ArrayList<Double>();
        this.shares = new ArrayList<Double>();
        this.values = new ArrayList<Double>();
        this.values.add(0.0);
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

    public void setPrices(ArrayList<Double> quotes) {
        this.quotes = quotes;
    }

    public void setShares(ArrayList<Double> shares) {
        this.shares = shares;
    }

    public void setValues(ArrayList<Double> values) {
        this.values = values;
    }
}
