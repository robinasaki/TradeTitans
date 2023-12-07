package use_case;

import entity.Portfolio;

import java.util.List;

public interface FileDataAccessInterface {
    public void removePortfolio(String portfolioName);
    public void savePortfolio(Portfolio portfolio);
    public List<Portfolio> loadPortfolios();
    public Portfolio getPortfolio(String portfolioName);
}
