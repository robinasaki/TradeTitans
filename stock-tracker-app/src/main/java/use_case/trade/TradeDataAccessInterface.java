package use_case.trade;

import entity.User;

public interface TradeDataAccessInterface {
    boolean notTradeable();
    void save(User user);
}
