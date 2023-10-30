package use_cases;

import use_cases.TradeInputBoundary;
import use_cases.TradeInputData;
import entity.TradeTransaction;

public class TradeInteractor implements TradeInputBoundary{
    
    @Override
    public void execute(TradeInputData tradeInputData,) {
        TradeTransaction tradeTransaction = new TradeTransaction(tradeInputData.getTradingFee(), tradeInputData.getAssetIn, tradeInputData.getAssetOut(), tradeInputData.getAmountIn(), tradeInputData.getAmountOut());
        
    }
}
