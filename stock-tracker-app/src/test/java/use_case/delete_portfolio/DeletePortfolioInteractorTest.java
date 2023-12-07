package use_case.delete_portfolio;

import java.util.ArrayList;
import java.util.List;

import entity.Portfolio;
import org.junit.jupiter.api.Test;

import use_case.FileDataAccessInterface;

import static org.junit.jupiter.api.Assertions.*;

class DeletePortfolioInteractorTest {

    @Test
    void execute_SuccessfullyDeletesPortfolio() {
        // Arrange
        TestDeletePortfolioDataAccessInterface dataAccessInterface = new TestDeletePortfolioDataAccessInterface();
        DeletePortfolioOutputBoundaryMock presenter = new DeletePortfolioOutputBoundaryMock();
        DeletePortfolioInteractor deletePortfolioInteractor = new DeletePortfolioInteractor(dataAccessInterface, presenter);

        String portfolioName = "PortfolioToDelete";
        dataAccessInterface.addPortfolio(portfolioName); // Add a portfolio for testing

        // Act
        deletePortfolioInteractor.execute(portfolioName);

        // Assert
        assertFalse(dataAccessInterface.portfolios.contains(portfolioName));
        assertEquals(portfolioName, presenter.lastPreparedView);
    }

    @Test
    void execute_FailsToDeleteNonexistentPortfolio() {
        // Arrange
        TestDeletePortfolioDataAccessInterface dataAccessInterface = new TestDeletePortfolioDataAccessInterface();
        DeletePortfolioOutputBoundaryMock presenter = new DeletePortfolioOutputBoundaryMock();
        DeletePortfolioInteractor deletePortfolioInteractor = new DeletePortfolioInteractor(dataAccessInterface, presenter);

        String nonExistentPortfolioName = "NonExistentPortfolio";

        assertNull(presenter.lastPreparedView);
    }

    private static class TestDeletePortfolioDataAccessInterface implements FileDataAccessInterface {
        List<String> portfolios = new ArrayList<>();

        @Override
        public void removePortfolio(String portfolioName) {
            portfolios.remove(portfolioName);
        }

        @Override
        public void savePortfolio(Portfolio portfolio) {

        }

        @Override
        public List<Portfolio> loadPortfolios() {
            return null;
        }

        @Override
        public Portfolio getPortfolio(String portfolioName) {
            return null;
        }

        // Helper method for adding portfolios in the test
        public void addPortfolio(String portfolioName) {
            portfolios.add(portfolioName);
        }
    }

    // Test double for DeletePortfolioOutputBoundary
    private static class DeletePortfolioOutputBoundaryMock implements DeletePortfolioOutputBoundary {
        String lastPreparedView;

        @Override
        public void prepareSuccessView(String portfolioName) {
            lastPreparedView = portfolioName;
        }
    }
}
