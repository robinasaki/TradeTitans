package use_case.trade;

import entity.TradeTransaction;
import entity.Portfolio;
import entity.Tradeable;
import use_case.FileDataAccessInterface;
import use_case.APIDataAccessInterface;

import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;

public class TradeInteractor implements TradeInputBoundary {
    private final APIDataAccessInterface apiDataAccessObject;
    private final FileDataAccessInterface fileDataAccessObject;
    private final TradeOutputBoundary presenter;

    public TradeInteractor(APIDataAccessInterface apiDataAccessObject, FileDataAccessInterface fileDataAccessObject, TradeOutputBoundary presenter) {
        this.apiDataAccessObject = apiDataAccessObject;
        this.fileDataAccessObject = fileDataAccessObject;
        this.presenter = presenter;
    }

    @Override
    public void execute(TradeInputData tradeInputData) {
        Portfolio portfolio = fileDataAccessObject.getPortfolio(tradeInputData.getPortfolioName());
        TradeTransaction trade = new TradeTransaction(
                tradeInputData.getAssetIn(),
                tradeInputData.getAssetOut(),
                tradeInputData.getAmountIn(),
                tradeInputData.getAmountOut(),
                tradeInputData.getTradingFee(),
                tradeInputData.getDate());

        // deposit filtering
        if (!trade.getAssetIn().isEmpty() && trade.getAssetOut().isEmpty()) {
            // zero deposit prevention
            if (trade.getAmountIn() == 0) {
                throw new RuntimeException("Zero deposit not allowed.");
            }
            // negative deposit prevention
            if (trade.getAmountIn() < 0) {
                throw new RuntimeException("<html> Negative deposit not allowed. <br/> Please use the \"withdraw\" option. <html/>");
            }
        }

        // withdraw filtering
        if (!trade.getAssetOut().isEmpty() && trade.getAssetIn().isEmpty()) {
            // zero withdraw prevention
            if (trade.getAmountOut() == 0) {
                throw new RuntimeException("Zero withdraw not allowed.");
            }
            // negative withdraw prevention
            if (trade.getAmountOut() < 0) {
                throw new RuntimeException("<html> Negative withdrawal not allowed. <br/> Please use the \"Deposit\" option. <html/>");
            }
        }

        boolean newAssetIn = !(tradeInputData.getAssetIn().isEmpty() && !portfolio.getHoldings().containsKey(tradeInputData.getAssetIn()));
        boolean newAssetOut = !tradeInputData.getAssetOut().isEmpty() && !portfolio.getHoldings().containsKey(tradeInputData.getAssetOut());
        portfolio.addTrade(trade);


        // if the asset in isn't in the portfolio, give it a price history from API
        if (newAssetIn) {
            TreeMap<Date, Double> priceHistory = apiDataAccessObject.getHistoricalQuotes(tradeInputData.getAssetIn(), portfolio.getCurrency().getSymbol());
            portfolio.getHoldings().get(tradeInputData.getAssetIn()).setPriceHistory(priceHistory);
        }

        // if the asset out isn't in the portfolio, give it a price history from API
        // might not be necessary if we don't allow trading out of assets that aren't in the portfolio
        if (newAssetOut) {
            TreeMap<Date, Double> priceHistory = apiDataAccessObject.getHistoricalQuotes(tradeInputData.getAssetOut(), portfolio.getCurrency().getSymbol());
            portfolio.getHoldings().get(tradeInputData.getAssetOut()).setPriceHistory(priceHistory);
        }

        ArrayList<String> symbols = new ArrayList<>();
        ArrayList<Double> prices = new ArrayList<>();
        ArrayList<Double> shares = new ArrayList<>();
        ArrayList<Double> values = new ArrayList<>();
        ArrayList<Double> changes = new ArrayList<>();
        ArrayList<Double> changePercents = new ArrayList<>();

        // establishing what will be shown in the holdings
        for (String symbol : portfolio.getHoldings().keySet()) {
            Tradeable asset = portfolio.getHoldings().get(symbol);
            if (asset.getSharesHeld() == 0 && !asset.getSymbol().equals(portfolio.getCurrency().getSymbol())) {
                portfolio.removeAsset(asset.getSymbol());
            } else {
                symbols.add(asset.getSymbol());
                prices.add(asset.getCurrentPrice());
                shares.add(asset.getSharesHeld());
                values.add(asset.getCurrentValue());
                changes.add(asset.getCurrentPrice() - asset.getPreviousPrice());
                changePercents.add((asset.getCurrentPrice() - asset.getPreviousPrice()) / asset.getPreviousPrice() * 100);
            }
        }
        fileDataAccessObject.removePortfolio(portfolio.getName());
        fileDataAccessObject.savePortfolio(portfolio);

        symbols.add("Total");
        // TODO: should be something meaningful like N/A for total price and shares
        prices.add(0.0);
        shares.add(0.0);
        values.add(portfolio.getPortfolioValue());

        double totalChange = 0;
        for (int i = 0; i < changes.size(); i++) {
            totalChange = totalChange + changes.get(i) * shares.get(i);
        }
        changes.add(totalChange);

        if (portfolio.getPortfolioValue() == 0) {
            changePercents.add(0.0);
        } else {
            changePercents.add(totalChange / (portfolio.getPortfolioValue() - totalChange) * 100);
        }

        TradeOutputData tradeOutputData = new TradeOutputData(symbols, prices, shares, values, changes, changePercents);
        presenter.present(tradeOutputData);


    }
}
