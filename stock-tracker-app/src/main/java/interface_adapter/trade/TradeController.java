package interface_adapter.trade;

import entity.Tradeable;
import use_case.trade.TradeInputBoundary;
import use_case.trade.TradeInputData;

public class TradeController {
    final TradeInputBoundary tradeInteractor;


    public TradeController(TradeInputBoundary tradeInteractor) {
        this.tradeInteractor = tradeInteractor;
    }

    public void execute(String portfolioName, String assetInSymbol, String assetOutSymbol,
                        double amountIn, double amountOut, double tradingFee) {
        TradeInputData tradeInputData = new TradeInputData(portfolioName, tradingFee, assetInSymbol, assetOutSymbol, amountIn, amountOut);
        tradeInteractor.execute(tradeInputData);
    }
}
