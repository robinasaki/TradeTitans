package interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TradeViewModel extends ViewModel {
    public final String TITLE_ABEL = "Trade View";
    //TODO: fix TRADE_LABEL
    public final String TRADE_LABEL = "Trade amount";
    public final String CONFIRM_BUTTON_LABEL = "Trade now";
    public final String CANCEL_BUTTON_LABEL = "Cancel";
    private TradeState state = new TradeState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public TradeViewModel() {
        super("trade");
    }

    public void setState(TradeState state) {
        this.state = state;
    }

    public TradeState getState() {
        return this.state;
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
