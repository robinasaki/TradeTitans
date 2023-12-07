package java.use_case.trade;

import data_access.FileDataAccessObject;
import entity.Portfolio;
import entity.TradeTransaction;
import interface_adapter.ViewManagerModel;
import interface_adapter.holdings.HoldingsState;
import interface_adapter.holdings.HoldingsViewModel;
import interface_adapter.trade.TradePresenter;
import org.junit.Assert.*;
import org.junit.Test;
import entity.Tradeable;
import use_case.trade.TradeInputData;
import use_case.trade.TradeInteractor;
import use_case.trade.TradeOutputBoundary;

import java.util.ArrayList;
import java.util.Date;

public class TradeInteractorTest {

    private TradeInteractor tradeInteractor;

    @Test
    public void testSellNoEnoughAsset() {
        /**
         * Test selling without enough assets.
         */
        FileDataAccessObject fileDataAccessObject = new FileDataAccessObject();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        HoldingsViewModel holdingsViewModel = new HoldingsViewModel();
        TradeOutputBoundary presenter = new TradePresenter(viewManagerModel, holdingsViewModel);
        this.tradeInteractor = new TradeInteractor(fileDataAccessObject, presenter);

        Portfolio hypoPortfolio = new Portfolio("TradeInteractorTestProfile", new Tradeable("the US Dollar", "$USD"));
        fileDataAccessObject.savePortfolio(hypoPortfolio);

        // suppose user wants to sell an IBM stock without having it
        TradeInputData tradeInputData = new TradeInputData("TradeInteractorTestProfile", 10.99, "$USD", "IBM", 50.00, 1, new Date(123, 9, 11));
        try {
            tradeInteractor.execute(tradeInputData);
        } catch (RuntimeException exp) {
            if (exp.getMessage().contains("Code100")) {
                // Code100 should be dealt in the TradeView
                assert true;
            } else {
                assert false;
            }
        }
        fileDataAccessObject.removePortfolio("TradeInteractorTestProfile");
    }

    @Test
    public void testBuyNoEnoughCurrency() {
        /**
         * Test buying without enough default currency.
         */
        FileDataAccessObject fileDataAccessObject = new FileDataAccessObject();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        HoldingsViewModel holdingsViewModel = new HoldingsViewModel();
        TradeOutputBoundary presenter = new TradePresenter(viewManagerModel, holdingsViewModel);
        this.tradeInteractor = new TradeInteractor(fileDataAccessObject, presenter);

        Portfolio hypoPortfolio = new Portfolio("TradeInteractorTestProfile", new Tradeable("the US Dollar", "$USD"));
        fileDataAccessObject.savePortfolio(hypoPortfolio);

        // suppose user wants to buy an IBM stock without having it
        TradeInputData tradeInputData = new TradeInputData("TradeInteractorTestProfile", 10.99, "IBM", "$USD", 1.00, 50.00, new Date(123, 9, 11));
        try {
            tradeInteractor.execute(tradeInputData);
        } catch (RuntimeException exp) {
            if (exp.getMessage().contains("Code100")) {
                // Code100 should be dealt in the TradeView
                assert true;
            } else {
                assert false;
            }
        }
        fileDataAccessObject.removePortfolio("TradeInteractorTestProfile");
    }

//    @Test
//    public void testBuy() {
//        /**
//         * Test buying with enough currency.
//         */
//
//        FileDataAccessObject fileDataAccessObject = new FileDataAccessObject();
//        Portfolio hypoPortfolio = new Portfolio("TradeInteractorTestProfile", new Tradeable("the US Dollar", "$USD"));
//        fileDataAccessObject.savePortfolio(hypoPortfolio);
//        ViewManagerModel viewManagerModel = new ViewManagerModel();
//        HoldingsViewModel holdingsViewModel = new HoldingsViewModel();
//
//        HoldingsState hypoState = new HoldingsState();
//
//        hypoState.setPortfolioName(hypoPortfolio.getName());
//        hypoState.setDefaultCurrency(hypoPortfolio.getCurrency().getSymbol());
//
//        ArrayList<String> symbols = new ArrayList<>(hypoPortfolio.getHoldings().keySet());
//        hypoState.setSymbols(symbols);
//
//
//        holdingsViewModel.setState(hypoState);
//        TradeOutputBoundary presenter = new TradePresenter(viewManagerModel, holdingsViewModel);
//        this.tradeInteractor = new TradeInteractor(fileDataAccessObject, presenter);
//
//        // user deposits 500 $USD
//        TradeInputData deposit = new TradeInputData("TradeInteractorTestProfile", 0.00, "$USD", "", 500.00, 0.00, new Date(123, 9, 11));
//        tradeInteractor.execute(deposit);
//
//        assert (hypoPortfolio.getHoldings().get("TradeInteractorTestProfile").getSharesHeld() == 500);
//
//        // TODO: remove the portfolio
//        fileDataAccessObject.removePortfolio("TradeInteractorTestProfile");
//    }
}
