package use_case.CurrentPrice;

public class CurrentPriceInputData {
    private final String assetInSymbol;
    private final String assetOutSymbol;

    public CurrentPriceInputData(String assetInSymbol, String assetOutSymbol) {
        this.assetInSymbol = assetInSymbol;
        this.assetOutSymbol = assetOutSymbol;
    }

    public String getAssetInSymbol() {
        return assetInSymbol;
    }

    public String getAssetOutSymbol() {
        return assetOutSymbol;
    }

}
