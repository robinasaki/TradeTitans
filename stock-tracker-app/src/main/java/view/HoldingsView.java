package view;

import interface_adapter.holdings.HoldingsViewModel;
import interface_adapter.holdings.HoldingsState;
import interface_adapter.ViewManagerModel;
import interface_adapter.trade.TradeViewModel;
import interface_adapter.trade.TradeState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class HoldingsView extends JPanel {
    public final String viewName = "holdings";

    private HoldingsViewModel viewModel;
    private ViewManagerModel viewManagerModel;
    private TradeViewModel tradeViewModel;

    public HoldingsView(HoldingsViewModel viewModel, ViewManagerModel viewManagerModel, TradeViewModel tradeViewModel) {
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
        this.tradeViewModel = tradeViewModel;
        initView();

        viewModel.addPropertyChangeListener(evt -> {
            if ("state".equals(evt.getPropertyName())) {
                initView();
            }
        });
    }

    private void initView() {
        // Clear panel
        removeAll();

        JPanel panel = new JPanel();
        try {
            // Create title
            JLabel title = new JLabel("<html> <font color='gray'>Current Portfolio: </font> <br/>" + "<b>" + viewModel.getState().getPortfolioName() + "</b>" + "<br/>" + "<font color='gray'>Portfolio Currency:</font> <br/>" + "<b>" + viewModel.getState().getDefaultCurrency() + "</b>" + "<br/>" + "<html/>");
            title.setFont(new Font("Georgia", Font.PLAIN, 15));
            panel.add(title);

            // Create table model
            HoldingsTableModel tableModel = new HoldingsTableModel();
            tableModel.addColumn("<html><font color='gray'>Symbol</font><html>");
            tableModel.addColumn("<html><font color='gray'>Price(" + viewModel.getState().getDefaultCurrency() + ")</font><html>");
            tableModel.addColumn("<html><font color='gray'>Shares</font><html>");
            tableModel.addColumn("<html><font color='gray'>Total Value</font><html>");
            tableModel.addColumn("<html><font color='gray'>Change</font><html>");
            tableModel.addColumn("<html><font color='gray'>Change (%)</font><html>");

            // Populate table
            List<String> symbols = viewModel.getState().getSymbols();
            List<Double> quotes = viewModel.getState().getQuotes();
            List<Double> shares = viewModel.getState().getShares();
            List<Double> values = viewModel.getState().getValues();
            List<Double> changes = viewModel.getState().getChanges();
            List<Double> changePercents = viewModel.getState().getChangePercents();
            for (int i = 0; i < symbols.size(); i++) {
                // Format doubles to 3 decimal places with + or - sign in front and % sign at end of change percent
                String changesFormatted = String.format("%+.3f", changes.get(i));
                String changePercentsFormatted = String.format("%+.3f%%", changePercents.get(i));

                // set color of change to red if negative, green if positive, and black if 0
                if (changes.get(i) < 0) {
                    changesFormatted = "<html><font color='red'>" + changesFormatted + "</font></html>";
                    changePercentsFormatted = "<html><font color='red'>" + changePercentsFormatted + "</font></html>";
                } else if (changes.get(i) > 0) {
                    changesFormatted = "<html><font color='green'>" + changesFormatted + "</font></html>";
                    changePercentsFormatted = "<html><font color='green'>" + changePercentsFormatted + "</font></html>";
                }

                Object[] row = {symbols.get(i), quotes.get(i), shares.get(i), values.get(i), changesFormatted, changePercentsFormatted};
                tableModel.addRow(row);
            }

            // Create table
            JTable table = new JTable(tableModel);
            panel.add(table);
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            panel.add(scrollPane);

            // Create back button for going back to portfolio selection
            JButton backButton = new JButton("Back to Portfolio Selection");
            backButton.addActionListener(new BackButtonListener());
            panel.add(backButton);

            // Create button for adding trade
            JButton addTradeButton = new JButton("Add Trade");
            addTradeButton.addActionListener(new AddTradeButtonListener());
            panel.add(addTradeButton);

            // Create button for viewing transaction history
            JButton transactionHistoryButton = new JButton("View Transaction History");
            transactionHistoryButton.addActionListener(new TransactionHistoryButtonListener());
            panel.add(transactionHistoryButton);

            add(panel);
        } catch (Exception exp) {
            JOptionPane.showMessageDialog(panel, exp.getMessage());
        }
    }

    private class BackButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            viewManagerModel.setActiveView("portfolio_selection");
        }
    }

    private class AddTradeButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            tradeViewModel.getState().setPortfolioName(viewModel.getState().getPortfolioName());
            tradeViewModel.getState().setDefaultCurrency(viewModel.getState().getDefaultCurrency());
            viewManagerModel.setActiveView("trade");
        }
    }

    private class TransactionHistoryButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            viewManagerModel.setActiveView("transactions");
        }
    }

}

