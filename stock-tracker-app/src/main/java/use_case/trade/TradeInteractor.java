package use_case.trade;

import entity.TradeTransaction;
import entity.User;
import entity.UserFactory;
import java.time.LocalDateTime;

public class TradeInteractor implements TradeInputBoundary{
    final UserTradeDataAccessInterface userDataAccessObject;
    final TradeOutputBoundary userPresenter;
    final UserFactory userFactory;

    public TradeInteractor(UserTradeDataAccessInterface userDataAccessObject, TradeOutputBoundary userPresenter, UserFactory userFactory) {
        this.userDataAccessObject = userDataAccessObject;
        this.userPresenter = userPresenter;
        this.userFactory = userFactory;
    }

/* Not sure why there are two of these
    @Override
    public void execute(TradeInputData tradeInputData) {
        TradeTransaction tradeTransaction = new TradeTransaction(tradeInputData.getTradingFee(),
                                                                 tradeInputData.getAssetIn(),
                                                                 tradeInputData.getAssetOut(),
                                                                 tradeInputData.getAmountIn(),
                                                                 tradeInputData.getAmountOut());
        
    }
*/

    @Override
    public void execute(TradeInputData tradeInputData) {
        if (userDataAccessObject.existsByName(tradeInputData.getAmountIn())) {
            userPresenter.prepareFailView("PUT SOMETHING HERE");
        } else {
            LocalDateTime now =LocalDateTime.now();
            User user = userFactory.create(tradeInputData.getAmountIn(), tradeInputData.getTradingFee(), now);
            userDataAccessObject.save(user);

            TradeOutputData tradeOutputData = new TradeOutputData(user.getName(), now.toString(), false);
            userPresenter.prepareSuccessView(tradeOutputData);
        }
    }
}
