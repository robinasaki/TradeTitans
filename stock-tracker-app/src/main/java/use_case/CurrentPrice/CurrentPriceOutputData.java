package use_case.CurrentPrice;

public class CurrentPriceOutputData {

    private final double rate;

    public CurrentPriceOutputData(double rate) {
        this.rate = rate;
    }

    public double getRate() {
        return rate;
    }
}
