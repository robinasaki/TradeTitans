package use_case.trade;
import entity.Portfolio;

public interface TradeDataAccessInterface {
    public Portfolio getPortfolio(String portfolioName);
    public void removePortfolio(String PortfolioName);
    public void savePortfolio(Portfolio portfolio);
}
