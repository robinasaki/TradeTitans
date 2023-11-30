package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.credit.CreditViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreditView extends JPanel {
    public final String viewName = "credit_view";
    private CreditViewModel viewModel;
    private ViewManagerModel viewManagerModel;

    public CreditView(CreditViewModel viewModel, ViewManagerModel viewManagerModel) {
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
        initView();
    }

    private void initView() {
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new backButtonListener());
        panel.add(backButton);

        // TODO: write more
        JLabel portfolioSelectionInstruction = new JLabel("<html>" +
                "Trade Titans is designed for you to explore the stock market with real-life stock data without paying." +
                "</html>");
        portfolioSelectionInstruction.setFont(new Font("Georgia", Font.PLAIN, 15));
        panel.add(portfolioSelectionInstruction);

        JLabel creditInfo = new JLabel("<html>" +
                "Made for csc207 @UofT by Chenxu Robin Mao, Jarod Palubiski, Colin Walton and Abdulrahman Mubarak" +
                "<html/>"
        );
        creditInfo.setFont(new Font("Georgia", Font.ITALIC, 10));
        creditInfo.setForeground(Color.gray);
        panel.add(creditInfo);

        add(panel);

    }

    private class backButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            viewManagerModel.setActiveView("portfolio_selection");
        }
    }
}
