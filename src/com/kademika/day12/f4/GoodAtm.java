package com.kademika.day12.f4;


public class GoodAtm implements Atm {
    public GoodAtm() {
    }

    @Override
    public void checkBalance(long accountId) {
        System.out.println(accountId + " going to withdraw some money.");
    }

    @Override
    public void withdrawMoney(long accountId, int amount) {
        if (allowWithdrawal(accountId, amount)) {
            updateBalance(accountId, amount, TransactionType.WITHDRAWAL);
        }
    }

    private void updateBalance(long accountId, int amount, TransactionType type) {
        System.out.println("Successful " + type + " account: " + accountId + " amount: " + amount);
    }

    private boolean allowWithdrawal(long accountId, int amount) {
        return true;
    }

    enum TransactionType {
        DEPOSIT, WITHDRAWAL
    }
}
