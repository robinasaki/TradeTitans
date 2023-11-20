package use_case.trade;

import entity.User;

public interface UserTradeDataAccessInterface {
    boolean notTradeable();
    void save(User user);
}
