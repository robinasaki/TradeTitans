package interface_adapter.view_transactions;

import interface_adapter.ViewManagerModel;
import use_case.view_transactions.ViewTransactionsOutputBoundary;
import use_case.view_transactions.ViewTransactionsOutputData;

public class ViewTransactionsPresenter implements ViewTransactionsOutputBoundary {
    private TransactionsViewModel transactionsViewModel;
    private ViewManagerModel viewManagerModel;

    public ViewTransactionsPresenter(ViewManagerModel viewManagerModel, TransactionsViewModel transactionsViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.transactionsViewModel = transactionsViewModel;
    }

    @Override
    public void present(ViewTransactionsOutputData outputData) {
        viewManagerModel.setActiveView("transactions");
    }
}
