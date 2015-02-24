package com.kademika.day14.shop_v2.transactions;

import com.kademika.day14.shop_v2.domain.Transaction;

import java.util.ArrayList;
import java.sql.Date;

/**
 * Created by Admin on 09.02.2015.
 */
public class TransactionOperations {
    private TransactionDB transactionDB;

    public TransactionOperations() {

    }

    //***************************************************************
    //    get
    //***************************************************************

    public ArrayList<Transaction> getTransByDate(Date date) {
        ArrayList<Transaction> transactions = null;
        transactionDB = new TransactionDB();
        transactions = transactionDB.selectTransactionsByDate(date);
        transactionDB.closeTransactionDB();
        return transactions;
    }

    public ArrayList<Transaction> getAllTrans() {
        ArrayList<Transaction> transactions = null;
        transactionDB = new TransactionDB();
        transactions = transactionDB.selectTransactions();
        transactionDB.closeTransactionDB();
        return transactions;
    }

    //***************************************************************
    //    insert
    //***************************************************************

    public boolean insertTrans(Transaction transaction) {
        boolean bool;
        transactionDB = new TransactionDB();
        bool = transactionDB.insertTransaction(transaction);
        transactionDB.closeTransactionDB();
        return bool;
    }

}
