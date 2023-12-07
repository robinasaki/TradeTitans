package use_case.add_portfolio;

import org.junit.Test;

public class AddPortfolioOutputDataTest {
    private AddPortfolioOutputData testingAddPortfolioOutputData;

    @Test
    public void test() {
        testingAddPortfolioOutputData = new AddPortfolioOutputData(true, true);
        assert testingAddPortfolioOutputData.getSuccessfulCreation();
    }
}
