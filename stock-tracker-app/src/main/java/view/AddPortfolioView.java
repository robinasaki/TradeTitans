package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private final JTextField defaultCurrencyField = new JTextField(5);

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
            if (!portfolioName.matches("\\S+")){
                JOptionPane.showMessageDialog(panel, "Invalid name", "Fail to create a Portfolio",1 );
            }
            else {
                // we have to add the $ sign to the default currency string
                String defaultCurrency = "$" + defaultCurrencyField.getText();
                addPortfolioController.execute(portfolioName, defaultCurrency);
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
