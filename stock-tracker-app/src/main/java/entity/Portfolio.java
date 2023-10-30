package entity;

import java.util.HashMap;
import java.util.ArrayList;

import entity.Currency;
import entity.Tradeable;
import entity.Transaction;

public class Portfolio {
    private final String name;

    private final Currency currency;

    private final int portfolioId;

    private HashMap <Tradeable, Double> holdings;
    private ArrayList<Transaction> transactions;

    public Portfolio(String name, Currency currency, int portfolioId){
        this.name = name;
        this.currency = currency;
        this.portfolioId = portfolioId;
    }
}
