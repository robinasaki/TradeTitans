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

    private HashMap<String, Tradeable> holdings;

    private ArrayList<TradeTransaction> transactions;

    private final int portfolioId;

    public Portfolio(String name, Tradeable currency, HashMap<String, Tradeable> holdings, ArrayList<TradeTransaction> transactions) {
        this.name = name;
        this.currency = currency;
        this.holdings = holdings;
        this.transactions = transactions;
        this.portfolioId = 1;
    }

    public Portfolio(String name, Tradeable currency) {
        this.name = name;
        this.currency = currency;
        this.holdings = new HashMap<>();
        this.transactions = new ArrayList<>();
        this.portfolioId = 1;
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

    public HashMap<String, Tradeable> getHoldings() {
        return this.holdings;
    }

    public ArrayList<TradeTransaction> getTransactions() {
        return this.transactions;
    }

    // for adding to "watchlist"
    public void addAsset(String asset) {
        if (!holdings.containsKey(asset)) {
            Tradeable assetTradeable = new Tradeable("name", asset);
            holdings.put(asset, assetTradeable);
        }
    }

    // users should only be able to do this if they have no holdings of the asset
    public void removeAsset(String asset) {
        holdings.remove(asset);
    }

    public ArrayList<String> getWatchlist() {
        return new ArrayList<>(holdings.keySet());
    }


    public void addTrade(TradeTransaction transaction) {
        // amounts being traded
        String assetIn = transaction.getAssetIn();
        String assetOut = transaction.getAssetOut();
        double amountIn = transaction.getAmountIn();
        double amountOut = transaction.getAmountOut();

        // if the asset is not in holdings, add it, unless it is "outside portfolio"
        if (holdings.get(assetIn) == null && !assetIn.isEmpty())
            addAsset(assetIn);
        if (holdings.get(assetOut) == null && !assetOut.isEmpty())
            addAsset(assetOut);

        if (!transaction.getAssetIn().isEmpty()) {
            // as long as it's not a withdrawal, we calculate amount in holdings after trade, then update holdings
            double assetInAmount = holdings.get(assetIn).getSharesHeld() + amountIn;
            holdings.get(assetIn).setSharesHeld(assetInAmount);
        }

        if (!transaction.getAssetOut().isEmpty()) {
            // as long as it's not a deposit, we calculate amount in holdings after trade, then update holdings
            double assetOutAmount = holdings.get(assetOut).getSharesHeld() - amountOut;
            if (assetOutAmount < 0 && !assetIn.isEmpty()) {
                // case: buy without enough default currency || selling without enough asset
                // this Exception should be caught and filtered in TradeView
                throw new RuntimeException("Code100: over-withdrawal or buying without enough asset");
            } else if (assetOutAmount < 0) {
                // case: withdraw currency
                throw new RuntimeException("You don't have enough asset.");
            }
            holdings.get(assetOut).setSharesHeld(assetOutAmount);
        }

        // record transaction
        transactions.add(transaction);
    }

    public double getPortfolioValue() {
        double value = 0;
        for (String asset : this.holdings.keySet()) {
            value += holdings.get(asset).getSharesHeld() * holdings.get(asset).getCurrentPrice();
        }
        return value;
    }
}
