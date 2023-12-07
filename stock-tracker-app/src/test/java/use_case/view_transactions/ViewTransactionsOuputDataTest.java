package use_case.view_transactions;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

public class ViewTransactionsOuputDataTest {
    private ViewTransactionsOutputData viewTransactionsOutputData;

    @Test
    public void test() {
        ArrayList<String> assetsIn = new ArrayList<>();
        assetsIn.add("$USD");
        ArrayList<String> assetOut = new ArrayList<>();
        assetOut.add("IBM");
        ArrayList<Double> amountIn = new ArrayList<>();
        amountIn.add(150.00);
        ArrayList<Double> amountOut = new ArrayList<>();
        amountOut.add(1.00);
        ArrayList<Date> dates = new ArrayList<>();
        dates.add(new Date(123, 9, 11));

        viewTransactionsOutputData = new ViewTransactionsOutputData("ViewTransactionOutputDataTest", "$USD", assetsIn, assetOut, amountIn, amountOut, dates);

        assert viewTransactionsOutputData.getPortfolioName().equals("ViewTransactionOutputDataTest");
        assert viewTransactionsOutputData.getAssetsIn().equals(assetsIn);
        assert viewTransactionsOutputData.getAssetsOut().equals(assetOut);
        assert viewTransactionsOutputData.getAmountsIn().equals(amountIn);
        assert viewTransactionsOutputData.getAmountsOut().equals(amountOut);
        assert viewTransactionsOutputData.getDates().equals(dates);
    }
}
