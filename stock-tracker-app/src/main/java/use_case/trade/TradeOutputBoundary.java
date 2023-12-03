package use_case.trade;

public interface TradeOutputBoundary {
    void present(TradeOutputData outputData);

    void prepareFailView(String error);
}
