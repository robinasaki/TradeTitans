package interface_adapter.credit;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CreditViewModel extends ViewModel {

    public CreditViewModel() {
        super("credit");
    }

    public void setState(CreditState state) {
        this.state = state;
    }

    public CreditState state = new CreditState();

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public CreditState getState() {return state;}
}
