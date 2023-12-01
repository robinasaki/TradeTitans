package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import interface_adapter.add_portfolio.AddPortfolioController;
import interface_adapter.add_portfolio.AddPortfolioState;
import interface_adapter.add_portfolio.AddPortfolioViewModel;
import interface_adapter.ViewManagerModel;

public class AddPortfolioView extends JPanel { // implements ActionListener, PropertyChangeListener {
    public final String viewName = "add_portfolio";

    private final AddPortfolioViewModel addPortfolioViewModel;
    private final ViewManagerModel viewManagerModel;
    private final AddPortfolioController addPortfolioController;

    private final JTextField portfolioInputField = new JTextField(15);
    private final JComboBox<String> defaultCurrencyField = new JComboBox<>(new String[]{"CAD", "USD", "GBP", "EUR", "CNY", "CHF", "AUD", "other"});

    private final JTextField otherCurrencyField = new JTextField(5);

    private JButton cancel;
    private JButton addPortfolio;

    public AddPortfolioView(AddPortfolioViewModel addPortfolioViewModel, ViewManagerModel viewManagerModel, AddPortfolioController addPortfolioController) {
        this.addPortfolioViewModel = addPortfolioViewModel;
        this.viewManagerModel = viewManagerModel;
        this.addPortfolioController = addPortfolioController;
        initView();
    }

    JPanel panel;
    private void initView() {
         panel = new JPanel(new GridLayout(0,1));

        JLabel title = new JLabel(addPortfolioViewModel.TITLE_LABEL);
        title.setFont(new Font("Georgia", Font.BOLD, 20));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(title);

        panel.add(new LabelTextPanel(new JLabel("Portfolio Name"), portfolioInputField));
        panel.add(new LabelTextPanel(new JLabel("Default Currency"), defaultCurrencyField));

        // TODO: add more
        JLabel currencyInstruction = new JLabel("<html>" + "CAD: the Canadian Dollar <br/>USD: the United States Dollar <br/>GRP: the Great Britain Pound <br/>EUR: the Euro <br/>CNY: the Chinese Yuan <br/> ... " + "<html/>");
        currencyInstruction.setForeground(Color.gray);
        panel.add(currencyInstruction);
        panel.add(new LabelTextPanel(new JLabel("If you select `others`, please specify its abbreviation"), otherCurrencyField));

        cancel = new JButton(addPortfolioViewModel.CANCEL_BUTTON_LABEL);
        addPortfolio = new JButton(addPortfolioViewModel.CONFIRM_BUTTON_LABEL);

        addPortfolio.addActionListener(new ConfirmButtonListener());
        cancel.addActionListener(new CancelButtonListener());

        panel.add(addPortfolio);
        panel.add(cancel);

        add(panel);
    }

    private class ConfirmButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String portfolioName = portfolioInputField.getText();
          
            // to prevent an empty name or name with spaces at end
            if (portfolioName.length() == 0 || portfolioName.charAt(0) == ' ' || portfolioName.charAt(portfolioName.length() - 1) == ' ') {
                JOptionPane.showMessageDialog(panel, "<html> Invalid name, <br/> please try again <html/>", "Failed to create a Portfolio", JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                // TODO: this should really be implementing with the currency text filed having a listener and autofilling if something is selected in the dropdown
                // TODO: this would make it so we only have to read in the text field, and not have to worry about the dropdown
                // we have to add the $ sign to the default currency string
                String defaultCurrency = "$" + defaultCurrencyField.getSelectedItem();

                if (!(defaultCurrency.equals("$other")) && !(otherCurrencyField.getText().isEmpty())) {
                    JOptionPane.showMessageDialog(panel, "<html> If you want to choose other currencies, <br/> please elect `other` in the dropdown menu <html/>", "Failed to select a currency", JOptionPane.INFORMATION_MESSAGE);
                }

                // selected other, wrong currency
                // TODO: we could potentially allow lengths that aren't 3, if we want to support default currencies that aren't actually currencies
                else
                if (defaultCurrency.equals("$other") && otherCurrencyField.getText().length() != 3) {
                    JOptionPane.showMessageDialog(panel, "<html>Bad currency abbreviation input,<br/> please try again<html/>", "Failed to select currency", JOptionPane.INFORMATION_MESSAGE);
                }

                // use the other currency input field
                else if (defaultCurrency.equals("$other") && !(otherCurrencyField.getText().isEmpty())) {
                    defaultCurrency = "$" + otherCurrencyField.getText();
                    addPortfolioController.execute(portfolioName, defaultCurrency);
                }
            }
        }
    }

    private class CancelButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            viewManagerModel.setActiveView("portfolio_selection");
        }
    }
}
