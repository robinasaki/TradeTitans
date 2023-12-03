package entity;

import java.util.Date;
import java.util.TreeMap;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Tradeable implements Serializable {
    private String name; // e.g. "Apple Inc."
    private String symbol; // e.g. "AAPL"
    private TreeMap<Date, Double> priceHistory; // price history in USD
    private double sharesHeld; // number of shares held in portfolio
//    private static Map<String, Tradeable> tradeables = new HashMap<>();

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
/*
    public static Tradeable getTradeable(String symbol) {
        return tradeables.get(symbol);
    }

    // adds tradeable to map if not already there
    // this should be the only place tradeables are instantiated
    public static void addTradeable(String symbol) {
        if (!tradeables.containsKey(symbol)) {
            Tradeable tradeable = new Tradeable("tradeable_name_todo", symbol);
            tradeables.put(symbol, tradeable);
        }
    }
*/
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
