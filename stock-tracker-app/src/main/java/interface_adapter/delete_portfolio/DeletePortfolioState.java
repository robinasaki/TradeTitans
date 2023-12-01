package interface_adapter.delete_portfolio;

public class DeletePortfolioState {
    //private String confirm;
    private String portfolioName;
    //private String Cancel;

    public DeletePortfolioState(DeletePortfolioState deletePortfolioState){
        //this.Cancel = deletePortfolioState.Cancel;
        //this.confirm = deletePortfolioState.confirm;
        this.portfolioName = deletePortfolioState.portfolioName;
    }

    public DeletePortfolioState(String portfolioName){
        this.portfolioName = portfolioName;
    }

    public String getPortfolioName() {
        return portfolioName;
    }
}
