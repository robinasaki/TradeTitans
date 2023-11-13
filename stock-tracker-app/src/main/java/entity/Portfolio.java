package entity;

import java.util.HashMap;
import java.util.ArrayList;
import java.io.Serializable;
import entity.Currency;
import entity.Transaction;
import entity.TradeTransaction;
import entity.BankingTransaction;
import entity.Tradeable;

public class Portfolio implements Serializable {
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

    public Portfolio(String name) {
        this.name = name;
        this.currency = new Currency("USD");
        this.holdings = new HashMap<>();
        this.transactions = new ArrayList<>();
        this.portfolioId = 0;
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

    // for adding to "watchlist"
    public void addAsset(Tradeable asset){
        if (!holdings.containsKey(asset))
            holdings.put(asset, 0.0);
    }

    // users should only be able to do this if they have no holdings of the asset
    public void removeAsset(Tradeable asset){
        holdings.remove(asset);
    }

    public ArrayList<Tradeable> getWatchlist(){
        return new ArrayList<>(holdings.keySet());
    }


    public void addTrade(TradeTransaction transaction){
        // amounts being traded
        double amountIn = transaction.getAmountIn();
        double amountOut = transaction.getAmountOut();
        
        // amount there is in holdings after the trade
        double assetInAmount = holdings.get(transaction.getAssetIn()) + amountIn;
        double assetOutAmount = holdings.get(transaction.getAssetOut()) - amountOut;

        // update holdings
        holdings.put(transaction.getAssetIn(), assetInAmount);
        holdings.put(transaction.getAssetOut(), assetOutAmount);

        // record transaction
        transactions.add(transaction);
    }

    public void deposit(BankingTransaction transaction){
        this.transactions.add(transaction);
        this.holdings.put(transaction.getAsset(), this.holdings.get(transaction.getAsset()) + transaction.getAmount());
    }

    public void withdraw(BankingTransaction transaction){
        this.transactions.add(transaction);
        this.holdings.put(transaction.getAsset(), this.holdings.get(transaction.getAsset()) - transaction.getAmount());
    }

    public double getPortfolioValue(){
        double value = 0;
        for (Tradeable asset : this.holdings.keySet()){
            value += asset.getCurrentPrice() * this.holdings.get(asset);
        }
        return value;
    }
}
