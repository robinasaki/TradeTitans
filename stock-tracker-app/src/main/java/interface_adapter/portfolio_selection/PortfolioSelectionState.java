package interface_adapter.portfolio_selection;

import java.util.List;

public class PortfolioSelectionState {

    private List<String> portfolioNames;

    public PortfolioSelectionState(PortfolioSelectionState copy) {
        this.portfolioNames = copy.getPortfolioNames();
    }

    public PortfolioSelectionState() {
    }

    public List<String> getPortfolioNames() {
        return this.portfolioNames;
    }

    public void setPortfolioNames(List<String> portfolioNames) {
        this.portfolioNames = portfolioNames;
    }
}
