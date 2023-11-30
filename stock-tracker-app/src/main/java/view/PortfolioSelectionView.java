package view;

import interface_adapter.portfolio_selection.PortfolioSelectionViewModel;
import interface_adapter.add_portfolio.AddPortfolioController;
import interface_adapter.ViewManagerModel;
import interface_adapter.holdings.UpdatePricesController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;



public class PortfolioSelectionView extends JPanel {
    public final String viewName = "portfolio_selection";

    private PortfolioSelectionViewModel viewModel;
    private ViewManagerModel viewManagerModel;
    private UpdatePricesController updatePricesController;

    public PortfolioSelectionView(PortfolioSelectionViewModel viewModel, ViewManagerModel viewManagerModel, UpdatePricesController updatePricesController) {
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
        this.updatePricesController = updatePricesController;
        initView();
    }

    private void initView() {
        JPanel panel = new JPanel(new GridLayout(0, 1));
        ImageIcon icon = new ImageIcon("logo.jpg");
        panel.add(new JLabel(icon));

        // set the view border
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel portfolioSelectionInstruction = new JLabel("Select one portfolio below to begin with.");
        portfolioSelectionInstruction.setFont(new Font("Georgia", Font.PLAIN, 15));
        panel.add(portfolioSelectionInstruction);

        // Add buttons for each portfolio
        List<String> portfolioNames = viewModel.getPortfolioNames();
        for (String portfolioName : portfolioNames) {
            JButton button = new JButton(portfolioName);
            button.addActionListener(new PortfolioButtonListener(portfolioName));
            panel.add(button);
        }

        // Add button to add a new portfolio
        JButton addPortfolioButton = new JButton("Add Portfolio");
        addPortfolioButton.addActionListener(new AddPortfolioButtonListener());
        panel.add(addPortfolioButton);

        JLabel description1 = new JLabel("A CSC207 project @UofT by Chenxu Robin Mao, Jarod Palubiski, Colin Walton, Abdulrahman Mubarak");
        description1.setFont(new Font("Georgia", Font.PLAIN, 12));
        description1.setForeground(Color.gray);
        panel.add(description1);

        add(panel);
    }

    private class PortfolioButtonListener implements ActionListener {

        private String portfolioName;

        public PortfolioButtonListener(String portfolioName) {
            this.portfolioName = portfolioName;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            updatePricesController.execute(portfolioName);
        }
    }

    private class AddPortfolioButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            viewManagerModel.setActiveView("add_portfolio");
        }
    }

}

