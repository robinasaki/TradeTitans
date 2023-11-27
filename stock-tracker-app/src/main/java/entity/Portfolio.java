package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.Serializable;
import entity.Transaction;
import entity.TradeTransaction;
import entity.BankingTransaction;
import entity.Tradeable;

public class Portfolio implements Serializable {
    private final String name;

    private Tradeable currency;

    private HashMap<String, Double> holdings;

    private ArrayList<Transaction> transactions;

    private final int portfolioId;

    public Portfolio(String name, Tradeable currency, HashMap<String, Double> holdings, ArrayList<Transaction> transactions, int portfolioId) {
        this.name = name;
        this.currency = currency;
        this.holdings = holdings;
        this.transactions = transactions;
        this.portfolioId = portfolioId;
    }

    public Portfolio(String name, Tradeable currency) {
        this.name = name;
        this.currency = currency;
        this.holdings = new HashMap<>();
        this.transactions = new ArrayList<>();
        this.portfolioId = 0;
    }

    public String getName() {
        return this.name;
    }

    public void setCurrency(Tradeable NewCurrency) {
        this.currency = NewCurrency;
    }

    public Tradeable getCurrency() {
        return this.currency;
    }

    public HashMap<String, Double> getHoldings() {
        return this.holdings;
    }

    public ArrayList<Transaction> getTransactions() {
        return this.transactions;
    }

    // for adding to "watchlist"
    public void addAsset(String asset){
        if (!holdings.containsKey(asset))
            holdings.put(asset, 0.0);
    }

    // users should only be able to do this if they have no holdings of the asset
    public void removeAsset(String asset){
        holdings.remove(asset);
    }

    public ArrayList<String> getWatchlist(){
        return new ArrayList<>(holdings.keySet());
    }


    public void addTrade(TradeTransaction transaction){
        // amounts being traded
        double amountIn = transaction.getAmountIn();
        double amountOut = transaction.getAmountOut();
        
        // if the asset is not in holdings, add it, unless it is "outside portfolio"
        if (holdings.get(transaction.getAssetIn()) == null && !transaction.getAssetIn().isEmpty())
            holdings.put(transaction.getAssetIn(), 0.0);
        if (holdings.get(transaction.getAssetOut()) == null && !transaction.getAssetOut().isEmpty())
            holdings.put(transaction.getAssetOut(), 0.0);

        if (!transaction.getAssetIn().isEmpty()){
            // as long a it's not a withdraw, we calculate amount in holdings after trade, then update holdings
            double assetInAmount = holdings.get(transaction.getAssetIn()) + amountIn;
            holdings.put(transaction.getAssetIn(), assetInAmount);
        }

        if (!transaction.getAssetOut().isEmpty()){
            // as long a it's not a deposit, we calculate amount in holdings after trade, then update holdings
            double assetOutAmount = holdings.get(transaction.getAssetOut()) - amountOut;
            holdings.put(transaction.getAssetOut(), assetOutAmount);
        }

        // record transaction
        transactions.add(transaction);
    }

    public void deposit(BankingTransaction transaction){
        this.transactions.add(transaction);
        this.holdings.put(String.valueOf(transaction.getAsset()), this.holdings.get(String.valueOf(transaction.getAsset())) + transaction.getAmount());
    }

    public void withdraw(BankingTransaction transaction){
        this.transactions.add(transaction);
        this.holdings.put(String.valueOf(transaction.getAsset()), this.holdings.get(String.valueOf(transaction.getAsset())) - transaction.getAmount());
    }

    public double getPortfolioValue(){
        double value = 0;
        for (String asset : this.holdings.keySet()){
            // TODO: fix the below
            value += asset.getCurrentPrice() * this.holdings.get(asset);
        }
        return value;
    }
}
