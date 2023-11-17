package entity;

import java.util.Date;
import java.util.HashMap;
import java.time.LocalDate;

public class Tradeable {
    private String name; // e.g. "Apple Inc."
    private String symbol; // e.g. "AAPL"
    private HashMap<Date, Double> priceHistory; // price history in USD

    public Tradeable(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
        this.priceHistory = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public HashMap<Date, Double> getPriceHistory() {
        return priceHistory;
    }

    public void setPriceHistory(HashMap<Date, Double> priceHistory) {
        this.priceHistory = priceHistory;
    }

    public double getCurrentPrice() {
        return this.priceHistory.get(LocalDate.now());
    }
}
