package use_case.deposit;

import org.junit.Test;

public class DepositInputDataTest {
    private DepositInputData testingDepositInputData;

    @Test
    public void test() {
        this.testingDepositInputData = new DepositInputData(500);

        assert testingDepositInputData.getAmount() == 500;
    }
}
