package entity;
import entity.Currency;
public class Portfolio {
    private final String name;

    private final Currency currency;

    private final int portfolioId;

    public Portfolio(String name, Currency currency, int portfolioId){
        this.name = name;
        this.currency = currency;
        this.portfolioId = portfolioId;
    }
}
