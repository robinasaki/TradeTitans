package entity;
import Currency;
public class Portfolio {
    private final String name;

    private final Currency currency;

    private final int protfolioId;

    public Portfolio(String name, Currency currency, int protfolioId){
        this.name = name;
        this.currency = currency;
        this.protfolioId = protfolioId;
    }
}
