package view;

import interface_adapter.holdings.HoldingsViewModel;
import interface_adapter.holdings.HoldingsState;
import interface_adapter.ViewManagerModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class HoldingsView extends JPanel {
    public final String viewName = "holdings";

    private final HoldingsViewModel viewModel;
    private ViewManagerModel viewManagerModel;

    public HoldingsView(HoldingsViewModel viewModel, ViewManagerModel viewManagerModel) {
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
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
        tableModel.addColumn("Quantity");
        tableModel.addColumn("Value");

        // Populate table
        List<String> symbols = viewModel.getState().getSymbols();
        List<Double> quotes = viewModel.getState().getQuotes();
        List<Double> shares = viewModel.getState().getShares();
        List<Double> values = viewModel.getState().getValues();
        for (int i = 0; i < symbols.size(); i++) {
            Object[] row = {symbols.get(i), quotes.get(i), shares.get(i), values.get(i)};
            tableModel.addRow(row);
        }

        // Create table
        JTable table = new JTable(tableModel);
        panel.add(table);

        JScrollPane scrollPane = new JScrollPane(table);

        panel.add(scrollPane);

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
            // TODO: we have to pass the portfolio name and default currency
            viewManagerModel.setActiveView("trade");
        }
    }
    
}

