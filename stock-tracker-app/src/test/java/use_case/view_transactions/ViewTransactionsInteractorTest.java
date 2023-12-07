package use_case.view_transactions;

import data_access.FileDataAccessObject;
import entity.Portfolio;
import entity.TradeTransaction;
import entity.Tradeable;
import interface_adapter.ViewManagerModel;
import interface_adapter.view_transactions.TransactionsViewModel;
import interface_adapter.view_transactions.ViewTransactionsPresenter;
import org.junit.Test;

import java.util.Date;

public class ViewTransactionsInteractorTest {
    private ViewTransactionsInteractor viewTransactionsInteractor;

    @Test
    public void test() {
        FileDataAccessObject fileDataAccessObject = new FileDataAccessObject();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        TransactionsViewModel transactionsViewModel = new TransactionsViewModel();
        ViewTransactionsOutputBoundary presenter = new ViewTransactionsPresenter(viewManagerModel, transactionsViewModel);

        this.viewTransactionsInteractor = new ViewTransactionsInteractor(fileDataAccessObject, presenter);

        Portfolio viewTransactionInteractorTestPortfolio = new Portfolio("viewTransactionInteractorTestPortfolio", new Tradeable("the US Dollar", "$USD"));
        fileDataAccessObject.savePortfolio(viewTransactionInteractorTestPortfolio);
        // user deposits USD$500
        viewTransactionInteractorTestPortfolio.addTrade(new TradeTransaction("$USD", "", 500.00, 0.00, 0.00, new Date(123, 9, 11)));
        // user spends USD$150 on 1 stock of IBM
        viewTransactionInteractorTestPortfolio.addTrade(new TradeTransaction("IBM", "$USD", 1, 150, 0.00, new Date(123, 9, 11)));

        viewTransactionsInteractor.execute("viewTransactionInteractorTestPortfolio");

        fileDataAccessObject.removePortfolio("viewTransactionInteractorTestPortfolio");
    }
}
