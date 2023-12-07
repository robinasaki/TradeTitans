package interface_adapter.trade;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TradeViewModel extends ViewModel {
    public final String TITLE_LABEL = "Trade View";
    public final String TRADE_TYPE_LABEL = "Trade type";
    public final String AMOUNT_LABEL = "Amount";
    public final String CURRENCY_LABEL = "Currency";
    public final String SHARES_LABEL = "Shares";
    public final String SYMBOL_LABEL = "Symbol";
    public final String PRICE_LABEL = "Price";
    
    public final String CONFIRM_BUTTON_LABEL = "Confirm Trade";
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
