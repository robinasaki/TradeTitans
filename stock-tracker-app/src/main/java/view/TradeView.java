package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.event.ActionListener;

import interface_adapter.TradeController;
import interface_adapter.TradeState;
import interface_adapter.TradeViewModel;

public class TradeView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "trade";
    private final TradeViewModel tradeViewModel;
    private final TradeController tradeController;
    private final JTextField valueInputField = new JTextField(15);
    private final JButton cancel;
    private final JButton trade;

    public TradeView(TradeController tradeController, TradeViewModel tradeViewModel) {
        this.tradeController = tradeController;
        this.tradeViewModel = tradeViewModel;
        tradeViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(tradeViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel amountInfo = new LabelTextPanel(new JLabel(tradeViewModel.TRADE_LABEL), valueInputField);
        JPanel buttons = new JPanel();
        cancel = new JButton(tradeViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);
        trade = new JButton(tradeViewModel.CONFIRM_BUTTON_LABEL);
        trade.add(trade);

        trade.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource() == "trade") {
                            TradeState currentState = tradeViewModel.getState();
                            tradeController.execute(currentState.getTradeValue());
                        }
                    }
                }
        );
        cancel.addActionListener(this);

        valueInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        TradeState currentState = tradeViewModel.getState();
                        currentState.setTradeValue(Double.parseDouble(valueInputField.getText() + e.getKeyChar()));
                        tradeViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {}

                    @Override
                    public void keyReleased(KeyEvent e) {}
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(amountInfo);
        this.add(buttons);
    }
}
