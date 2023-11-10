package data_access;

import entity.Portfolio;
import entity.User;

public class FileUserDataAccessObject {

    private final File csvFile;

    private final Map<String, Portfolio> portfolios;

    public FileUserDataAccessObject(String csvPath, User user) {}
}
