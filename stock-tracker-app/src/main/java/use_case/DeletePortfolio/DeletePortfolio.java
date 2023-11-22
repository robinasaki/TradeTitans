package use_case.DeletePortfolio;

import entity.Portfolio;
import data_access.FileDataAccessObject;

public class DeletePortfolio {
    private final FileDataAccessObject fileDataAccessObject;

    public DeletePortfolio(FileDataAccessObject fileDataAccessObject) {
        this.fileDataAccessObject = fileDataAccessObject;
    }

    public void execute(Portfolio portfolio){
        fileDataAccessObject.removePortfolio(portfolio.getName());
    }
}
