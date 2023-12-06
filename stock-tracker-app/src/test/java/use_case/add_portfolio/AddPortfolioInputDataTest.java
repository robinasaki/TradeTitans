package use_case.add_portfolio;

import org.junit.Test;

public class AddPortfolioInputDataTest {
    private AddPortfolioInputData testingInputData;

    @Test
    public void test() {
        testingInputData = new AddPortfolioInputData("robin", "$USD");
        assert testingInputData.getPortfolioName().equals("robin");
        assert testingInputData.getDefaultCurrency().equals("$USD");
    }
}
