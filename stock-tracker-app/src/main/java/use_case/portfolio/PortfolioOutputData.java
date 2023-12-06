package use_case.portfolio;

public class PortfolioOutputData {
    private final String portfolioName;
    private final int portfolioId;

    public PortfolioOutputData(String portfolioName, int portfolioId){
        this.portfolioName = portfolioName;
        this.portfolioId = portfolioId;
    }

    public String getPortfolioName(){
        return portfolioName;
    }
    public int getPortfolioId(){
        return portfolioId;
    }
}
