package view;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import entity.Portfolio;
import interface_adapter.trade.TradeController;
import interface_adapter.trade.TradeState;
import interface_adapter.trade.TradeViewModel;
import interface_adapter.ViewManagerModel;
import data_access.APIDataAccessObject;

import java.text.DecimalFormat;

public class TradeView extends JPanel { //implements ActionListener, PropertyChangeListener {
    public final String viewName = "trade";
    private final TradeViewModel tradeViewModel;
    private final ViewManagerModel viewManagerModel;
    private final TradeController tradeController;

    private final JComboBox<String> tradeTypeComboBox;
    private final JTextField amountField;
    private final JTextField currencyField;
    private final JTextField currencyField2;
    private final JTextField sharesField;
    private final JTextField symbolField;
    private final JTextField priceField;

    private final JTextField totalPriceField;
    private String selected = "";
    private JPanel panel;

    public TradeView(TradeViewModel tradeViewModel, ViewManagerModel viewManagerModel, TradeController tradeController) {
        this.viewManagerModel = viewManagerModel;
        this.tradeController = tradeController;
        this.tradeViewModel = tradeViewModel;
        this.tradeTypeComboBox = new JComboBox<>(new String[]{"Buy", "Sell", "Deposit", "Withdraw", "Currency Exchange"});
        this.amountField = new JTextField(10);
        this.currencyField = new JTextField(5);
        this.currencyField2 = new JTextField(5);
        this.sharesField = new JTextField(10);
        this.symbolField = new JTextField(5);
        this.priceField = new NonEditableTextField(10);
        this.totalPriceField = new NonEditableTextField(10);
        initView();
    }

    private void initView() {
        //tradeViewModel.addPropertyChangeListener(this);

        // this is to prevent the user from typing Strings in the numeric fields
        // int specific fields that require a numeric values.
        ((AbstractDocument) amountField.getDocument()).setDocumentFilter(new NumericFilter());
        // Set up DocumentFilter for sharesField
        ((AbstractDocument) sharesField.getDocument()).setDocumentFilter(new NumericFilter());

        // Set up CurrencyFilter
        ((AbstractDocument) symbolField.getDocument()).setDocumentFilter(new CurrencyInputFilter());
        ((AbstractDocument) currencyField.getDocument()).setDocumentFilter(new CurrencyInputFilter());

        panel = new JPanel(new GridLayout(0, 1));
        JLabel title = new JLabel(tradeViewModel.TITLE_LABEL);
        title.setFont(new Font("Georgia", Font.BOLD, 15));
        panel.add(title);
        panel.add(tradeTypeComboBox);
        panel.add(new LabelTextPanel(new JLabel(tradeViewModel.TRADE_TYPE_LABEL), tradeTypeComboBox));
        tradeTypeComboBox.addItemListener(new TradeTypeComboBoxListener());
        panel.add(new LabelTextPanel(new JLabel(tradeViewModel.AMOUNT_LABEL), amountField));
        panel.add(new LabelTextPanel(new JLabel(tradeViewModel.CURRENCY_LABEL), currencyField));
        panel.add(new LabelTextPanel(new JLabel(tradeViewModel.TO_CURRENCY_LABEL), currencyField2));
        panel.add(new LabelTextPanel(new JLabel(tradeViewModel.SHARES_LABEL), sharesField));
        panel.add(new LabelTextPanel(new JLabel(tradeViewModel.SYMBOL_LABEL), symbolField));
        panel.add(new LabelTextPanel(new JLabel(tradeViewModel.PRICE_LABEL), priceField));
        panel.add(new LabelTextPanel(new JLabel("TotalPrice"), totalPriceField));

        symbolField.addKeyListener(new SymbolFieldKeyListener());
        sharesField.addKeyListener(new SharesFieldKeyListener());

        JPanel buttons = new JPanel();

        JButton cancelButton = new JButton(tradeViewModel.CANCEL_BUTTON_LABEL);
        cancelButton.addActionListener(new CancelButtonListener());
        buttons.add(cancelButton);

        JButton confirmButton = new JButton(tradeViewModel.CONFIRM_BUTTON_LABEL);
        confirmButton.addActionListener(new ConfirmButtonListener());
        buttons.add(confirmButton);

        showRelevantFields();

        panel.add(buttons);

        JButton instructionButton = new JButton("<html> <i>What are these trade types?</i> <html/>");
        instructionButton.addActionListener(new InstructionButtonListener());
        panel.add(instructionButton);

        add(panel);

    }

