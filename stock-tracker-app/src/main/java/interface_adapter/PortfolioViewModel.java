package interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PortfolioViewModel extends ViewModel {

    public final String TITLE_LABEL = "Portfolios View";
    public final String SIGNUP_BUTTON_LABEL = "Add  portfolio";
    public final String CANCEL_BUTTON_LABEL = "Close";

    public PortfolioViewModel(String viewName) {
        super("Portfolio");
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
