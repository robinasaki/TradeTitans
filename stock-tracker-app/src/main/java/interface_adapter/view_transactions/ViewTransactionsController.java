package interface_adapter.view_transactions;

import use_case.view_transactions.ViewTransactionsInputBoundary;

public class ViewTransactionsController {
    final ViewTransactionsInputBoundary viewTransactionsInteractor;

    public ViewTransactionsController(ViewTransactionsInputBoundary viewTransactionsInteractor) {
        this.viewTransactionsInteractor = viewTransactionsInteractor;
    }

    public void execute(String portfolioName, String defaultCurrency) {
        viewTransactionsInteractor.execute(portfolioName);
    }
}
