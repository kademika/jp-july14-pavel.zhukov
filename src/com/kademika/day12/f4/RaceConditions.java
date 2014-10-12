package com.kademika.day12.f4;


import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RaceConditions {
    public static void main(String[] args) {
        Random rand = new Random();

        long husband = 1122;
        long wife = 2211;

        Atm atm = new LegacyAtm(2000);

        Set<Runnable> threads = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            threads.add(createWithdrawalThread(atm, wife, rand.nextInt(1000)));
            threads.add(createWithdrawalThread(atm, husband, rand.nextInt(1000)));
        }

        for (Runnable r : threads) {
            new Thread(r).start();
        }
    }

    private static Runnable createWithdrawalThread(final Atm atm, final long accountId, final int amount) {
        return new Runnable() {
            @Override
            public void run() {
                atm.checkBalance(accountId);
                atm.withdrawMoney(accountId, amount);
            }
        };
    }
}
