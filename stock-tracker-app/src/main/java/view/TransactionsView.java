package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.view_transactions.TransactionsViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransactionsView extends JPanel {
    public final String viewName = "transactions";

    private TransactionsViewModel viewModel;
    private ViewManagerModel viewManagerModel;

    private final JTextArea transactionHistoryTextArea;

    private final JScrollPane scrollPane;
 
    public TransactionsView(TransactionsViewModel viewModel, ViewManagerModel viewManagerModel) {
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;

        this.transactionHistoryTextArea = new JTextArea(20, 50);
        scrollPane = new JScrollPane(transactionHistoryTextArea);
        initView();
    }

    private void initView() {
        setLayout(new BorderLayout());

        transactionHistoryTextArea.setEditable(false);
        updateTransactionHistory();

        add(scrollPane, BorderLayout.CENTER);

        JButton backButton = new JButton("Back to Holdings");
        backButton.addActionListener(new BackButtonListener());
        add(backButton, BorderLayout.SOUTH);
    }

    private void updateTransactionHistory() {
        // TODO: more to be done here
        //String transactionHistory = viewModel.getTransactionHistory();
        //transactionHistoryTextArea.setText(viewModel.getTransactionHistory());
    }

    private class BackButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            viewManagerModel.setActiveView("holdings");
        }
    }
}
