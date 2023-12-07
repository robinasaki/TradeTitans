package use_case.trade;

<<<<<<< HEAD
public interface TradeDataAccessInterface {
    // TODO
=======
import entity.Portfolio;
import entity.User;

public interface TradeDataAccessInterface {
    public Portfolio getPortfolio(String portfolioName);
    public void removePortfolio(String PortfolioName);
    public void savePortfolio(Portfolio portfolio);
>>>>>>> main
}
