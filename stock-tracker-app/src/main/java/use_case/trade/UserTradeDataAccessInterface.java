package use_case.trade;

public interface UserTradeDataAccessInterface {
    boolean existsByName(String identifier);
    void save(User user);
}
