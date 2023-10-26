package entity;

import java.util.Date;
import java.util.HashMap;

public class Stock extends Tradeable {

    public Stock(String name, String symbol, HashMap<Date, Double> priceHistory) {
        super(name, symbol, priceHistory);
    }
}
