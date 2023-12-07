package use_case.trade;

import data_access.FileDataAccessObject;
import data_access.APIDataAccessObject;
import entity.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class TradeInteractorTest {

    @Test
    void execute_TradeDeposit_Successful() {
        // Arrange
        FileDataAccessObject fileDataAccessObject = new TestFileDataAccessObject();
        TradeOutputBoundaryMock presenter = new TradeOutputBoundaryMock();
        TradeInteractor tradeInteractor = new TradeInteractor(fileDataAccessObject, presenter);
        String portfolioName = "TestPortfolio";
        double tradingFee = 0.01;
        String assetInSymbol = "USD";
        double amountIn = 100.0;
        Date date = new Date();
        TradeInputData tradeInputData = new TradeInputData(portfolioName, tradingFee, assetInSymbol, "", amountIn, 0, date);

        // Act
        tradeInteractor.execute(tradeInputData);

        // Assert
        Portfolio portfolio = fileDataAccessObject.getPortfolio(portfolioName);
        assertNotNull(portfolio);
        assertTrue(portfolio.getHoldings().containsKey(assetInSymbol));
        assertEquals(amountIn, portfolio.getHoldings().get(assetInSymbol).getSharesHeld());
        assertTrue(presenter.wasPresented);
    }

    @Test
    void execute_TradeWithdraw_Successful() {
        // Arrange
        FileDataAccessObject fileDataAccessObject = new TestFileDataAccessObject();
        TradeOutputBoundaryMock presenter = new TradeOutputBoundaryMock();
        TradeInteractor tradeInteractor = new TradeInteractor(fileDataAccessObject, presenter);
        String portfolioName = "TestPortfolio";
        double tradingFee = 0.01;
        String assetOutSymbol = "USD";
        double amountOut = 50.0;
        Date date = new Date();
        TradeInputData tradeInputData = new TradeInputData(portfolioName, tradingFee, "", assetOutSymbol, 0, amountOut, date);

        // Act
        tradeInteractor.execute(tradeInputData);

        // Assert
        Portfolio portfolio = fileDataAccessObject.getPortfolio(portfolioName);
        assertNotNull(portfolio);
        assertTrue(portfolio.getHoldings().containsKey(assetOutSymbol));
        assertEquals(amountOut, portfolio.getHoldings().get(assetOutSymbol).getSharesHeld());
        assertTrue(presenter.wasPresented);
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

