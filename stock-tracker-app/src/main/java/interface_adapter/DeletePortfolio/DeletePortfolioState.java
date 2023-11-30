package interface_adapter.DeletePortfolio;

import interface_adapter.deposit.DepositState;

public class DeletePortfolioState {
    private String confirm;
    private String portfolioName;
    private String Cancel;

    public DeletePortfolioState(DeletePortfolioState deletePortfolioState){
        this.Cancel = deletePortfolioState.Cancel;
        this.confirm = deletePortfolioState.confirm;
        this.portfolioName = deletePortfolioState.portfolioName;
    }

    public DeletePortfolioState(){}

    public String getPortfolioName() {
        return portfolioName;
    }
}
