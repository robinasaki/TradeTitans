package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import java.util.TreeMap;

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

    public TreeMap<Date, Double> getPriceHistory() {
        TreeMap<Date, Double> combinedPriceHistory = new TreeMap<>();
        for (Tradeable asset : holdings.values()) {
            TreeMap<Date, Double> historicalPrices = asset.getPriceHistory();
            TreeMap<Date, Double> historicalShares = calculateHistoricalShares(asset);
            for (Date date : historicalShares.keySet()) {
                double price = historicalPrices.get(date);
                double shares = historicalShares.get(date);
                double value = price * shares;
                if (combinedPriceHistory.containsKey(date)) {
                    combinedPriceHistory.put(date, combinedPriceHistory.get(date) + value);
                } else {
                    combinedPriceHistory.put(date, value);
                }
            }
        }
        return combinedPriceHistory;
    }

    private TreeMap<Date, Double> calculateHistoricalShares(Tradeable asset) {
        TreeMap<Date, Double> historicalShares = new TreeMap<>();
        for (TradeTransaction transaction : transactions) {
            if (transaction.getAssetIn().equals(asset.getName())) {
                // if asset is being bought, add amount to historicalShares
                historicalShares.put(transaction.getDate(), historicalShares.getOrDefault(transaction.getDate(), 0.0) + transaction.getAmountIn());
            }
            if (transaction.getAssetOut().equals(asset.getName())) {
                // if asset is being sold, subtract amount from historicalShares
                historicalShares.put(transaction.getDate(), historicalShares.getOrDefault(transaction.getDate(), 0.0) - transaction.getAmountOut());
            }
        }
        return historicalShares;
    }

    public double getPortfolioValue() {
        double value = 0;
        for (String asset : this.holdings.keySet()) {
            value += holdings.get(asset).getSharesHeld() * holdings.get(asset).getCurrentPrice();
        }
        return value;
    }
}
