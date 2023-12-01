package use_case.trade;

import data_access.FileDataAccessObject;
import entity.TradeTransaction;
import entity.Portfolio;
import java.time.LocalDateTime;

public class TradeInteractor implements TradeInputBoundary{
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

        portfolio.addTrade(trade);

        // Probably not the cleanest way to do this but it works
        fileDataAccessObject.removePortfolio(portfolio.getName());
        fileDataAccessObject.savePortfolio(portfolio);

        presenter.present();
        
    }
}
