package use_case.trade;

import data_access.FileDataAccessObject;
import entity.TradeTransaction;
import entity.Portfolio;
import java.time.LocalDateTime;

public class TradeInteractor implements TradeInputBoundary{
    private final FileDataAccessObject fileDataAccessObject;

    final TradeOutputBoundary tradePresenter;

    public TradeInteractor(FileDataAccessObject fileDataAccessObject, TradeOutputBoundary tradePresenter) {
        this.fileDataAccessObject = fileDataAccessObject;
        this.tradePresenter = tradePresenter;
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

        portfolio.addTrade(trade);
        fileDataAccessObject.savePortfolio(portfolio);
        
    }

/*
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
*/
}
