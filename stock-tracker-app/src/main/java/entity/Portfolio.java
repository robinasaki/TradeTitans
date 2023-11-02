package entity;
import Currency;
public class Portfolio {
    private final String name;

    private final Currency currency;

    private HashMap<Tradeable, Double> holdings;

    private ArrayList<Transaction> transactions;

    private final Integer portfolioId;

    public Portfolio(String name, Currency currency, HashMap<Tradeable, Double> holdings, ArrayList<Transaction> transactions, Integer portfolioId) {
        this.name = name;
        this.currency = currency;
        this.holdings = holdings;
        this.transactions = transactions;
        this.portfolioId = portfolioId;
    }

    public String getName() {
        return this.name;
    }

    public Currency getCurrency() {
        return this.currency;
    }

    public HashMap<Tradeable, Double> getHoldings() {
        return this.holdings;
    }

    public ArrayList<Transaction> getTransactions() {
        return this.transactions;
    }

    public void addStock(Tradeable stock, Double amount){
        this.holdings.put(stock, amount);
    }
}
