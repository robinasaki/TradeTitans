package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.event.ActionListener;

import interface_adapter.add_portfolio.AddPortfolioController;
import interface_adapter.add_portfolio.AddPortfolioState;
import interface_adapter.add_portfolio.AddPortfolioViewModel;

public class AddPortfolioView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "add portfolio";
    private final AddPortfolioViewModel addPortfolioViewModel;
    private final AddPortfolioController addPortfolioController;
    private final JTextField portfolioInputField = new JTextField(15);
    private final JButton cancel;
    private final JButton addPortfolio;

    public AddPortfolioView(AddPortfolioController addPortfolioController, AddPortfolioViewModel addPortfolioViewModel){
        this.addPortfolioController = addPortfolioController;
        this.addPortfolioViewModel = addPortfolioViewModel;
        addPortfolioViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(addPortfolioViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel amountInfo = new LabelTextPanel(new JLabel(addPortfolioViewModel.ADD_PORTFOLIO_LABEL),
                portfolioInputField);
        JPanel buttons = new JPanel();
        cancel = new JButton(addPortfolioViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);
        addPortfolio = new JButton(addPortfolioViewModel.CONFIRM_BUTTON_LABEL);
        addPortfolio.add(addPortfolio);

        addPortfolio.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals("add portfolio")) {
                            AddPortfolioState currentState = addPortfolioViewModel.getState();

                            addPortfolioController.execute(currentState.getPortfolioName(),
                                    currentState.getDefaultCurrency());
                        }
                    }
                }
        );
        cancel.addActionListener(this);

        portfolioInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        AddPortfolioState currentState = addPortfolioViewModel.getState();
                        String text = portfolioInputField.getText() + e.getKeyChar();
                        currentState.setPortfolioName(text);
                        addPortfolioViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(amountInfo);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {}

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        AddPortfolioState state = (AddPortfolioState) evt.getNewValue();
        if (state.getPortfolioCreationError() != null) {
            JOptionPane.showMessageDialog(this, state.getPortfolioCreationError());
        }
        if (state.getClearMessage() != null) {
            JOptionPane.showMessageDialog(this, state.getClearMessage());
        }
    }
}
