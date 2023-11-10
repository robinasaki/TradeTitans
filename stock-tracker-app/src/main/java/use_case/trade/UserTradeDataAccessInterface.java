package use_case.trade;

import entity.User;

public interface UserTradeDataAccessInterface {
    boolean existsByName(String identifier);
    void save(User user);
}
