package entity;

import java.util.HashMap;
import java.util.ArrayList;

import entity.Currency;
import entity.Tradeable;
import entity.Transaction;
public class Portfolio {
    private final String name;

    private Currency currency;

    private HashMap<Tradeable, Double> holdings;

    private ArrayList<Transaction> transactions;

    private int portfolioId;

    public Portfolio(String name, Currency currency, HashMap<Tradeable, Double> holdings, ArrayList<Transaction> transactions, int portfolioId) {
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

    public void addTransaction(Transaction transaction){
        this.transactions.add(transaction);
        this.holdings[transaction.getAssetIn()] += transaction.getAmountIn();
        this.holdings[transaction.getAssetOut()] += transaction.getAmountOut();
    }
}
