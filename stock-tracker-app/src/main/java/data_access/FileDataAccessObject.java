package data_access;

import entity.Portfolio;
import entity.User;
import java.util.HashMap;

public class FileUserDataAccessObject {

    private final File csvFile;

    private final HashMap<String, Portfolio> portfolios;

    public FileUserDataAccessObject(String csvPath, User user, HashMap<String, Portfolio> portfolios) {
        this.portfolios = portfolios;
    }
}