    // Those classes for the Filter that prevents the user from entering Strings
    // in the Numeric fields

    private static class NumericFilter extends DocumentFilter {
        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                throws BadLocationException {
            if (string.matches("\\d*")) {
                super.insertString(fb, offset, string, attr);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                throws BadLocationException {
            if (text.matches("[\\d, .]*")) {
                super.replace(fb, offset, length, text, attrs);
            }
        }
    }

    private static class CurrencyInputFilter extends DocumentFilter {
        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            if (string.matches("[A-Z]*")) {
                super.insertString(fb, offset, string, attr);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                throws BadLocationException {
            if (text.matches("[A-Z]*")) {
                super.replace(fb, offset, length, text, attrs);
            }
        }
    }

    private class ConfirmButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // gets trade type
            String tradeType = (String) tradeTypeComboBox.getSelectedItem();

            // TODO: trading fee is set to 0.0 right now

            String portfolio = tradeViewModel.getState().getPortfolioName();
            String defaultCurrency = tradeViewModel.getState().getDefaultCurrency();

            assert tradeType != null;
            switch (tradeType) {
                case "Deposit" -> {
                    double amount = Double.parseDouble(amountField.getText());
                    String currency = currencyField.getText();
                    try {
                        tradeController.execute(portfolio, "$" + currency, "", amount, 0.0, 0.0);
                    } catch (RuntimeException exp) {
                        if (exp.getClass().equals(NullPointerException.class)) {
                            JOptionPane.showMessageDialog(panel, "API access error. Please check again.");
                        }
                        JOptionPane.showMessageDialog(panel, exp.getMessage());
                    }
                }
                case "Withdraw" -> {
                    double amount = Double.parseDouble(amountField.getText());
                    String currency = currencyField.getText();
                    try {
                        tradeController.execute(portfolio, "", "$" + currency, 0.0, amount, 0.0);
                    } catch (RuntimeException exp) {
                        if (exp.getClass().equals(NullPointerException.class)) {
                            JOptionPane.showMessageDialog(panel, "API access error. Please check again.");
                        }
                         JOptionPane.showMessageDialog(panel, exp.getMessage());
                    }
                }
                case "Buy" -> {
                    double shares = Double.parseDouble(sharesField.getText());
                    String symbol = symbolField.getText();
                    try {
                        double price = Double.parseDouble(priceField.getText());
                        tradeController.execute(portfolio, symbol, defaultCurrency, shares, shares * price, 0.0);
                    } catch (RuntimeException exp) {
                        if (exp.getClass().equals(NullPointerException.class)) {
                            JOptionPane.showMessageDialog(panel, "API access error. Please check again.");
                        }
                        JOptionPane.showMessageDialog(panel, exp.getMessage());
                    }
                }
                case "Sell" -> {
                    double shares = Double.parseDouble(sharesField.getText());
                    String symbol = symbolField.getText();
                    try {
                        double price = Double.parseDouble(priceField.getText());
                        tradeController.execute(portfolio, defaultCurrency, symbol, shares * price, shares, 0.0);
                    } catch (RuntimeException exp) {
                        if (exp.getClass().equals(NullPointerException.class)) {
                            JOptionPane.showMessageDialog(panel, "API access error. Please check again.");
                        }
                        JOptionPane.showMessageDialog(panel, exp.getMessage());
                    }
                }
                // TODO: The logic behind this is incorrect. Still need proper implementation.
                case "Currency Exchange" -> {
                    double amount = Double.parseDouble(amountField.getText());
                    String currency = currencyField.getText();
                    String to_currency = currencyField2.getText();

                    tradeController.execute(portfolio, currency, to_currency, amount, currency , 0.0);
                }
            }
        }
    }

