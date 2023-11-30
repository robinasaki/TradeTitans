package view;

import interface_adapter.portfolio_selection.PortfolioSelectionViewModel;
import interface_adapter.add_portfolio.AddPortfolioController;
import interface_adapter.ViewManagerModel;
import interface_adapter.holdings.UpdatePricesController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.util.Objects;


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
        try {
            BufferedImage myPicture = ImageIO.read(new File("src/images/logo.jpg"));
            JLabel picLabel = new JLabel(new ImageIcon(myPicture));
            panel.add(picLabel);
        } catch (Exception e) {
            System.out.println("Logo compiling error, please open the project as `Trade Titans`.");
            e.printStackTrace();
        }

        // Add vertical panel
        JScrollPane scrollPane = new JScrollPane(panel);
        JScrollBar scrollBar = new JScrollBar();

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

        JButton creditButton = new JButton("About the program");
        creditButton.addActionListener(new creditButtonListener());
        panel.add(creditButton);

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

