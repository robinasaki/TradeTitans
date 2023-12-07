package entity;

import java.util.Date;
import java.util.TreeMap;
import java.io.Serializable;

public class Tradeable implements Serializable {
    private String name; // e.g. "Apple Inc."
    private String symbol; // e.g. "AAPL"
    private TreeMap<Date, Double> priceHistory; // price history in USD
    private double sharesHeld; // number of shares held in portfolio

    public Tradeable(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
        this.sharesHeld = 0;
        this.priceHistory = new TreeMap<>();
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getSharesHeld() {
        return sharesHeld;
    }

    public TreeMap<Date, Double> getPriceHistory() {
        return priceHistory;
    }

    public void setPriceHistory(TreeMap<Date, Double> priceHistory) {
        this.priceHistory = priceHistory;
    }

    public void setSharesHeld(double sharesHeld) {
        this.sharesHeld = sharesHeld;
    }

    public double getCurrentPrice() {
        if (priceHistory.isEmpty()) {
            throw new RuntimeException("No price history found for " + this.symbol);
        }
        return priceHistory.lastEntry().getValue();
    }

    public double getPreviousPrice() {
        if (priceHistory.isEmpty()) {
            throw new RuntimeException("No price history found for " + this.symbol);
        }
        return priceHistory.lowerEntry(priceHistory.lastKey()).getValue();
    }

    public boolean equals(Tradeable compare) {
        return (this.symbol.equals(compare.symbol));
    }

    public double getCurrentValue() {
        return getCurrentPrice() * sharesHeld;
    }
}
