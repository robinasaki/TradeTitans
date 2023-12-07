package use_case.trade;

import org.junit.Test;

import java.util.ArrayList;

public class TradeOutputDataTest {
    private TradeOutputData testingTradeOutputData;

    @Test
    public void test() {
        // hypothetical data
        ArrayList<String> symbols = new ArrayList<>();
        symbols.add("IBM");
        ArrayList<Double> prices = new ArrayList<>();
        prices.add(50.00);
        ArrayList<Double> shares = new ArrayList<>();
        shares.add(1.00);
        ArrayList<Double> values = new ArrayList<>();
        values.add(50.00);
        ArrayList<Double> changes = new ArrayList<>();
        changes.add(0.1);
        ArrayList<Double> changePercents = new ArrayList<>();
        changePercents.add(10.00);
        this.testingTradeOutputData = new TradeOutputData(symbols, prices, shares, values, changes, changePercents);

        assert testingTradeOutputData.getSymbols().equals(symbols);
        assert testingTradeOutputData.getPrices().equals(prices);
        assert testingTradeOutputData.getShares().equals(shares);
        assert testingTradeOutputData.getValues().equals(values);
        assert testingTradeOutputData.getChanges().equals(changes);
        assert testingTradeOutputData.getChangePercents().equals(changePercents);

        ArrayList<String> newSymbols = symbols;
        newSymbols.add("AAPL");
        ArrayList<Double> newPrices = prices;
        newPrices.add(100.00);
        ArrayList<Double> newShares = shares;
        newShares.add(2.00);
        ArrayList<Double> newValues = values;
        newValues.add(200.00);
        ArrayList<Double> newChanges = changes;
        newChanges.add(0.05);
        ArrayList<Double> newChangePercents = changePercents;
        newChangePercents.add(5.00);

        testingTradeOutputData.setSymbols(newSymbols);
        assert testingTradeOutputData.getSymbols().equals(newSymbols);
        testingTradeOutputData.setPrices(newPrices);
        assert testingTradeOutputData.getPrices().equals(newPrices);
        testingTradeOutputData.setShares(newShares);
        assert testingTradeOutputData.getShares().equals(newShares);
        testingTradeOutputData.setValues(newValues);
        assert testingTradeOutputData.getValues().equals(newValues);
        testingTradeOutputData.setChanges(newChanges);
        assert testingTradeOutputData.getChanges().equals(newChanges);
        testingTradeOutputData.setChangePercents(newChangePercents);
        assert testingTradeOutputData.getChangePercents().equals(newChangePercents);
    }
}
