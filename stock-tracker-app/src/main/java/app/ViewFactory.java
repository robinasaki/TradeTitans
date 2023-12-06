package app;

import view.*;
import interface_adapter.ViewManagerModel;
import interface_adapter.credit.CreditViewModel;
import interface_adapter.portfolio_selection.PortfolioSelectionViewModel;
import interface_adapter.add_portfolio.AddPortfolioViewModel;
import interface_adapter.add_portfolio.AddPortfolioPresenter;
import interface_adapter.add_portfolio.AddPortfolioController;
import interface_adapter.delete_portfolio.DeletePortfolioPresenter;
import interface_adapter.delete_portfolio.DeletePortfolioController;
import interface_adapter.delete_portfolio.DeletePortfolioViewModel;
import interface_adapter.holdings.HoldingsViewModel;
import interface_adapter.holdings.UpdatePricesPresenter;
import interface_adapter.holdings.UpdatePricesController;
import interface_adapter.trade.TradeViewModel;
import interface_adapter.trade.TradePresenter;
import interface_adapter.trade.TradeController;
import interface_adapter.view_transactions.TransactionsViewModel;
import use_case.add_portfolio.AddPortfolioInteractor;
import use_case.add_portfolio.AddPortfolioInputBoundary;
import use_case.add_portfolio.AddPortfolioOutputBoundary;
import use_case.delete_portfolio.DeletePortfolioInteractor;
import use_case.delete_portfolio.DeletePortfolioInputBoundary;
import use_case.delete_portfolio.DeletePortfolioOutputBoundary;
import use_case.update_prices.UpdatePricesInteractor;
import use_case.update_prices.UpdatePricesInputBoundary;
import use_case.update_prices.UpdatePricesOutputBoundary;
import use_case.trade.TradeInteractor;
import use_case.trade.TradeInputBoundary;
import use_case.trade.TradeOutputBoundary;
import data_access.APIDataAccessObject;
import data_access.FileDataAccessObject;

public class ViewFactory {

    protected static PortfolioSelectionView createPortfolioSelectionView(PortfolioSelectionViewModel portfolioSelectionViewModel, HoldingsViewModel holdingsViewModel, DeletePortfolioViewModel deletePortfolioViewModel, ViewManagerModel viewManagerModel) {
        FileDataAccessObject fileDataAccessObject = new FileDataAccessObject();
        APIDataAccessObject apiDataAccessObject = new APIDataAccessObject();
        UpdatePricesOutputBoundary updatePricesOutputBoundary = new UpdatePricesPresenter(viewManagerModel, holdingsViewModel);
        UpdatePricesInputBoundary updatePricesInputBoundary = new UpdatePricesInteractor(fileDataAccessObject, apiDataAccessObject, updatePricesOutputBoundary);
        UpdatePricesController updatePricesController = new UpdatePricesController(updatePricesInputBoundary);
        return new PortfolioSelectionView(portfolioSelectionViewModel, viewManagerModel, updatePricesController, deletePortfolioViewModel);
    }

    protected static AddPortfolioView createAddPortfolioView(AddPortfolioViewModel addPortfolioViewModel, ViewManagerModel viewManagerModel, PortfolioSelectionViewModel portfolioSelectionViewModel) {
        FileDataAccessObject fileDataAccessObject = new FileDataAccessObject();
        AddPortfolioOutputBoundary addPortfolioOutputBoundary = new AddPortfolioPresenter(viewManagerModel, portfolioSelectionViewModel);
        AddPortfolioInputBoundary addPortfolioInputBoundary = new AddPortfolioInteractor(fileDataAccessObject, addPortfolioOutputBoundary);
        AddPortfolioController addPortfolioController = new AddPortfolioController(addPortfolioInputBoundary);
        return new AddPortfolioView(addPortfolioViewModel, viewManagerModel, addPortfolioController);
    }

    protected static DeletePortfolioView createDeletePortfolioView(DeletePortfolioViewModel deletePortfolioViewModel, ViewManagerModel viewManagerModel, PortfolioSelectionViewModel portfolioSelectionViewModel) {
        FileDataAccessObject fileDataAccessObject = new FileDataAccessObject();
        DeletePortfolioOutputBoundary deletePortfolioOutputBoundary = new DeletePortfolioPresenter(viewManagerModel, portfolioSelectionViewModel);
        DeletePortfolioInputBoundary deletePortfolioInputBoundary = new DeletePortfolioInteractor(fileDataAccessObject, deletePortfolioOutputBoundary);
        DeletePortfolioController deletePortfolioController = new DeletePortfolioController(deletePortfolioInputBoundary);
        return new DeletePortfolioView(deletePortfolioViewModel, viewManagerModel, deletePortfolioController);
    }

    protected static TradeView createTradeView(TradeViewModel tradeViewModel, ViewManagerModel viewManagerModel, HoldingsViewModel holdingsViewModel) {
        FileDataAccessObject fileDataAccessObject = new FileDataAccessObject();
        TradeOutputBoundary tradeOutputBoundary = new TradePresenter(viewManagerModel, holdingsViewModel);
        TradeInputBoundary TradeInputBoundary = new TradeInteractor(fileDataAccessObject, tradeOutputBoundary);
        TradeController tradeController = new TradeController(TradeInputBoundary);
        return new TradeView(tradeViewModel, viewManagerModel, tradeController);
    }

    protected static CreditView createCreditView(CreditViewModel creditViewModel, ViewManagerModel viewManagerModel) {
        return new CreditView(creditViewModel, viewManagerModel);
    }

    protected static TransactionsView createTransactionsView(TransactionsViewModel transactionsViewModel, ViewManagerModel viewManagerModel) {
        return new TransactionsView(transactionsViewModel, viewManagerModel);
    }
}
