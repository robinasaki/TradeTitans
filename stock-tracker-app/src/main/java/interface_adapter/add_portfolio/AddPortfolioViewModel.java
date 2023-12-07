package interface_adapter.add_portfolio;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AddPortfolioViewModel extends ViewModel {
    public final String TITLE_LABEL = "Add portfolio";
    public final String ADD_PORTFOLIO_LABEL = "Add portfolio";
    public final String CONFIRM_BUTTON_LABEL = "Add portfolio";
    public final String CANCEL_BUTTON_LABEL = "Cancel";
    private AddPortfolioState state = new AddPortfolioState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public AddPortfolioViewModel() {
        super("add_portfolio");
    }

    public void setState(AddPortfolioState state) {
        this.state = state;
    }

    public AddPortfolioState getState() {
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
