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
        // TODO: implement portfolio id as the following:
        // portfolioId = currentPortfolioCount + 1
        // with the initial portfolio id = 1
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
            if (assetOutAmount < 0) {
                throw new RuntimeException("<html> No enough asset. <html/>");
            }
            holdings.get(assetOut).setSharesHeld(assetOutAmount);
        }

        // record transaction
        transactions.add(transaction);
    }
/*
    public void deposit(BankingTransaction transaction) {
        if (transaction.getIfDeposit()) {
            if (this.holdings.containsKey(transaction.getAsset().getSymbol())) {
                // if the user already has the inputted asset
                this.holdings.put(transaction.getAsset().getSymbol(), this.holdings.get(transaction.getAsset().getSymbol()) + transaction.getAmount());
                // TODO: how to add BankingTransaction to transactions, and do we need to?
                //this.transactions.add(transaction);
            } else {
                // no existing asset
                this.holdings.put(transaction.getAsset().getSymbol(), transaction.getAmount());
                // TODO: how to add BankingTransaction to transactions, and do we need to?
                //this.transactions.add(transaction);
            }
        } else {
            throw new RuntimeException("This transaction is not a deposit transaction");
        }
    }

    public void withdraw(BankingTransaction transaction) {
        if (!transaction.getIfDeposit()) {
            if (this.holdings.containsKey(transaction.getAsset().getSymbol())) {
                if (this.holdings.get(transaction.getAsset().getSymbol()) - transaction.getAmount() >= 0) {
                    this.holdings.put(transaction.getAsset().getSymbol(), this.holdings.get(transaction.getAsset().getSymbol()) - transaction.getAmount());
                    // TODO: how to add BankingTransaction to transactions, and do we need to?
                    // this.transactions.add(transaction);
                } else {
                    throw new RuntimeException("You do not have enough assets for this withdraw.");
                }
            } else {
                throw new RuntimeException("You do not have the corresponding asset in your holding.");
            }
        } else {
            throw new RuntimeException("This transaction is not a withdraw transaction");
        }

        // if the portfolio have an empty asset, remove it
        for (String s: holdings.keySet()) {
            if (holdings.get(s) == 0) {
                holdings.remove(s);
            }
        }
    }
*/
    public double getPortfolioValue() {
        double value = 0;
        for (String asset : this.holdings.keySet()) {
            value += holdings.get(asset).getSharesHeld() * holdings.get(asset).getCurrentPrice();
        }
        return value;
    }

    public int getPortfolioId() {
        return this.portfolioId;
    }
}
