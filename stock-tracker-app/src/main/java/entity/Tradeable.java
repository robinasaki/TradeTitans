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
        // checks the last 365 days for a price
        for (int i = 0; i < 365; i++) {
            if (priceHistory.get(LocalDate.now().minusDays(i)) != null) {
                return priceHistory.get(LocalDate.now().minusDays(i));
            }
        }
        throw new RuntimeException("No price history found in last year for " + this.symbol);
    }
}
