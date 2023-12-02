package interface_adapter.delete_portfolio;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class DeletePortfolioViewModel extends ViewModel {

    public final String TITLE_LABEL = "Delete Portfolio";

    public final String CANCEL_BUTTON_LABEL = "Cancel";
    public final String CONFIRM_BUTTON_LABEL = "Confirm";


    private DeletePortfolioState state = new DeletePortfolioState();

    public DeletePortfolioState getState() {
        return state;
    }

    public void setState(DeletePortfolioState state) {
        this.state = state;
    }

    public DeletePortfolioViewModel() {
        super("delete_portfolio");
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void setPortfolioNames(String portfolioName) {
        this.getState().setPortfolioName(portfolioName);
    }

}
