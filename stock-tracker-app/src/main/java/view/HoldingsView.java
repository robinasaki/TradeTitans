package view;

import interface_adapter.holdings.HoldingsViewModel;
import interface_adapter.holdings.HoldingsState;
import interface_adapter.ViewManagerModel;
import interface_adapter.trade.TradeViewModel;
import interface_adapter.trade.TradeState;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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

        // Create title
        JLabel title = new JLabel(viewModel.getState().getPortfolioName());
        panel.add(title);

        // Create table model
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Symbol");
        tableModel.addColumn("Price");
        tableModel.addColumn("Shares");
        tableModel.addColumn("Value");
        tableModel.addColumn("Change");
        tableModel.addColumn("Change %");

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
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scrollPane);

        // display default currency
        JLabel currency = new JLabel("Currency: " + viewModel.getState().getDefaultCurrency());
        panel.add(currency);

        // Create back button for going back to portfolio selection
        JButton backButton = new JButton("Back to Portfolio Selection");
        backButton.addActionListener(new BackButtonListener());
        panel.add(backButton);

        // Create button for adding trade
        JButton addTradeButton = new JButton("Add Trade");
        addTradeButton.addActionListener(new AddTradeButtonListener());
        panel.add(addTradeButton);

        add(panel);
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
    
}

