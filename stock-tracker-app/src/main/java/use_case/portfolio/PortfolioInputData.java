package use_case.portfolio;

public class PortfolioInputData {
    private final String Portfolioname;

    public PortfolioInputData(String portfolioname){
        this.Portfolioname = portfolioname;
    }

    public String getPortfolioname() {
        return Portfolioname;
    }
}
