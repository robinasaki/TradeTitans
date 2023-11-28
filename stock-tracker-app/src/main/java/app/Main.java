package app;

import interface_adapter.deposit.DepositViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.portfolio_selection.PortfolioSelectionViewModel;
import view.PortfolioSelectionView;
import interface_adapter.holdings.HoldingsViewModel;
import view.HoldingsView;
import interface_adapter.trade.TradeViewModel;
import view.TradeView;
import view.ViewManager;
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
        //HoldingsViewModel holdingsViewModel = new HoldingsViewModel();
        //TradeViewModel tradeViewModel = new TradeViewModel();

        FileDataAccessObject fileDataAccessObject = new FileDataAccessObject();

        // Loading portfolios from file
        List<Portfolio> portfolios = fileDataAccessObject.loadPortfolios();

        // Adding portfolios to portfolio selection state
        List<String> portfolioNames = new java.util.ArrayList<>();
        for (Portfolio portfolio : portfolios) {
            portfolioNames.add(portfolio.getName());
        }
        portfolioSelectionViewModel.getState().setPortfolioNames(portfolioNames);

        PortfolioSelectionView portfolioSelectionView = new PortfolioSelectionView(portfolioSelectionViewModel);
        views.add(portfolioSelectionView, portfolioSelectionView.viewName);

        //HoldingsView holdingsView = new HoldingsView(holdingsViewModel);
        //views.add(holdingsView, "holdingsView");
        //TradeView tradeView = new TradeView(tradeViewModel);
        //views.add(tradeView, "tradeView");

        //viewManagerModel.setActiveView(portfolioSelectionView.viewName);
        //viewManagerModel.firePropertyChanged();


        application.pack();
        application.setVisible(true);
    }
}