    private class CancelButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            viewManagerModel.setActiveView("holdings");
        }

    }

    private class TradeTypeComboBoxListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                showRelevantFields();
            }
        }
    }

    private class InstructionButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(panel, "<html>" +
                    "<b>Deposit</b>: deposit selected currency into the portfolio " +
                    "<br/> <br/> <b>Withdraw</b>: withdraw selected currency into the portfolio " +
                    "<br/> <br/> <b>Buy</b>: purchase the inputted stock with the default currency " +
                    "<br/> <br/> <b>Sell</b>: sell the inputted stock and convert to the default currency " +
                    "<br/> <br/> <b>Exchange</b>: exchange currency <html/>");
        }
    }

    /* private class TradeTypeComboBoxListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            showRelevantFields();
        }
    } */

    private class SymbolFieldKeyListener implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {
            // Do nothing
        }

        @Override
        public void keyPressed(KeyEvent e) {
            // Do nothing
        }

        @Override
        public void keyReleased(KeyEvent e) {
            String symbol = symbolField.getText();
            if (!symbol.isEmpty()) {
                symbolField.setText(symbol.toUpperCase());
                updatePrice();
                updateTotalPrice();
            }
        }
    }

    private class SharesFieldKeyListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
            // Do nothing
        }

        @Override
        public void keyPressed(KeyEvent e) {
            // Do nothing
        }

        @Override
        public void keyReleased(KeyEvent e) {
            updateTotalPrice();
        }
    }


    private void updatePrice() {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String defaultCurrency = tradeViewModel.getState().getDefaultCurrency();
        APIDataAccessObject apiDataAccessObject = new APIDataAccessObject();
        String symbol = symbolField.getText();
        double price = apiDataAccessObject.getHistoricalQuotes(symbol, defaultCurrency).lastEntry().getValue();
        priceField.setText(decimalFormat.format(price));
    }

    private void updateTotalPrice() {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String defaultCurrency = tradeViewModel.getState().getDefaultCurrency();
        if (priceField.getText().isEmpty() || symbolField.getText().isEmpty() || sharesField.getText().isEmpty()) {
            totalPriceField.setText("0.00");
        } else {
            double price = Double.parseDouble(priceField.getText());
            totalPriceField.setText(decimalFormat.format(Double.parseDouble(sharesField.getText()) * price));
        }
    }

    private void showRelevantFields() {
        String tradeType = (String) tradeTypeComboBox.getSelectedItem();

        // Hide all fields
        amountField.setVisible(false);
        currencyField.setVisible(false);
        sharesField.setVisible(false);
        symbolField.setVisible(false);
        priceField.setVisible(false);

        switch (tradeType) {
            case "Deposit":
            case "Withdraw":
                amountField.setVisible(true);
                currencyField.setVisible(true);
                break;
            case "Buy":
            case "Sell":
                sharesField.setVisible(true);
                symbolField.setVisible(true);
                priceField.setVisible(true);
                break;
            case "Currency Exchange":
                amountField.setVisible(true);
                currencyField.setVisible(true);
                currencyField2.setVisible(true);

                break;
            default:
                // TODO: implement default case here.
                break;
        }
        panel.revalidate();
        panel.repaint();
    }
/*
@Override
public void actionPerformed(ActionEvent e) {

} */




/* @Override
public void propertyChange(PropertyChangeEvent evt) {
    TradeState state = (TradeState) evt.getNewValue();
    } */
}
