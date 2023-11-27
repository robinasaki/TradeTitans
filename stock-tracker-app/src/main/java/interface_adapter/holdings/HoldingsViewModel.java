package interface_adapter.holdings;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class HoldingsViewModel extends ViewModel {

    public final String TITLE_LABEL = "Holdings";
    public final String SYMBOL_LABEL = "Symbol";
    public final String QUOTE_LABEL = "Quote";
    public final String SHARES_LABEL = "Shares";
    public final String VALUE_LABEL = "Value";
    
    public HoldingsState state = new HoldingsState();

    public HoldingsViewModel() {
        super("holdings");
    }

    public void setState(HoldingsState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public HoldingsState getState() {
        return state;
    }

}