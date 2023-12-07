package use_case.add_portfolio;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class AddPortfolioInputDataTest {

    @Test
    void constructor_ShouldSetValues() {
        // Arrange
        String portfolioName = "TestPortfolio";
        String defaultCurrency = "USD";

        // Act
        AddPortfolioInputData inputData = new AddPortfolioInputData(portfolioName, defaultCurrency);

        // Assert
        assertEquals(portfolioName, inputData.getPortfolioName());
        assertEquals(defaultCurrency, inputData.getDefaultCurrency());
    }

    @Test
    void getters_ShouldReturnCorrectValues() {
        // Arrange
        String portfolioName = "AnotherPortfolio";
        String defaultCurrency = "EUR";
        AddPortfolioInputData inputData = new AddPortfolioInputData(portfolioName, defaultCurrency);

        // Act and Assert
        assertEquals(portfolioName, inputData.getPortfolioName());
        assertEquals(defaultCurrency, inputData.getDefaultCurrency());
    }
}

