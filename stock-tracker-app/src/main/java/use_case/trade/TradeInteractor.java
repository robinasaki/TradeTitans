package use_case.trade;

import entity.TradeTransaction;
import entity.User;
import java.time.LocalDateTime;

public class TradeInteractor implements TradeInputBoundary{
    
    @Override
    public void execute(TradeInputData tradeInputData) {
        TradeTransaction tradeTransaction = new TradeTransaction(tradeInputData.getTradingFee(),
                                                                 tradeInputData.getAssetIn(),
                                                                 tradeInputData.getAssetOut(),
                                                                 tradeInputData.getAmountIn(),
                                                                 tradeInputData.getAmountOut());
        
    }

    @Override
    public void execute(TradeInputData tradeInputData) {
        if (userDataAccessObject.existsByName(tradeInputData.getAmountIn())) {
            tradePresenter.prepareFailView("PUT SOMETHING HERE");
        } else {
            LocalDateTime now =LocalDateTime.now();
            User user = userFactory.create(tradeInputData.getAmountIn(), tradeInputData.getTradingFee(), now);
            userDataAccessObject.save(user);

            TradeOutputData tradeOutputData = new TradeOutputData(user.getName(), now.toString(), false);
            user.Presenter.prepareSuccessView(tradeOutputData);
        }
    }
}
