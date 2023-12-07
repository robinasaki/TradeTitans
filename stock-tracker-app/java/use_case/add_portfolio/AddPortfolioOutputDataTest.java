package test.java.use_case.add_portfolio;

import org.junit.jupiter.api.Test;
import use_case.add_portfolio.AddPortfolioOutputData;

import static org.junit.jupiter.api.Assertions.*;

class AddPortfolioOutputDataTest {

    @Test
    void constructor_ShouldSetValues() {
        // Arrange
        boolean useCaseFailed = false;
        boolean portfolioCreated = true;

        // Act
        AddPortfolioOutputData outputData = new AddPortfolioOutputData(useCaseFailed, portfolioCreated);

        // Assert
        Assertions.assertEquals(useCaseFailed, outputData.getUseCaseFailed());
        Assertions.assertEquals(portfolioCreated, outputData.getSuccessfulCreation());
    }

    @Test
    void getters_ShouldReturnCorrectValues() {
        // Arrange
        boolean useCaseFailed = true;
        boolean portfolioCreated = false;
        AddPortfolioOutputData outputData = new AddPortfolioOutputData(useCaseFailed, portfolioCreated);

        // Act and Assert
        Assertions.assertEquals(useCaseFailed, outputData.getUseCaseFailed());
        Assertions.assertEquals(portfolioCreated, outputData.getSuccessfulCreation());
    }
}
