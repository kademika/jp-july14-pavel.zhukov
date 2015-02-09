package com.kademika.day14.shop_v2.Transaction;

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
        transactionDB = new TransactionDB();
        return transactionDB.selectTransactionsByDate(date);
    }

    public ArrayList<Transaction> getAllTrans() {
        transactionDB = new TransactionDB();
        return transactionDB.selectTransactions();
    }

    //***************************************************************
    //    insert
    //***************************************************************

    public boolean insertTrans(Transaction transaction) {
        transactionDB = new TransactionDB();
        return transactionDB.insertTransaction(transaction);
    }

}
