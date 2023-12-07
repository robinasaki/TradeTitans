package interface_adapter.view_transactions;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TransactionsViewModel extends ViewModel {

    private TransactionsState state;

    public TransactionsViewModel() {
        super("transactions");
    }

    public void setState(TransactionsState state) {
        this.state = state;
        firePropertyChanged();
    }

    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        pcs.firePropertyChange("state", null, state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public TransactionsState getState() {
        return state;
    }
}
