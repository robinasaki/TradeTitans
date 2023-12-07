package view;

import interface_adapter.portfolio_selection.PortfolioSelectionViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.holdings.UpdatePricesController;
import interface_adapter.delete_portfolio.DeletePortfolioViewModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;


public class PortfolioSelectionView extends JPanel {
    public final String viewName = "portfolio_selection";

    private PortfolioSelectionViewModel viewModel;
    private ViewManagerModel viewManagerModel;
    private UpdatePricesController updatePricesController;
    private DeletePortfolioViewModel deletePortfolioViewModel;

    public PortfolioSelectionView(PortfolioSelectionViewModel viewModel, ViewManagerModel viewManagerModel, UpdatePricesController updatePricesController, DeletePortfolioViewModel deletePortfolioViewModel) {
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
        this.updatePricesController = updatePricesController;
        this.deletePortfolioViewModel = deletePortfolioViewModel;
        initView();

        viewModel.addPropertyChangeListener(evt -> {
            if ("portfolioNames".equals(evt.getPropertyName())) {
                initView();
            }
        });
    }

    private void initView() {
        // Clear the panel
        removeAll();

        JPanel panel = new JPanel(new GridLayout(0, 1));
        try {
            BufferedImage myPicture = ImageIO.read(new File("src/images/logo.jpg"));
            JLabel picLabel = new JLabel(new ImageIcon(myPicture));
            panel.add(picLabel);
        } catch (Exception e) {
            System.out.println("Logo compiling error.");
            e.printStackTrace();
        }

        try {
            // set the view border
            panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JLabel portfolioSelectionInstruction = new JLabel("Select one portfolio below to begin with.");
            portfolioSelectionInstruction.setFont(new Font("Georgia", Font.PLAIN, 15));
            panel.add(portfolioSelectionInstruction);

            // Add buttons for each portfolio
            List<String> portfolioNames = viewModel.getPortfolioNames();
            for (String portfolioName : portfolioNames) {
                // Create a new panel for each portfolio
                JPanel portfolioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

                // Portfolio button
                JButton portfolioButton = new JButton(portfolioName);
                portfolioButton.setFont(new Font("SansSerif", Font.PLAIN, 17));
                portfolioButton.setPreferredSize(new Dimension(300, 50));
                portfolioButton.addActionListener(new PortfolioButtonListener(portfolioName));
                portfolioPanel.add(portfolioButton);

                // Delete button
                JButton deleteButton = new JButton("\uD83D\uDDD1");
                deleteButton.setFont(new Font("SansSerif", Font.PLAIN, 20));
                deleteButton.setPreferredSize(new Dimension(50, 50));
                deleteButton.addActionListener(new DeletePortfolioButtonListener(portfolioName));
                portfolioPanel.add(deleteButton);

                // Portfolio panel to the main panel
                panel.add(portfolioPanel);
            }

            // Button to add a new portfolio
            JButton addPortfolioButton = new JButton("Add Portfolio");
            addPortfolioButton.addActionListener(new AddPortfolioButtonListener());
            panel.add(addPortfolioButton);

            JButton creditButton = new JButton("About the program");
            creditButton.addActionListener(new creditButtonListener());
            panel.add(creditButton);

            add(panel);
        } catch (Exception exp) {
            JOptionPane.showMessageDialog(panel, exp.getMessage());
        }
    }

    private class PortfolioButtonListener implements ActionListener {

        private String portfolioName;

        public PortfolioButtonListener(String portfolioName) {
            this.portfolioName = portfolioName;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                updatePricesController.execute(portfolioName);
            } catch (Exception exception) {
                if (exception.getMessage().equals("\"Cannot invoke \\\"com.fasterxml.jackson.databind.JsonNode.fields()\\\" because \\\"timeSeries\\\" is null\"")) {
                    throw new RuntimeException("API Key error. Please check again.");
                }
            }
        }
    }

    private class DeletePortfolioButtonListener implements ActionListener {
        private String portfolioName;

        public DeletePortfolioButtonListener(String portfolioName) {
            this.portfolioName = portfolioName;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            deletePortfolioViewModel.setPortfolioName(portfolioName);
            viewManagerModel.setActiveView("delete_portfolio");
        }
    }

    private class AddPortfolioButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            viewManagerModel.setActiveView("add_portfolio");
        }
    }

    private class creditButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            viewManagerModel.setActiveView("credit");
        }
    }
}

