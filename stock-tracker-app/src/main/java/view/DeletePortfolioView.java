package view;

import interface_adapter.delete_portfolio.DeletePortfolioState;
import interface_adapter.delete_portfolio.DeletePortfolioViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class DeletePortfolioView extends JFrame implements ActionListener,PropertyChangeListener{
    private final String ViewName = "Delete Portfolio";
    private final DeletePortfolioViewModel deletePortfolioViewModel;
    DeletePortfolioState currentState;
    final JButton confirm;
    final JButton cancel;

    public DeletePortfolioView(DeletePortfolioViewModel deletePortfolioViewModel){
        this.deletePortfolioViewModel = deletePortfolioViewModel;
        this.deletePortfolioViewModel.addPropertyChangeListener((PropertyChangeListener) this);

        JLabel title = new JLabel("Are you sure you want to delete this Portfolio".concat(this.getName()));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel buttons = new JPanel();
        confirm = new JButton(deletePortfolioViewModel.CONFIRM_BUTTON_LABEL);
        buttons.add(confirm);
        cancel = new JButton(deletePortfolioViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);

        cancel.addActionListener(this);
        confirm.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource()=="confirm"){
                            DeletePortfolioState currentState = deletePortfolioViewModel.getState();
                            // TODO : which controller we shall use here?
                        }
                    }
        }

        ); this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, currentState.getPortfolioName() +" deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        JOptionPane.showMessageDialog(null, "Portfolio deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
    }
}
