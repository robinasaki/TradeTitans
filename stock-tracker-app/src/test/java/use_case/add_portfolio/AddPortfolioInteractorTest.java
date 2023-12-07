package use_case.add_portfolio;

import entity.Portfolio;
import entity.Tradeable;
import data_access.FileDataAccessObject;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class AddPortfolioInteractorTest {

    @Test
    void execute_SuccessfullyAddsPortfolio() {
        // Arrange
        TestFileDataAccessObject fileDataAccessObject = new TestFileDataAccessObject();
        AddPortfolioOutputBoundaryMock presenter = new AddPortfolioOutputBoundaryMock();
        AddPortfolioInteractor addPortfolioInteractor = new AddPortfolioInteractor(fileDataAccessObject, presenter);

        String portfolioName = "NewPortfolio";
        String defaultCurrency = "$USD";

        // Act
        addPortfolioInteractor.execute(portfolioName, defaultCurrency);

        // Assert
        assertTrue(fileDataAccessObject.savedPortfolio != null && fileDataAccessObject.savedPortfolio.getName().equals(portfolioName));
    }

    @Test
    void execute_FailsToAddPortfolioWhenNameAlreadyExists() {
        // Arrange
        TestFileDataAccessObject fileDataAccessObject = new TestFileDataAccessObject();
        AddPortfolioOutputBoundaryMock presenter = new AddPortfolioOutputBoundaryMock();
        AddPortfolioInteractor addPortfolioInteractor = new AddPortfolioInteractor(fileDataAccessObject, presenter);

        String existingPortfolioName = "ExistingPortfolio";
        String defaultCurrency = "$USD";

        Portfolio existingPortfolio = new Portfolio(existingPortfolioName, new Tradeable("currency", defaultCurrency));
        fileDataAccessObject.portfolios.add(existingPortfolio);

        assertNull(fileDataAccessObject.savedPortfolio);
        assertNull(presenter.lastPreparedView);
    }

    // Test double for FileDataAccessObject
    private static class TestFileDataAccessObject extends FileDataAccessObject {
        List<Portfolio> portfolios = new ArrayList<>();
        Portfolio savedPortfolio;

        @Test
        public List<Portfolio> loadPortfolios() {
            return portfolios;
        }

        @Test
        public void savePortfolio(Portfolio portfolio) {
            savedPortfolio = portfolio;
        }
    }

    // Test double for AddPortfolioOutputBoundary
    private static class AddPortfolioOutputBoundaryMock implements AddPortfolioOutputBoundary {
        public String lastPreparedView;
        String lastPreparedSuccessView;

        @Test
        public void prepareSuccessView(String portfolioName) {
            lastPreparedSuccessView = portfolioName;
        }

        @Test
        public void prepareFailView(String error) {

        }
    }
}
