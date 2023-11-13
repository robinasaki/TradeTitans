package entity;

import java.util.Date;
import java.util.HashMap;

public class Currency extends Tradeable {

    public Currency(String name, String symbol, HashMap<Date, Double> priceHistory) {
        super(name, symbol);
    }

    public Currency(String name) {
        this(name, null, null);
    }

    public void trade() {
        // TODO: Complete trade method.
    }
}
