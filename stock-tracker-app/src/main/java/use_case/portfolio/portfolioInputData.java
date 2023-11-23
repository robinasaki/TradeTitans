package use_case.portfolio;

public class portfolioInputData {
    private final String Portfolioname;

    public portfolioInputData(String portfolioname){
        this.Portfolioname = portfolioname;
    }

    public String getPortfolioname() {
        return Portfolioname;
    }
}
