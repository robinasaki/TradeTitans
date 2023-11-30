package app;

import view.PortfolioSelectionView;
import view.HoldingsView;
import view.TradeView;
import view.AddPortfolioView;
import view.ViewManager;
import interface_adapter.ViewManagerModel;
import interface_adapter.portfolio_selection.PortfolioSelectionViewModel;
import interface_adapter.add_portfolio.AddPortfolioViewModel;
import interface_adapter.add_portfolio.AddPortfolioPresenter;
import interface_adapter.add_portfolio.AddPortfolioController;
import interface_adapter.holdings.HoldingsViewModel;
import interface_adapter.holdings.UpdatePricesPresenter;
import interface_adapter.holdings.UpdatePricesController;
import interface_adapter.holdings.HoldingsState;
import interface_adapter.trade.TradeViewModel;
import interface_adapter.trade.TradePresenter;
import interface_adapter.trade.TradeController;
import use_case.add_portfolio.AddPortfolioInteractor;
import use_case.add_portfolio.AddPortfolioInputBoundary;
import use_case.add_portfolio.AddPortfolioOutputBoundary;
import use_case.update_prices.UpdatePricesInteractor;
import use_case.update_prices.UpdatePricesInputBoundary;
import use_case.update_prices.UpdatePricesOutputBoundary;
import use_case.trade.TradeInteractor;
import use_case.trade.TradeInputBoundary;
import use_case.trade.TradeOutputBoundary;
import data_access.APIDataAccessObject;
import data_access.FileDataAccessObject;
import entity.Portfolio;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        JFrame application = new JFrame("Trade Titans");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        application.setPreferredSize(new Dimension(800, 600));

        CardLayout cardLayout = new CardLayout();
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        PortfolioSelectionViewModel portfolioSelectionViewModel = new PortfolioSelectionViewModel();

        HoldingsState emptyHoldingsState = new HoldingsState();
        HoldingsViewModel holdingsViewModel = new HoldingsViewModel();
        holdingsViewModel.setState(emptyHoldingsState);

        AddPortfolioViewModel addPortfolioViewModel = new AddPortfolioViewModel();

        TradeViewModel tradeViewModel = new TradeViewModel();

        FileDataAccessObject fileDataAccessObject = new FileDataAccessObject();

        // Loading portfolios from file
        List<Portfolio> portfolios = fileDataAccessObject.loadPortfolios();

        // Adding portfolios to portfolio selection state
        List<String> portfolioNames = new java.util.ArrayList<>();
        for (Portfolio portfolio : portfolios) {
            portfolioNames.add(portfolio.getName());
        }
        portfolioSelectionViewModel.setPortfolioNames(portfolioNames);

        viewManagerModel.setActiveView("portfolio_selection");
        
        PortfolioSelectionView portfolioSelectionView = createPortfolioSelectionView(portfolioSelectionViewModel, holdingsViewModel, viewManagerModel);
        views.add(portfolioSelectionView, portfolioSelectionView.viewName);

        HoldingsView holdingsView = new HoldingsView(holdingsViewModel, viewManagerModel);
        views.add(holdingsView, "holdings");

        AddPortfolioView addPortfolioView = createAddPortfolioView(addPortfolioViewModel, viewManagerModel, portfolioSelectionViewModel);
        views.add(addPortfolioView, "add_portfolio");

        TradeView tradeView = createTradeView(tradeViewModel, viewManagerModel, holdingsViewModel);
        views.add(tradeView, "trade");

        application.pack();
        application.setVisible(true);
    }

    private static PortfolioSelectionView createPortfolioSelectionView(PortfolioSelectionViewModel portfolioSelectionViewModel, HoldingsViewModel holdingsViewModel, ViewManagerModel viewManagerModel) {
        FileDataAccessObject fileDataAccessObject = new FileDataAccessObject();
        APIDataAccessObject apiDataAccessObject = new APIDataAccessObject();
        UpdatePricesOutputBoundary updatePricesOutputBoundary = new UpdatePricesPresenter(viewManagerModel, holdingsViewModel);
        UpdatePricesInputBoundary updatePricesInputBoundary = new UpdatePricesInteractor(fileDataAccessObject, apiDataAccessObject, updatePricesOutputBoundary);
        UpdatePricesController updatePricesController = new UpdatePricesController(updatePricesInputBoundary);
        return new PortfolioSelectionView(portfolioSelectionViewModel, viewManagerModel, updatePricesController);
    }

    private static AddPortfolioView createAddPortfolioView(AddPortfolioViewModel addPortfolioViewModel, ViewManagerModel viewManagerModel, PortfolioSelectionViewModel portfolioSelectionViewModel) {
        FileDataAccessObject fileDataAccessObject = new FileDataAccessObject();
        AddPortfolioOutputBoundary addPortfolioOutputBoundary = new AddPortfolioPresenter(viewManagerModel, portfolioSelectionViewModel);
        AddPortfolioInputBoundary addPortfolioInputBoundary = new AddPortfolioInteractor(fileDataAccessObject, addPortfolioOutputBoundary);
        AddPortfolioController addPortfolioController = new AddPortfolioController(addPortfolioInputBoundary);
        return new AddPortfolioView(addPortfolioViewModel, viewManagerModel, addPortfolioController);
    }

    private static TradeView createTradeView(TradeViewModel tradeViewModel, ViewManagerModel viewManagerModel, HoldingsViewModel holdingsViewModel) {
        FileDataAccessObject fileDataAccessObject = new FileDataAccessObject();
        TradeOutputBoundary TradeOutputBoundary = new TradePresenter(viewManagerModel, holdingsViewModel);
        TradeInputBoundary TradeInputBoundary = new TradeInteractor(fileDataAccessObject, TradeOutputBoundary);
        TradeController tradeController = new TradeController(TradeInputBoundary);
        return new TradeView(tradeViewModel, viewManagerModel, tradeController);
    }
}
