package entity;

import java.util.Date;
import java.util.HashMap;
import java.time.LocalDate;

public abstract class Tradeable {
    private String name; // e.g. "Apple Inc."
    private String symbol; // e.g. "AAPL"
    private HashMap<Date, Double> priceHistory; // price history in USD

    public Tradeable(String name, String symbol, HashMap<Date, Double> priceHistory) {
        this.name = name;
        this.symbol = symbol;
        this.priceHistory = priceHistory;
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

    public double getCurrentPrice() {
        return this.priceHistory.get(LocalDate.now());
    }
}
