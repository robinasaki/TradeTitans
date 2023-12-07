package interface_adapter.view_transactions;

import interface_adapter.ViewManagerModel;
import use_case.view_transactions.ViewTransactionsOutputBoundary;
import use_case.view_transactions.ViewTransactionsOutputData;

import java.util.ArrayList;

public class ViewTransactionsPresenter implements ViewTransactionsOutputBoundary {
    private TransactionsViewModel transactionsViewModel;
    private ViewManagerModel viewManagerModel;

    public ViewTransactionsPresenter(ViewManagerModel viewManagerModel, TransactionsViewModel transactionsViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.transactionsViewModel = transactionsViewModel;
    }

    @Override
    public void present(ViewTransactionsOutputData outputData) {
        TransactionsState state = new TransactionsState();
        state.setPortfolioName(outputData.getPortfolioName());
        state.setDefaultCurrency(outputData.getDefaultCurrency());

        state.setTradeTypes(new ArrayList<String>());
        for (int i = 0; i < outputData.getAssetsIn().size(); i++) {
            if (outputData.getAssetsIn().get(i).equals("")) {
                state.getTradeTypes().add("Withdraw");
            }
            else if (outputData.getAssetsOut().get(i).equals("")) {
                state.getTradeTypes().add("Deposit");
            }
            else if (outputData.getAssetsIn().get(i).equals(outputData.getDefaultCurrency())) {
                state.getTradeTypes().add("Sell");
            }
            else if (outputData.getAssetsOut().get(i).equals(outputData.getDefaultCurrency())) {
                state.getTradeTypes().add("Buy");
            }
        }

        state.setSymbols(new ArrayList<String>());
        for (int i = 0; i < outputData.getAssetsIn().size(); i++) {
            if (state.getTradeTypes().get(i).equals("Withdraw")) {
                state.getSymbols().add(outputData.getAssetsOut().get(i));
            }
            else if (state.getTradeTypes().get(i).equals("Deposit")) {
                state.getSymbols().add(outputData.getAssetsIn().get(i));
            }
            else if (state.getTradeTypes().get(i).equals("Sell")) {
                state.getSymbols().add(outputData.getAssetsOut().get(i));
            }
            else if (state.getTradeTypes().get(i).equals("Buy")) {
                state.getSymbols().add(outputData.getAssetsIn().get(i));
            }
        }

        state.setAmounts(new ArrayList<String>());
        for (int i = 0; i < outputData.getAssetsIn().size(); i++) {
            if (state.getTradeTypes().get(i).equals("Withdraw")) {
                state.getAmounts().add(String.valueOf(outputData.getAmountsOut().get(i)));
            }
            else if (state.getTradeTypes().get(i).equals("Deposit")) {
                state.getAmounts().add(String.valueOf(outputData.getAmountsIn().get(i)));
            }
            else if (state.getTradeTypes().get(i).equals("Sell")) {
                double price = outputData.getAmountsIn().get(i) / outputData.getAmountsOut().get(i);
                state.getAmounts().add(String.valueOf(price) + " x " + String.valueOf(outputData.getAmountsOut().get(i)) + " shares");
            }
            else if (state.getTradeTypes().get(i).equals("Buy")) {
                double price = outputData.getAmountsOut().get(i) / outputData.getAmountsIn().get(i);
                state.getAmounts().add(String.valueOf(price) + " x " + String.valueOf(outputData.getAmountsIn().get(i)) + " shares");
            }
        }

        state.setDates(outputData.getDates());
        transactionsViewModel.setState(state);
        viewManagerModel.setActiveView("transactions");
    }
}
