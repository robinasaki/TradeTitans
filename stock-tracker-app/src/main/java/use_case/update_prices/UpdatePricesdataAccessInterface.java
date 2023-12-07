package use_case.update_prices;

import entity.Portfolio;

public interface UpdatePricesdataAccessInterface {

    public void savePortfolio(Portfolio portfolio);
    public Portfolio getPortfolio(String portfolioName);
}
