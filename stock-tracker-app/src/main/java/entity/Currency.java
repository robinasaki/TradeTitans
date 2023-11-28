package entity;

import java.util.Date;
import java.util.HashMap;
import java.util.TreeMap;

public class Currency extends Tradeable {

    public Currency(String name, String symbol, TreeMap<Date, Double> priceHistory) {
        super(name, symbol);
    }

    public Currency(String name) {
        this(name, null, null);
    }

    // TODO: test Trade()
}
