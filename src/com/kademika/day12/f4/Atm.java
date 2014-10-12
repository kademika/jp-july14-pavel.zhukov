package com.kademika.day12.f4;

public interface Atm {
    public void checkBalance(long accountId);

    public void withdrawMoney(long accountId, int amount);
}
