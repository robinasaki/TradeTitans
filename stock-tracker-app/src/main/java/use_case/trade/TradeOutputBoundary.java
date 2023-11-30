package use_case.trade;

public interface TradeOutputBoundary {
    void present();

    void prepareFailView(String error);
}
