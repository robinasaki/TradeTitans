package use_case.deposit;

import java.util.Date;
import java.util.TreeMap;

public interface DepositDataAccessInterface {

    boolean negativeDeposit();

    void save();
}
