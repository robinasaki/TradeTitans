package entity;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

public class BankingTransactionTest {
    /**
     * BankingTransaction should be use cases such as deposit, withdraw, etc.
     */
    private Tradeable ibm;
    private Tradeable usd;
    private boolean deposit;
    private double amount;

    private double fee;

    @BeforeEach
    public void init() {
        usd = new Tradeable("US Dollar", "$USD");
        deposit = false;
        amount = 500;
        fee = 0.5;
    }

    @Test
    public void testTransaction() {
        BankingTransaction testing = new BankingTransaction(fee, usd, deposit, amount);
        assert (testing.getAsset() == usd);
        assert (testing.getAmount() == amount);
    }
}
