package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.credit.CreditViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreditView extends JPanel {
    public final String viewName = "credit";
    private CreditViewModel viewModel;
    private ViewManagerModel viewManagerModel;

    public CreditView(CreditViewModel viewModel, ViewManagerModel viewManagerModel) {
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
        initView();
    }

    private void initView() {
        JPanel panel = new JPanel(new GridLayout(0, 1));

        JButton backButton = new JButton("Back");
        backButton.setSize(5, 2);
        backButton.addActionListener(new backButtonListener());
        panel.add(backButton);

        JLabel portfolioSelectionInstruction = new JLabel("<html>" +
                "Trade Titans is a stock exchange software designed to explore the stock market <br/> with real-life stock data without paying. <br/>" +
                "The program contains some necessary methods for exchange simulation such as deposit, withdraw, buy, sell, etc. <br/>" +
                "in different currencies with different portfolios.<br/>" +
                "The app also tracks the value of currencies and cryptocurrencies." +
                "</html>");
        portfolioSelectionInstruction.setFont(new Font("Georgia", Font.PLAIN, 15));
        panel.add(portfolioSelectionInstruction);

        JLabel creditInfo = new JLabel("<html>" +
                "Made for csc207 @UofT by Chenxu Robin Mao, Jarod Palubiski, <br/> Colin Walton and Abdulrahman Mubarak" +
                "<html/>"
        );
        creditInfo.setFont(new Font("Georgia", Font.ITALIC, 10));
        creditInfo.setForeground(Color.gray);
        panel.add(creditInfo);

        JButton groupPicButton = new JButton("Group Photo");
        groupPicButton.addActionListener(new groupPicButtonListener());
        panel.add(groupPicButton);

        add(panel);

    }

    private class backButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            viewManagerModel.setActiveView("portfolio_selection");
        }
    }

    private class groupPicButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ImageIcon groupP = new ImageIcon("src/images/groupPhoto.jpg");
            JOptionPane.showMessageDialog(null, "", "Meet the Developers", JOptionPane.PLAIN_MESSAGE, groupP);
        }
    }
}
