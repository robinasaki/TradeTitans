package view.portfolio_selection;

import interface_adapter.portfolio_selection.PortfolioSelectionViewModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PortfolioSelectionView extends JFrame {

    private PortfolioSelectionViewModel viewModel;

    public PortfolioSelectionView(PortfolioSelectionViewModel viewModel) {
        this.viewModel = viewModel;
        initView();
    }

    private void initView() {
        setTitle("Portfolio Selection");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        JPanel panel = new JPanel(new GridLayout(0, 1));

        // Add buttons for each portfolio
        List<String> portfolioNames = viewModel.getState().getPortfolioNames();
        for (String portfolioName : portfolioNames) {
            JButton button = new JButton(portfolioName);
            button.addActionListener(new PortfolioButtonListener(portfolioName));
            panel.add(button);
        }

        add(panel);
    }

    private class PortfolioButtonListener implements ActionListener {

        private String portfolioName;

        public PortfolioButtonListener(String portfolioName) {
            this.portfolioName = portfolioName;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            viewModel.switchToHoldingsView(portfolioName);
        }

    }

}

