package interface_adapter;

import entity.Tradeable;
import use_case.trade.TradeInputBoundary;
import use_case.trade.TradeInputData;

public class TradeController {
    final TradeInputBoundary tradeInteractor;

    public TradeController(TradeInputBoundary tradeInteractor) {
        this.tradeInteractor = tradeInteractor;
    }

    public void execute(String portfolioName, double tradingFee, Tradeable assetIn, Tradeable assetOut,
                        double amountIn, double amountOut) {
        TradeInputData tradeInputData = new TradeInputData(portfolioName, tradingFee, assetIn, assetOut,
                amountIn, amountOut);
        tradeInteractor.execute(tradeInputData);
    }
}
