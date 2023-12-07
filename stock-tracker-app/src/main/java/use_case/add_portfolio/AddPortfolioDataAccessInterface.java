package use_case.add_portfolio;

import entity.Portfolio;

import java.util.List;

public interface AddPortfolioDataAccessInterface {

    public List<Portfolio> loadPortfolios();
    public void savePortfolio(Portfolio portfolio);

}
