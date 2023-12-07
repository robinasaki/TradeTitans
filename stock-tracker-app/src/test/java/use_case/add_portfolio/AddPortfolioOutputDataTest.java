package use_case.add_portfolio;

import org.junit.jupiter.api.Test;

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
        assertEquals(useCaseFailed, outputData.getUseCaseFailed());
        assertEquals(portfolioCreated, outputData.getSuccessfulCreation());
    }

    @Test
    void getters_ShouldReturnCorrectValues() {
        // Arrange
        boolean useCaseFailed = true;
        boolean portfolioCreated = false;
        AddPortfolioOutputData outputData = new AddPortfolioOutputData(useCaseFailed, portfolioCreated);

        // Act and Assert
        assertEquals(useCaseFailed, outputData.getUseCaseFailed());
        assertEquals(portfolioCreated, outputData.getSuccessfulCreation());
    }
}
