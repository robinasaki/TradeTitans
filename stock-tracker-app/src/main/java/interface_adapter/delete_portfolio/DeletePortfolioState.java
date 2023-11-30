package interface_adapter.delete_portfolio;

public class DeletePortfolioState {
    private String confirm;

    private String Cancel;

    public DeletePortfolioState(DeletePortfolioState deletePortfolioState){
        this.Cancel = deletePortfolioState.Cancel;
        this.confirm = deletePortfolioState.confirm;
    }

    public DeletePortfolioState(){}
}
