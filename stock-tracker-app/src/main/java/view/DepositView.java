package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.event.ActionListener;

import interface_adapter.DepositController;
import interface_adapter.DepositViewModel;

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
        // TODO: further implement this
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
