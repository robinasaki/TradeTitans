package use_case.trade;

import data_access.FileDataAccessObject;
import data_access.APIDataAccessObject;
import entity.TradeTransaction;
import entity.Portfolio;
import entity.Tradeable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;

public class TradeInteractor implements TradeInputBoundary {
    private final FileDataAccessObject fileDataAccessObject;
    private final TradeOutputBoundary presenter;

    public TradeInteractor(FileDataAccessObject fileDataAccessObject, TradeOutputBoundary presenter) {
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
                tradeInputData.getTradingFee());

        // deposit filtering
        if (!trade.getAssetIn().isEmpty()){
            // zero deposit prevention
            if (trade.getAmountIn() == 0) {
                throw new RuntimeException("<html> Zero deposit now allowed. <html/>");
            }
            // negative deposit prevention
            if (trade.getAmountIn() < 0) {
                throw new RuntimeException("<html> Negative deposit now allowed. <br/> Please use the withdraw option. <html/>");
            }
        }

        // withdraw filtering
        if (!trade.getAssetOut().isEmpty()){
            // zero withdraw prevention
            if (trade.getAmountOut() == 0) {
                throw new RuntimeException("<html> Zero deposit now allowed. <html/>");
            }
            // negative withdraw prevention
            if (trade.getAmountOut() < 0) {
                throw new RuntimeException("<html> Negative withdrawal not allowed. <br/> Please use the \"Deposit\" option. <html/>");
            }
        }

        // filtering shares input when buying
        if (!trade.getSharesHeld().isEmpty()) {
            // zero stocks prevention
            if (trade.getAmountOut() == 0) {
                throw new RuntimeException("<html> Not allowed to buy 0 stocks <html/>");
            }
            // negative buy prevention
            if (trade.getAmountOut() <= 0) {
                throw new RuntimeException("<html> Not allowed to but negative stocks. <br/> Please use the \"Sell\" option. <html/>");
            }
        }

        // filtering shares input when selling
        if (!trade.getSharesHeld().isEmpty()) {
            // zero stocks prevention
            if (trade.getAmountOut() == 0) {
                throw new RuntimeException("<html> Not allowed to buy 0 stocks <html/>");
            }
            // negative buy prevention
            if (trade.getAmountOut() <= 0) {
                throw new RuntimeException("<html> Not allowed to but negative stocks. <br/> Please use the \"Sell\" option. <html/>");
            }
        }

        boolean newAssetIn = !(tradeInputData.getAssetIn().isEmpty() && !portfolio.getHoldings().containsKey(tradeInputData.getAssetIn()));
        boolean newAssetOut = !tradeInputData.getAssetOut().isEmpty() && !portfolio.getHoldings().containsKey(tradeInputData.getAssetOut());
        portfolio.addTrade(trade);

        APIDataAccessObject apiDataAccessObject = new APIDataAccessObject();

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


        // Probably not the cleanest way to do this but it works
        fileDataAccessObject.removePortfolio(portfolio.getName());
        fileDataAccessObject.savePortfolio(portfolio);


        ArrayList<String> symbols = new ArrayList<>();
        ArrayList<Double> prices = new ArrayList<>();
        ArrayList<Double> shares = new ArrayList<>();
        ArrayList<Double> values = new ArrayList<>();
        ArrayList<Double> changes = new ArrayList<>();
        ArrayList<Double> changePercents = new ArrayList<>();

        // establishing what will be showen in the holdings
        for (String symbol : portfolio.getHoldings().keySet()) {
            Tradeable asset = portfolio.getHoldings().get(symbol);
            if (asset.getSharesHeld() != 0){
                symbols.add(asset.getSymbol());
                prices.add(asset.getCurrentPrice());
                shares.add(asset.getSharesHeld());
                values.add(asset.getCurrentValue());
                changes.add(asset.getCurrentPrice() - asset.getPreviousPrice());
                changePercents.add((asset.getCurrentPrice() - asset.getPreviousPrice()) / asset.getPreviousPrice() * 100);
            }

        }

        symbols.add("Total");
        // TODO: should be something meaningful like N/A for total price and shares
        prices.add(0.0);
        shares.add(0.0);
        values.add(portfolio.getPortfolioValue());

        // TODO: these should actually be calculated
        changes.add(0.0);
        changePercents.add(0.0);



        TradeOutputData tradeOutputData = new TradeOutputData(symbols, prices, shares, values, changes, changePercents);
        presenter.present(tradeOutputData);


}}
