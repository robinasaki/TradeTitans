package use_case.DeletePortfolio;

import entity.Portfolio;
import data_access.FileDataAccessObject;

public class DeletePortfolio {
    Private final FileDataAccessObject fileDataAccessObject;

    public DeletePortfolio(FileDataAccessObject fileDataAccessObject) {
        this.fileDataAccessObject = fileDataAccessObject;
    }

    Public void execute(Portfolio portfolio){
        fileDataAccessObject.removePortfolio(portfolio.getName());
    }

}
