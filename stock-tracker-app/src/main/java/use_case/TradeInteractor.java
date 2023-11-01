package use_case;

import use_case.TradeInputBoundary;
import use_case.TradeInputData;
import entity.TradeTransaction;

public class TradeInteractor implements TradeInputBoundary{
    
    @Override
    public void execute(TradeInputData tradeInputData) {
        TradeTransaction tradeTransaction = new TradeTransaction(tradeInputData.getTradingFee(), tradeInputData.getAssetIn, tradeInputData.getAssetOut(), tradeInputData.getAmountIn(), tradeInputData.getAmountOut());
        
    }
}
