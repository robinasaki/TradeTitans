package data_access;

import entity.Portfolio;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class FileDataAccessObject {
    private static final String FILE_PATH = "portfolioData.ser";

    private void savePortfolios(List<Portfolio> portfolios) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(portfolios);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removePortfolio(String PortfolioName){
        List<Portfolio> portfolios = loadPortfolios();
        portfolios.removeIf(portfolio -> portfolio.getName().equals(PortfolioName));
        savePortfolios(portfolios);
    }

    public void savePortfolio(Portfolio portfolio) {
        List<Portfolio> portfolios = loadPortfolios();
        for (int i = 0; i < portfolios.size(); i++) {
            if (portfolios.get(i).getName().equals(portfolio.getName())) {
                portfolios.set(i, portfolio);
                return;
            }
        }
        portfolios.add(portfolio);
        savePortfolios(portfolios);
    }

    public List<Portfolio> loadPortfolios() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            return (List<Portfolio>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    public Portfolio getPortfolio(String portfolioName) {
        List<Portfolio> portfolios = loadPortfolios();

        for (Portfolio portfolio : portfolios) {
            if (portfolio.getName().equals(portfolioName)) {
                return portfolio;
            }
        }

        throw new IllegalArgumentException("Portfolio not found");
    }
}
