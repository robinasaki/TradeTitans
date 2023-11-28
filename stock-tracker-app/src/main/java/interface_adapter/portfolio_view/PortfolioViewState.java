package interface_adapter.portfolio_view;

import entity.Portfolio;

import javax.sound.sampled.Port;
import java.util.List;

public class PortfolioViewState {
    private List<Portfolio> portfolioList;

    private Sting FailportfolioView = null;

    public PortfolioViewState(PortfolioViewState copy) {
            this.portfolioList = copy.portfolioList;
    }

    public PortfolioViewState(){
    }

    public Sting getFailportfolioView() {
        return FailportfolioView;
    }

    public List<Portfolio> getPortfolioList() {
        return portfolioList;
    }
}
