package interface_adapter.portfolio_selection;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class PortfolioSelectionViewModel extends ViewModel {

    public static final String PROGRAM_TITLE_LABEL = "Trade Titans";
    public static final String TITLE_LABEL = "Portfolio Selection View";
    public static final String ADD_PORTFOLIO_BUTTON_LABEL = "Add Portfolio";
    public List<String> portfolioNames;

    public PortfolioSelectionViewModel() {
        super("portfolio_selection");
    }

    public List<String> getPortfolioNames() {
        return portfolioNames;
    }

    public void setPortfolioNames(List<String> portfolioNames) {
        this.portfolioNames = portfolioNames;
        firePropertyChanged();
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("portfolioNames", null, portfolioNames);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
