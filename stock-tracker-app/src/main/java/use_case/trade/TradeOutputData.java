package use_case.trade;

import entity.BankingTransaction;

public class TradeOutputData {
    private boolean useCaseFailed;
    private boolean successfulTrade;
    private BankingTransaction bankingTransaction;

    public TradeOutputData(boolean useCaseFailed, boolean successfulTrade, BankingTransaction bankingTransaction) {
        this.useCaseFailed = useCaseFailed;
        this.successfulTrade = successfulTrade;
        this.bankingTransaction = bankingTransaction;
    }

    public boolean getTradeInfo() {
        return successfulTrade;
    }

    public BankingTransaction getBankingTransaction() {
        return bankingTransaction;
    }
}
