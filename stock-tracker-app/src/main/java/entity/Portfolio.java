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

    private final int portfolioId;

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

    // don't see how this is strictly necessary
    public void addStock(Tradeable stock, Double amount){
        this.holdings.put(stock, amount);
    }

    public void addTrade(TradeTransaction transaction){
        this.transactions.add(transaction);
        this.holdings[transaction.getAssetIn()] += transaction.getAmountIn();
        this.holdings[transaction.getAssetOut()] += transaction.getAmountOut();
    }

    public void deposit(BankingTransaction transaction){
        this.transactions.add(transaction);
        this.holdings[transaction.getCurrency()] += transaction.getAmount();
    }

    public void withdraw(BankingTransaction transaction){
        this.transactions.add(transaction);
        this.holdings[transaction.getCurrency()] -= transaction.getAmount();
    }

    public double getPortfolioValue(){
        double value = 0;
        for (Tradeable asset : this.holdings.keySet()){
            value += asset.getCurrentPrice() * this.holdings.get(asset);
        }
        return value;
    }
}
