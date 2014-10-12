package com.kademika.day12.f4;


public class LegacyAtm implements Atm {
    private int balance;

    public LegacyAtm(int balance) {
        this.balance = balance;
    }

    @Override
    public void checkBalance(long accountId) {
        System.out.println(accountId + " going to withdraw some money.");
    }

    @Override
    public synchronized void withdrawMoney(long accountId, int amount) {
        if (allowWithdrawal(accountId, amount)) {
            updateBalance(accountId, amount, TransactionType.WITHDRAWAL);
        } else {
            System.out.println("*Fail* account: " + accountId + " can not withdraw money (" + amount + ")! Not enough money");
        }
    }

//    @Override
//    public void withdrawMoney(long accountId, int amount) {
//        synchronized (this) {
//            if (allowWithdrawal(accountId, amount)) {
//                updateBalance(accountId, amount, TransactionType.WITHDRAWAL);
//            } else {
//                System.out.println("*Fail* account: " + accountId + " can not withdraw money (" + amount + ")! Not enough money");
//            }
//        }
//    }

    private void updateBalance(long accountId, int amount, TransactionType type) {
        balance -= amount;
        System.out.println("*Successful* " + type + " account: " + accountId + " amount: " + amount + " residue: " + balance);
    }

    private boolean allowWithdrawal(long accountId, int amount) {
        if (amount < balance) return true;
        return false;
    }

    enum TransactionType {
        DEPOSIT, WITHDRAWAL
    }
}
