package app;

import view.PortfolioSelectionView;
import view.HoldingsView;
import view.TradeView;
import view.ViewManager;
import interface_adapter.ViewManagerModel;
import interface_adapter.portfolio_selection.PortfolioSelectionViewModel;
import interface_adapter.holdings.HoldingsViewModel;
import interface_adapter.holdings.UpdatePricesPresenter;
import interface_adapter.holdings.UpdatePricesController;
import interface_adapter.holdings.HoldingsState;
import interface_adapter.trade.TradeViewModel;
import use_case.update_prices.UpdatePricesInteractor;
import use_case.update_prices.UpdatePricesInputBoundary;
import use_case.update_prices.UpdatePricesOutputBoundary;
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
        //TradeViewModel tradeViewModel = new TradeViewModel();

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
        //TradeView tradeView = new TradeView(tradeViewModel);
        //views.add(tradeView, "tradeView");

        //viewManagerModel.firePropertyChanged();


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
}
