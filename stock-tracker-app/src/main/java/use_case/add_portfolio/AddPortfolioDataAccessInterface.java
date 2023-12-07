package use_case.add_portfolio;

import entity.Portfolio;

import java.util.List;

public interface AddPortfolioDataAccessInterface {
    List<Portfolio> loadPortfolios();
    void savePortfolio(Portfolio portfolio);

}
