package demo;

import entity.Portfolio;
import entity.TradeTransaction;
import entity.Tradeable;
import use_case.FileDataAccessInterface;
import data_access.FileDataAccessObject;

import java.util.Date;

public class CreateDemoPortfolio {

    public static void main(String[] args) {
        // Create a $USD Tradeable
        Tradeable usd = new Tradeable("", "$USD");

        // Create a new portfolio
        Portfolio portfolio = new Portfolio("Demo Portfolio", usd);

        // deposit 10000 USD on Sep 1, 2023
        TradeTransaction transaction1 = new TradeTransaction("$USD", "", 10000, 0, 0, new Date(123, 8, 1));
        portfolio.addTrade(transaction1);

        // buy 93 shares of ORCL on Oct 2, 2023
        TradeTransaction transaction2 = new TradeTransaction("ORCL", "$USD", 93, 9924.03, 0, new Date(123, 9, 2));
        portfolio.addTrade(transaction2);

        // sell 93 shares of ORCL on Nov 1, 2023
        TradeTransaction transaction3 = new TradeTransaction("$USD", "ORCL", 9834.75, 92, 0 , new Date(123, 10, 1));
        portfolio.addTrade(transaction3);

        // withdraw 5000 USD on Dec 1, 2023
        TradeTransaction transaction4 = new TradeTransaction("", "$USD", 0, 5000, 0, new Date(123, 11, 1));
        portfolio.addTrade(transaction4);
        
        FileDataAccessInterface fileDataAccessObject = new FileDataAccessObject();
        fileDataAccessObject.savePortfolio(portfolio);
    }
}
