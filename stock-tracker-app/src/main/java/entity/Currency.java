package entity;

import java.util.Date;
import java.util.HashMap;

public class Currency extends Tradeable {
    private final String name;

    public Currency(String name, String symbol, HashMap<Date, Double> priceHistory, String currencyName) {
        super(name, symbol, priceHistory);
        this.name = currencyName;
    }

    public void trade() {
        // TODO: Complete trade method.
    }

    public String getCurrencyName() {
        return name;
    }
}
