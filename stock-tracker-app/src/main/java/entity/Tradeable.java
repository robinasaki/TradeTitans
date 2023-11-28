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
    private static Map<String, Tradeable> tradeables = new HashMap<>();

    public Tradeable(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
        this.priceHistory = new TreeMap<>();
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public TreeMap<Date, Double> getPriceHistory() {
        return priceHistory;
    }

    public void setPriceHistory(TreeMap<Date, Double> priceHistory) {
        this.priceHistory = priceHistory;
    }

    public static Tradeable getTradeable(String symbol) {
        return tradeables.get(symbol);
    }

    public static void addTradeable(Tradeable tradeable) {
        tradeables.put(tradeable.getSymbol(), tradeable);
    }

    public double getCurrentPrice() {
        if (priceHistory.isEmpty()) {
            throw new RuntimeException("No price history found for " + this.symbol);
        }
        return priceHistory.lastEntry().getValue();
    }

    public boolean equals(Tradeable compare) {
        return (this.symbol.equals(compare.symbol));
    }
}
