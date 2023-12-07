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
import interface_adapter.view_price_history.PriceHistoryViewModel;
import interface_adapter.view_price_history.ViewPriceHistoryPresenter;
import interface_adapter.view_price_history.ViewPriceHistoryController;
import interface_adapter.view_transactions.TransactionsViewModel;
import interface_adapter.view_transactions.ViewTransactionsPresenter;
import interface_adapter.view_transactions.ViewTransactionsController;
import use_case.add_portfolio.AddPortfolioInteractor;
import use_case.add_portfolio.AddPortfolioInputBoundary;
import use_case.add_portfolio.AddPortfolioOutputBoundary;
import use_case.delete_portfolio.DeletePortfolioInteractor;
import use_case.delete_portfolio.DeletePortfolioInputBoundary;
import use_case.delete_portfolio.DeletePortfolioOutputBoundary;
import use_case.view_price_history.ViewPriceHistoryInputBoundary;
import use_case.view_price_history.ViewPriceHistoryOutputBoundary;
import use_case.view_price_history.ViewPriceHistoryInteractor;
import use_case.update_prices.UpdatePricesInteractor;
import use_case.update_prices.UpdatePricesInputBoundary;
import use_case.update_prices.UpdatePricesOutputBoundary;
import use_case.trade.TradeInteractor;
import use_case.trade.TradeInputBoundary;
import use_case.trade.TradeOutputBoundary;
import use_case.view_transactions.ViewTransactionsOutputBoundary;
import use_case.view_transactions.ViewTransactionsInputBoundary;
import use_case.view_transactions.ViewTransactionsInteractor;
import use_case.APIDataAccessInterface;
import use_case.FileDataAccessInterface;
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
        FileDataAccessInterface fileDataAccessObject = new FileDataAccessObject();
        AddPortfolioOutputBoundary addPortfolioOutputBoundary = new AddPortfolioPresenter(viewManagerModel, portfolioSelectionViewModel);
        AddPortfolioInputBoundary addPortfolioInputBoundary = new AddPortfolioInteractor(fileDataAccessObject, addPortfolioOutputBoundary);
        AddPortfolioController addPortfolioController = new AddPortfolioController(addPortfolioInputBoundary);
        return new AddPortfolioView(addPortfolioViewModel, viewManagerModel, addPortfolioController);
    }

    protected static DeletePortfolioView createDeletePortfolioView(DeletePortfolioViewModel deletePortfolioViewModel, ViewManagerModel viewManagerModel, PortfolioSelectionViewModel portfolioSelectionViewModel) {
        FileDataAccessInterface fileDataAccessObject = new FileDataAccessObject();
        DeletePortfolioOutputBoundary deletePortfolioOutputBoundary = new DeletePortfolioPresenter(viewManagerModel, portfolioSelectionViewModel);
        DeletePortfolioInputBoundary deletePortfolioInputBoundary = new DeletePortfolioInteractor(fileDataAccessObject, deletePortfolioOutputBoundary);
        DeletePortfolioController deletePortfolioController = new DeletePortfolioController(deletePortfolioInputBoundary);
        return new DeletePortfolioView(deletePortfolioViewModel, viewManagerModel, deletePortfolioController);
    }

    protected static TradeView createTradeView(TradeViewModel tradeViewModel, ViewManagerModel viewManagerModel, HoldingsViewModel holdingsViewModel) {
        APIDataAccessInterface apiDataAccessObject = new APIDataAccessObject();
        FileDataAccessInterface fileDataAccessObject = new FileDataAccessObject();
        TradeOutputBoundary tradeOutputBoundary = new TradePresenter(viewManagerModel, holdingsViewModel);
        TradeInputBoundary TradeInputBoundary = new TradeInteractor(apiDataAccessObject, fileDataAccessObject, tradeOutputBoundary);
        TradeController tradeController = new TradeController(TradeInputBoundary);
        return new TradeView(tradeViewModel, viewManagerModel, tradeController);
    }

    protected static CreditView createCreditView(CreditViewModel creditViewModel, ViewManagerModel viewManagerModel) {
        return new CreditView(creditViewModel, viewManagerModel);
    }

    protected static TransactionsView createTransactionsView(TransactionsViewModel transactionsViewModel, ViewManagerModel viewManagerModel) {
        return new TransactionsView(transactionsViewModel, viewManagerModel);
    }

    protected static PriceHistoryView createPriceHistoryView(PriceHistoryViewModel priceHistoryViewModel, ViewManagerModel viewManagerModel) {
        return new PriceHistoryView(priceHistoryViewModel, viewManagerModel);
    }

    protected static HoldingsView createHoldingsView(HoldingsViewModel holdingsViewModel, ViewManagerModel viewManagerModel, TradeViewModel tradeViewModel, TransactionsViewModel transactionsViewModel, PriceHistoryViewModel priceHistoryViewModel) {
        FileDataAccessObject fileDataAccessObject = new FileDataAccessObject();
        ViewTransactionsOutputBoundary viewTransactionsOutputBoundary = new ViewTransactionsPresenter(viewManagerModel, transactionsViewModel);
        ViewTransactionsInputBoundary viewTransactionsInputBoundary = new ViewTransactionsInteractor(fileDataAccessObject, viewTransactionsOutputBoundary);
        ViewTransactionsController viewTransactionsController = new ViewTransactionsController(viewTransactionsInputBoundary);
        ViewPriceHistoryOutputBoundary viewPriceHistoryOutputBoundary = new ViewPriceHistoryPresenter(priceHistoryViewModel, viewManagerModel);
        ViewPriceHistoryInputBoundary viewPriceHistoryInputBoundary = new ViewPriceHistoryInteractor(fileDataAccessObject, viewPriceHistoryOutputBoundary);
        ViewPriceHistoryController viewPriceHistoryController = new ViewPriceHistoryController(viewPriceHistoryInputBoundary);
        return new HoldingsView(holdingsViewModel, viewManagerModel, tradeViewModel, viewTransactionsController, viewPriceHistoryController);
    }
}
