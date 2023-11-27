package interface_adapter.deposit;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class DepositViewModel extends ViewModel {

    public final String TITLE_LABEL = "Deposit View";

    public final String AMOUNT_LABEL = "Deposit amount";

    public final String CONFIRM_BUTTON_LABEL = "Deposit now";

    public final String CANCEL_BUTTON_LABEL = "Cancel";

    private DepositState state = new DepositState();

    public DepositViewModel() {
        super("deposit");
    }

    public void setState(DepositState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public DepositState getState() {
        return this.state;
    }
}
