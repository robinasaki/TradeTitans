package interface_adapter.DeletePortfolio;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class DeletePortfolioViewModel extends ViewModel {
<<<<<<< HEAD
    public final String TITLE_LABEL = "Delete Portfolio";
=======
    private final String TITLE_LABEL = "Delete Portfolio";
>>>>>>> 631f4bed0ab641d538122d352ab658dcc1134a98
    public final String CANCEL_BUTTON_LABEL = "Cancel";
    public final String CONFIRM_BUTTON_LABEL = "Confirm";

    private DeletePortfolioState state = new DeletePortfolioState();

    public void setState(DeletePortfolioState state) {
        this.state = state;
    }

    public DeletePortfolioViewModel(){
        super("Delete Portfolio");
    }
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged(){
        support.firePropertyChange("state", null,this.state );
    }
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
<<<<<<< HEAD

=======
>>>>>>> 631f4bed0ab641d538122d352ab658dcc1134a98
