package interface_adapter.delete_portfolio;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class DeletePortfolioViewModel extends ViewModel {

    public final String TITLE_LABEL = "Delete Portfolio";

    public final String CANCEL_BUTTON_LABEL = "Cancel";
    public final String CONFIRM_BUTTON_LABEL = "Confirm";

    private String portfolioName;

    /*
    private DeletePortfolioState state = new DeletePortfolioState();

    public DeletePortfolioState getState() {
        return state;
    }

    public void setState(DeletePortfolioState state) {
        this.state = state;
    }
    */

    public DeletePortfolioViewModel(){
        super("delete_portfolio");
    }

    public String getPortfolioName(){
        return this.portfolioName;
    }

    public void setPortfolioName(String portfolioName){
        this.portfolioName = portfolioName;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged(){
        support.firePropertyChange("state", null, portfolioName);
    }
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
