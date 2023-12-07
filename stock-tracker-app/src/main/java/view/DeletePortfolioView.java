package view;

import interface_adapter.delete_portfolio.DeletePortfolioViewModel;
import interface_adapter.delete_portfolio.DeletePortfolioController;
import interface_adapter.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeletePortfolioView extends JPanel {
    public final String viewName = "delete_portfolio";

    private final DeletePortfolioViewModel deletePortfolioViewModel;
    private final ViewManagerModel viewManagerModel;
    private final DeletePortfolioController deletePortfolioController;

    public DeletePortfolioView(DeletePortfolioViewModel deletePortfolioViewModel, ViewManagerModel viewManagerModel, DeletePortfolioController deletePortfolioController) {
        this.deletePortfolioViewModel = deletePortfolioViewModel;
        this.viewManagerModel = viewManagerModel;
        this.deletePortfolioController = deletePortfolioController;
        initView();
    }

    private void initView() {
        JPanel panel = new JPanel(new GridLayout(0, 1));
        JLabel title = new JLabel("Are you sure you want to delete this Portfolio?");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(title);

        JPanel buttons = new JPanel();

        JButton confirm = new JButton(deletePortfolioViewModel.CONFIRM_BUTTON_LABEL);
        confirm.addActionListener(new ConfirmButtonListener());
        buttons.add(confirm);

        JButton cancel = new JButton(deletePortfolioViewModel.CANCEL_BUTTON_LABEL);
        cancel.addActionListener(new CancelButtonListener());
        buttons.add(cancel);

        panel.add(buttons);

        add(panel);
    }

    private class ConfirmButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String portfolioName = deletePortfolioViewModel.getPortfolioName();
            deletePortfolioController.execute(portfolioName);
        }
    }

    private class CancelButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            viewManagerModel.setActiveView("portfolio_selection");
        }
    }
/*
    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, currentState.getPortfolioName() +" deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        JOptionPane.showMessageDialog(null, "Portfolio deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
    }
*/
}
