package use_case.trade;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class TradeOutputDataTest {

    @Test
    void constructor_ShouldSetValues() {
        // Arrange
        ArrayList<String> symbols = new ArrayList<>();
        ArrayList<Double> prices = new ArrayList<>();
        ArrayList<Double> shares = new ArrayList<>();
        ArrayList<Double> values = new ArrayList<>();
        ArrayList<Double> changes = new ArrayList<>();
        ArrayList<Double> changePercents = new ArrayList<>();

        // Act
        TradeOutputData outputData = new TradeOutputData(symbols, prices, shares, values, changes, changePercents);

        // Assert
        assertEquals(symbols, outputData.getSymbols());
        assertEquals(prices, outputData.getPrices());
        assertEquals(shares, outputData.getShares());
        assertEquals(values, outputData.getValues());
        assertEquals(changes, outputData.getChanges());
        assertEquals(changePercents, outputData.getChangePercents());
    }

    @Test
    void gettersAndSetters_ShouldWorkCorrectly() {
        // Arrange
        ArrayList<String> symbols = new ArrayList<>();
        ArrayList<Double> prices = new ArrayList<>();
        ArrayList<Double> shares = new ArrayList<>();
        ArrayList<Double> values = new ArrayList<>();
        ArrayList<Double> changes = new ArrayList<>();
        ArrayList<Double> changePercents = new ArrayList<>();
        TradeOutputData outputData = new TradeOutputData(symbols, prices, shares, values, changes, changePercents);

        // Act
        ArrayList<String> newSymbols = new ArrayList<>();
        ArrayList<Double> newPrices = new ArrayList<>();
        ArrayList<Double> newShares = new ArrayList<>();
        ArrayList<Double> newValues = new ArrayList<>();
        ArrayList<Double> newChanges = new ArrayList<>();
        ArrayList<Double> newChangePercents = new ArrayList<>();

        outputData.setSymbols(newSymbols);
        outputData.setPrices(newPrices);
        outputData.setShares(newShares);
        outputData.setValues(newValues);
        outputData.setChanges(newChanges);
        outputData.setChangePercents(newChangePercents);

        // Assert
        assertEquals(newSymbols, outputData.getSymbols());
        assertEquals(newPrices, outputData.getPrices());
        assertEquals(newShares, outputData.getShares());
        assertEquals(newValues, outputData.getValues());
        assertEquals(newChanges, outputData.getChanges());
        assertEquals(newChangePercents, outputData.getChangePercents());
    }
}

