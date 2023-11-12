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
        if (userDataAccessObject.existsByName(TradeInputData.getAmountIn())) {
            userPresenter.prepareFailView("PUT SOMETHING HERE");
        } else {
            LocalDateTime now =LocalDateTime.now();
            User user = userFactory.create(TradeInputData.getAmountIn(), tradeInputData.getTradingFee(), now);
            userDataAccessObject.save(user);

            TradeOutputData tradeOutputData = new TradeOutputData(User.getName(), now.toString(), false);
            userPresenter.prepareSuccessView(tradeOutputData);
        }
    }
}
