package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.credit.CreditViewModel;

import javax.swing.*;
import java.awt.*;

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

        JLabel portfolioSelectionInstruction = new JLabel("Testing");
        portfolioSelectionInstruction.setFont(new Font("Georgia", Font.PLAIN, 15));
        panel.add(portfolioSelectionInstruction);

        add(panel);
    }
}
