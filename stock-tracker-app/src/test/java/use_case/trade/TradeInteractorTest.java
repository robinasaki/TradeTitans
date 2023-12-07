package use_case.trade;

import data_access.APIDataAccessObject;
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
import use_case.APIDataAccessInterface;
import use_case.FileDataAccessInterface;

import java.util.ArrayList;
import java.util.Date;

public class TradeInteractorTest {

    private TradeInteractor tradeInteractor;

    @Test
    public void testSellNoEnoughAsset() {
        /**
         * Test selling without enough assets.
         */
        FileDataAccessInterface fileDataAccessObject = new FileDataAccessObject();
        APIDataAccessInterface apiDataAccessInterface = new APIDataAccessObject();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        HoldingsViewModel holdingsViewModel = new HoldingsViewModel();
        TradeOutputBoundary presenter = new TradePresenter(viewManagerModel, holdingsViewModel);
        this.tradeInteractor = new TradeInteractor(apiDataAccessInterface, fileDataAccessObject, presenter);

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
        FileDataAccessInterface fileDataAccessObject = new FileDataAccessObject();
        APIDataAccessInterface apiDataAccessInterface = new APIDataAccessObject();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        HoldingsViewModel holdingsViewModel = new HoldingsViewModel();
        TradeOutputBoundary presenter = new TradePresenter(viewManagerModel, holdingsViewModel);
        this.tradeInteractor = new TradeInteractor(apiDataAccessInterface, fileDataAccessObject, presenter);

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

    private static class TestFileDataAccessObject extends FileDataAccessObject {
        private Portfolio portfolio;

        @Override
        public Portfolio getPortfolio(String portfolioName) {
            return portfolio;
        }

        @Override
        public void removePortfolio(String portfolioName) {

        }

        @Override
        public void savePortfolio(Portfolio portfolio) {
            this.portfolio = portfolio;
        }
    }

    private static class TradeOutputBoundaryMock implements TradeOutputBoundary {
        private boolean wasPresented = false;

        @Override
        public void present(TradeOutputData tradeOutputData) {
            wasPresented = true;
        }

        @Override
        public void prepareFailView(String error) {

        }
    }
}
