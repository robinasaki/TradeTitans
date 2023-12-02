package interface_adapter.delete_portfolio;

import java.util.ArrayList;
import java.util.List;

public class DeletePortfolioState {
    private String confirm;
    private String portfolioName;
    private String cancel;

    public DeletePortfolioState(DeletePortfolioState deletePortfolioState){
        this.cancel = deletePortfolioState.cancel;
        this.confirm = deletePortfolioState.confirm;
        this.portfolioName = deletePortfolioState.portfolioName;
    }

    public DeletePortfolioState(){
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    public String getPortfolioName() {
        return portfolioName;
    }
}
