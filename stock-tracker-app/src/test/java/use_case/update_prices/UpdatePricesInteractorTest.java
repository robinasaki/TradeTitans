package use_case.update_prices;

/* import data_access.APIDataAccessObject;
import data_access.FileDataAccessObject;
import entity.Portfolio;
import entity.Tradeable;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UpdatePricesInteractorTest {

    @Test
    void execute_ShouldUpdatePricesAndPresentOutputData() {
        // Arrange
        StubFileDataAccessObject fileDataAccessObject = new StubFileDataAccessObject();
        StubAPIDataAccessObject apiDataAccessObject = new StubAPIDataAccessObject();
        StubUpdatePricesOutputBoundary presenter = new StubUpdatePricesOutputBoundary();

        UpdatePricesInteractor interactor = new UpdatePricesInteractor(fileDataAccessObject, apiDataAccessObject,
                presenter);

        // Create a test portfolio
        Portfolio testPortfolio = createTestPortfolio();
        fileDataAccessObject.setPortfolio(testPortfolio);

        // Act
        interactor.execute("TestPortfolio");

        // Assert
        assertEquals(1, fileDataAccessObject.savePortfolioCallCount);
        assertEquals(1, presenter.presentCallCount);

        // Verify the updated prices in the portfolio
        Tradeable aapl = testPortfolio.getHoldings().get("AAPL");
        Tradeable googl = testPortfolio.getHoldings().get("GOOGL");

        TreeMap<Date, Double> aaplPriceHistory = aapl.getPriceHistory();
        TreeMap<Date, Double> googlPriceHistory = googl.getPriceHistory();

        assertEquals(150.0, aaplPriceHistory.get(new Date()));
        assertEquals(2500.0, googlPriceHistory.get(new Date()));
    }

    private Portfolio createTestPortfolio() {
        Portfolio portfolio = new Portfolio("TestPortfolio", new Tradeable("USD", "USD"));

        Tradeable apple = new Tradeable("AAPL", "USD");
        apple.setSharesHeld(10.0);
        portfolio.addAsset("AAPL");

        Tradeable google = new Tradeable("GOOGL", "USD");
        google.setSharesHeld(5.0);
        portfolio.addAsset("GOOGL");

        return portfolio;
    }

    private static class StubFileDataAccessObject extends FileDataAccessObject {
        private Portfolio portfolio;
        private int savePortfolioCallCount = 0;

        @Override
        public Portfolio getPortfolio(String portfolioName) {
            return portfolio;
        }

        @Override
        public void savePortfolio(Portfolio portfolio) {
            this.portfolio = portfolio;
            savePortfolioCallCount++;
        }

        public void setPortfolio(Portfolio testPortfolio) {
        }
    }

    private static class StubAPIDataAccessObject extends APIDataAccessObject {
        @Override
        public TreeMap<Date, Double> getHistoricalQuotes(String symbol, String currency) {
            TreeMap<Date, Double> priceHistory = new TreeMap<>();
            priceHistory.put(new Date(), 150.0); // Stubbed price for testing
            return priceHistory;
        }
    }

    private static class StubUpdatePricesOutputBoundary implements UpdatePricesOutputBoundary {
        private int presentCallCount = 0;

        @Override
        public void present(UpdatePricesOutputData outputData) {
            presentCallCount++;
        }
    }
} */
