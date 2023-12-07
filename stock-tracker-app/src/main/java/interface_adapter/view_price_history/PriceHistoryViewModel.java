package interface_adapter.view_price_history;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PriceHistoryViewModel extends ViewModel {

    private PriceHistoryState state;

    public PriceHistoryViewModel() {
        super("price_history");
    }

    public void setState(PriceHistoryState state) {
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

    public PriceHistoryState getState() {
        return state;
    }
}
