package use_case.update_prices;

import org.junit.Test;


import javax.print.attribute.standard.NumberUp;
import java.util.ArrayList;

public class UpdatePricesOutputDataTest {
    private UpdatePricesOutputData updatePricesOutputData;

    @Test
    public void test() {
        ArrayList<String> symbols = new ArrayList<>();
        symbols.add("$USD");
        ArrayList<Double> prices = new ArrayList<>();
        prices.add(1.00);
        ArrayList<Double> shares = new ArrayList<>();
        shares.add(50.00);
        ArrayList<Double> values = new ArrayList<>();
        values.add(50.00);
        ArrayList<Double> changes = new ArrayList<>();
        changes.add(0.1);
        ArrayList<Double> changePercents = new ArrayList<>();
        changePercents.add(10.00);

        updatePricesOutputData = new UpdatePricesOutputData("UpdatePricesOutputDataTestPortfolio", "$USD", symbols, prices, shares, values, changes, changePercents);

        assert updatePricesOutputData.getPortfolioName().equals("UpdatePricesOutputDataTestPortfolio");
        assert updatePricesOutputData.getDefaultCurrency().equals("$USD");
        assert updatePricesOutputData.getSymbols().equals(symbols);
        assert updatePricesOutputData.getPrices().equals(prices);
        assert updatePricesOutputData.getShares().equals(shares);
        assert updatePricesOutputData.getValues().equals(values);
        assert updatePricesOutputData.getChanges().equals(changes);
        assert updatePricesOutputData.getChangePercents().equals(changePercents);

        ArrayList<String> newSymbols = symbols;
        newSymbols.add("IBM");
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

        updatePricesOutputData.setSymbols(newSymbols);
        assert updatePricesOutputData.getSymbols().equals(newSymbols);
        updatePricesOutputData.setPrices(newPrices);
        assert updatePricesOutputData.getPrices().equals(newPrices);
        updatePricesOutputData.setShares(newShares);
        assert updatePricesOutputData.getShares().equals(newShares);
        updatePricesOutputData.setValues(newValues);
        assert updatePricesOutputData.getValues().equals(newValues);
        updatePricesOutputData.setChanges(newChanges);
        assert updatePricesOutputData.getChanges().equals(newChanges);
        updatePricesOutputData.setChangePercents(newChangePercents);
        assert updatePricesOutputData.getChangePercents().equals(newChangePercents);
        updatePricesOutputData.setPortfolioName("UpdatePricesOutputDataTestPortfolioAlt");
        assert updatePricesOutputData.getPortfolioName().equals("UpdatePricesOutputDataTestPortfolioAlt");
        updatePricesOutputData.setDefaultCurrency("$CNY");
        assert updatePricesOutputData.getDefaultCurrency().equals("$CNY");
    }
}
