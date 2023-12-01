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
    private final JComboBox<String> defaultCurrencyField = new JComboBox<>(new String[]{"CAD", "USD", "GBP", "EUR", "CNY", "INR"});

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
                // we have to add the $ sign to the default currency string
                String defaultCurrency = "$" + defaultCurrencyField.getSelectedItem();
                ArrayList<String> possibleCurrencyOptions = new ArrayList<>();
                possibleCurrencyOptions.add("$GBP");
                possibleCurrencyOptions.add("$CAD");
                possibleCurrencyOptions.add("$EUR");
                possibleCurrencyOptions.add("$INR");
                possibleCurrencyOptions.add("$CNY");
                possibleCurrencyOptions.add("$USD");
                if (possibleCurrencyOptions.contains(defaultCurrency)) {
                    addPortfolioController.execute(portfolioName, defaultCurrency);
                }
                else {
                    JOptionPane.showMessageDialog(panel, "<html> Invalid currency, <br/> please try again <html/>", "Failed to create a Portfolio", JOptionPane.INFORMATION_MESSAGE);
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
