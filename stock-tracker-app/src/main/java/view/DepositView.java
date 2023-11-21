package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.event.ActionListener;

import interface_adapter.DepositController;
import interface_adapter.DepositState;
import interface_adapter.DepositViewModel;

import static javax.swing.BoxLayout.Y_AXIS;

public class DepositView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "deposit";

    private final DepositViewModel depositViewModel;

    private final JTextField amountInputField = new JTextField(15);

    private final DepositController depositController;

    private final JButton cancel;

    private final JButton deposit;


    public DepositView(DepositController depositController, DepositViewModel depositViewModel) {
        this.depositController = depositController;
        this.depositViewModel = depositViewModel;
        depositViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(depositViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel amountInfo = new LabelTextPanel(new JLabel(depositViewModel.AMOUNT_LABEL), amountInputField);
        JPanel buttons = new JPanel();
        cancel = new JButton(depositViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);
        deposit = new JButton(depositViewModel.CONFIRM_BUTTON_LABEL);
        buttons.add(deposit);

        deposit.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource() == "deposit") {
                            DepositState currentState = depositViewModel.getState();
                            depositController.execute(currentState.getAmount());
                        }
                    }
                }
        );
        cancel.addActionListener(this);

        amountInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        DepositState currentState = depositViewModel.getState();
                        currentState.setAmount(Double.parseDouble(amountInputField.getText() + e.getKeyChar()));
                        depositViewModel.setState(currentState);
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
        // TODO: fix and further implement
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
