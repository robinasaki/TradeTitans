package java.entity;

import entity.Stock;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;

public class StockTest {

    @Test
    public void init() {
        HashMap<Date, Double> hypoHistory = new HashMap<>();
        Stock test = new Stock("International Bussiness Machine", "IBM", hypoHistory);
    }

}
