package interface_adapter.delete_portfolio;

import java.util.ArrayList;
import java.util.List;

public class DeletePortfolioState {
    private String confirm;
    private ArrayList<String> portfolioNames;
    private String cancel;

    private int deletionIndex;

    public DeletePortfolioState(DeletePortfolioState deletePortfolioState){
        this.cancel = deletePortfolioState.cancel;
        this.confirm = deletePortfolioState.confirm;
        this.portfolioNames = deletePortfolioState.portfolioNames;
    }

    public DeletePortfolioState(){
        this.portfolioNames = new ArrayList<>();
    }

    public void addPortfolioName(String portfolioName) {
        this.portfolioNames.add(portfolioName);
    }

    public List<String> getPortfolioNames() {
        return portfolioNames;
    }
}
