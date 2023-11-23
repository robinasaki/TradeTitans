package data_access;

import java.util.Date;
import java.util.TreeMap;

public interface DepositDataAccessInterface {

    boolean negativeDeposit();

    void save();
}
