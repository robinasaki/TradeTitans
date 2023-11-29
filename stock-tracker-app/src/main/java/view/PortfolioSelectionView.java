package view;

import interface_adapter.portfolio_selection.PortfolioSelectionViewModel;
import interface_adapter.add_portfolio.AddPortfolioController;
import interface_adapter.ViewManagerModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PortfolioSelectionView extends JPanel {
    public final String viewName = "portfolio_selection";

    private PortfolioSelectionViewModel viewModel;
    private ViewManagerModel viewManagerModel;

    public PortfolioSelectionView(PortfolioSelectionViewModel viewModel, ViewManagerModel viewManagerModel) {
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
        initView();
    }

    private void initView() {
        //setTitle("Portfolio Selection");
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        JPanel panel = new JPanel(new GridLayout(0, 1));

        // Add buttons for each portfolio
        List<String> portfolioNames = viewModel.getPortfolioNames();
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
            viewManagerModel.setActiveView("holdings");
        }
    }
}

