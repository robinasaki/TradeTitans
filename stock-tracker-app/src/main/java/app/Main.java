package app;

import view.*;
import interface_adapter.ViewManagerModel;
import interface_adapter.credit.CreditViewModel;
import interface_adapter.portfolio_selection.PortfolioSelectionViewModel;
import interface_adapter.add_portfolio.AddPortfolioViewModel;
import interface_adapter.delete_portfolio.DeletePortfolioViewModel;
import interface_adapter.holdings.HoldingsViewModel;
import interface_adapter.holdings.HoldingsState;
import interface_adapter.trade.TradeViewModel;
import interface_adapter.view_transactions.TransactionsViewModel;
import interface_adapter.view_transactions.TransactionsState;
import interface_adapter.view_price_history.PriceHistoryViewModel;
import interface_adapter.view_price_history.PriceHistoryState;
import data_access.FileDataAccessObject;
import entity.Portfolio;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        JFrame application = new JFrame("Trade Titans");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        application.setPreferredSize(new Dimension(1200, 800));

        CardLayout cardLayout = new CardLayout();
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        JScrollPane scrollPane = new JScrollPane(views);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        application.add(scrollPane);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        PortfolioSelectionViewModel portfolioSelectionViewModel = new PortfolioSelectionViewModel();
        CreditViewModel creditViewModel = new CreditViewModel();

        HoldingsState emptyHoldingsState = new HoldingsState();
        HoldingsViewModel holdingsViewModel = new HoldingsViewModel();
        holdingsViewModel.setState(emptyHoldingsState);

        AddPortfolioViewModel addPortfolioViewModel = new AddPortfolioViewModel();

        DeletePortfolioViewModel deletePortfolioViewModel = new DeletePortfolioViewModel();

        TradeViewModel tradeViewModel = new TradeViewModel();

        TransactionsState emptyTransactionsState = new TransactionsState();
        TransactionsViewModel transactionsViewModel = new TransactionsViewModel();
        transactionsViewModel.setState(emptyTransactionsState);

        PriceHistoryState emptyPriceHistoryState = new PriceHistoryState();
        PriceHistoryViewModel priceHistoryViewModel = new PriceHistoryViewModel();
        priceHistoryViewModel.setState(emptyPriceHistoryState);

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

        PortfolioSelectionView portfolioSelectionView = ViewFactory.createPortfolioSelectionView(portfolioSelectionViewModel, holdingsViewModel, deletePortfolioViewModel, viewManagerModel);
        views.add(portfolioSelectionView, portfolioSelectionView.viewName);

        HoldingsView holdingsView = ViewFactory.createHoldingsView(holdingsViewModel, viewManagerModel, tradeViewModel, transactionsViewModel, priceHistoryViewModel);
        views.add(holdingsView, holdingsView.viewName);

        AddPortfolioView addPortfolioView = ViewFactory.createAddPortfolioView(addPortfolioViewModel, viewManagerModel, portfolioSelectionViewModel);
        views.add(addPortfolioView, addPortfolioView.viewName);

        DeletePortfolioView deletePortfolioView = ViewFactory.createDeletePortfolioView(deletePortfolioViewModel, viewManagerModel, portfolioSelectionViewModel);
        views.add(deletePortfolioView, deletePortfolioView.viewName);

        TradeView tradeView = ViewFactory.createTradeView(tradeViewModel, viewManagerModel, holdingsViewModel);
        views.add(tradeView, tradeView.viewName);

        CreditView creditView = ViewFactory.createCreditView(creditViewModel, viewManagerModel);
        views.add(creditView, creditView.viewName);

        TransactionsView transactionsView = ViewFactory.createTransactionsView(transactionsViewModel, viewManagerModel);
        views.add(transactionsView, transactionsView.viewName);

        PriceHistoryView priceHistoryView = ViewFactory.createPriceHistoryView(priceHistoryViewModel, viewManagerModel);
        views.add(priceHistoryView, priceHistoryView.viewName);

        application.pack();
        application.setVisible(true);
    }
}
