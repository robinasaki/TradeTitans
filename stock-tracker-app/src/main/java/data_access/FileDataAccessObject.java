package data_access;

import entity.Portfolio;
import entity.User;
import java.io.File;
import java.util.HashMap;

public class FileUserDataAccessObject {
    private static final String FILE_PATH = "portfolioData.ser";

    public void savePortfolios(List<Portfolio> portfolios) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(portfolios);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
