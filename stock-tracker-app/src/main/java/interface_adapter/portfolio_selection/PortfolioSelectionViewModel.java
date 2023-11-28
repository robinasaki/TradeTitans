package interface_adapter.portfolio_selection;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PortfolioSelectionViewModel extends ViewModel {

    public final String TITLE_LABEL = "Portfolio Selection View";
    public final String CONFIRM_BUTTON = "Confirm";
    public final String CANCEL_BUTTON = "Cancel";

    PortfolioSelectionState state = new PortfolioSelectionState();

    public PortfolioSelectionViewModel() {
        super("portfolioSelection");
    }

    public void setState(PortfolioSelectionState state) {
        this.state = state;
    }

    public PortfolioSelectionState getState() {
        return this.state;
    }

    public void switchToHoldingsView(String viewName) {
        // TODO: implement this
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
