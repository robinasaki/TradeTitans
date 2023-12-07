package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.view_transactions.TransactionsViewModel;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

public class TransactionsView extends JPanel {
    public final String viewName = "transactions";

    private TransactionsViewModel viewModel;
    private ViewManagerModel viewManagerModel;

    private final JTable transactionHistoryTable;

    private final JScrollPane scrollPane;
 
    public TransactionsView(TransactionsViewModel viewModel, ViewManagerModel viewManagerModel) {
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;

        Font font = new Font("Arial", Font.PLAIN, 20);
        String[] columnNames = {"Date", "Transaction Type", "Symbol", "Shares"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        this.transactionHistoryTable = new JTable(tableModel);
        transactionHistoryTable.setFont(font);
        transactionHistoryTable.setRowHeight(30);
        scrollPane = new JScrollPane(transactionHistoryTable);
        initView();

        viewModel.addPropertyChangeListener(evt -> {
            if (evt.getPropertyName().equals("state")) {
                updateTransactionHistory();
            }
        });
    }

    private void initView() {
        setLayout(new BorderLayout());

        updateTransactionHistory();

        add(scrollPane, BorderLayout.CENTER);

        JButton backButton = new JButton("Back to Holdings");
        backButton.addActionListener(new BackButtonListener());
        add(backButton, BorderLayout.SOUTH);
    }

    private void updateTransactionHistory() {
        // get data from state
        String portfolioName = viewModel.getState().getPortfolioName();
        List<String> tradeTypes = viewModel.getState().getTradeTypes();
        List<String> symbols = viewModel.getState().getSymbols();
        List<String> amounts = viewModel.getState().getAmounts();
        List<Date> dates = viewModel.getState().getDates();

        // clear existing data
        DefaultTableModel tableModel = (DefaultTableModel) transactionHistoryTable.getModel();
        tableModel.setRowCount(0);

        // add new data
        for (int i = 0; i < tradeTypes.size(); i++) {
            SimpleDateFormat formatter = new SimpleDateFormat("MMMM d, yyyy");
            String dateString = formatter.format(dates.get(i));
            String[] rowData = {dateString, tradeTypes.get(i), symbols.get(i), amounts.get(i)};
            tableModel.addRow(rowData);
        }
    }

    private class BackButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            viewManagerModel.setActiveView("holdings");
        }
    }
}
