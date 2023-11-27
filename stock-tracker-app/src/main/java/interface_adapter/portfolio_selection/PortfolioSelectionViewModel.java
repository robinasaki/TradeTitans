package interface_adapter.portfolio_selection;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;

public class PortfolioSelectionViewModel extends ViewModel {

    public final String TITLE_LABEL = "Portfolio Selection View";
    public final String CONFIRM_BUTTON = "Confirm";
    public final String CANCEL_BUTTON = "Cancel";

    PortfolioSelectionState state = new PortfolioSelectionState();

    public PortfolioSelectionViewModel() {
        super("portfolioSelection");
    }

    public PortfolioSelectionState getState() {
        return this.state;
    }

    public void switchToHoldingsView(String viewName) {
        // TODO: implement this
    }

    @Override
    public void firePropertyChanged() {

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }
}
