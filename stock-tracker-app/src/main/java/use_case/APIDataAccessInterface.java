package use_case;
import java.util.Date;
import java.util.TreeMap;

public interface APIDataAccessInterface {
    public TreeMap<Date, Double> getHistoricalQuotes(String symbol, String targetCurrency);
}
