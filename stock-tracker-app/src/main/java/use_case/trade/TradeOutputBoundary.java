package use_case.trade;

public interface TradeOutputBoundary {
    void prepareSuccessView(TradeOutputData user);

    void prepareFailView(String error);
}
