package interface_adapter.delete_portfolio;

public class DeletePortfolioState {
    private String portfolioName;

    public DeletePortfolioState(DeletePortfolioState deletePortfolioState){
        this.portfolioName = deletePortfolioState.portfolioName;
    }

    public DeletePortfolioState(String portfolioName){
        this.portfolioName = portfolioName;
    }

    public String getPortfolioName() {
        return portfolioName;
    }
}
