package com.kademika.day14.shop_v2;

import com.kademika.day14.shop_v2.client.ClientOperations;
import com.kademika.day14.shop_v2.personal.PersonalOperations;
import com.kademika.day14.shop_v2.transactions.Transaction;
import com.kademika.day14.shop_v2.client.Client;
import com.kademika.day14.shop_v2.db.*;
import com.kademika.day14.shop_v2.personal.Personal;
import com.kademika.day14.shop_v2.transactions.TransactionOperations;
import com.kademika.day14.shop_v2.watches.*;

import java.sql.Date;
import java.util.ArrayList;

public class Shop {
    private ArrayList<Personal> personal;
    private ArrayList<Client> clients;
    private ArrayList<Mechanic> mechanics;
    private ArrayList<Quartz> quartzs;
    private Report report;
    private ArrayList<Watch> watches;
    //    private ArrayList<Watch> deleteWatches;
    private ArrayList<Watch> boughtWatches;
    //    private ArrayList<Watch>[] boughtWatchesPerWeek;
    private ArrayList<Integer> numberBoughtWatches;
    //    private ArrayList<Integer>[] numBoughtWatchesPerWeek;
    private ArrayList<Transaction> transactions;
    //    private ArrayList<transaction>[] transactionsPerWeek;
    private int day;
    private boolean isBought = false;
    private int idTransaction;
    private DBConnection dbConnection;
    //    private DBSelect dbSelect;
//    private DBInsert dbInsert;
//    private DBDelete dbDelete;
//    private DBUpdate dbUpdate;
    private TransactionOperations transactionOperations;
    private ClientOperations clientOperations;
    private PersonalOperations personalOperations;
    private WatchOperations watchOperations;
    private Date date;

    public Shop() {
//        this.dbConnection = dbConnection;

        transactionOperations = new TransactionOperations();
        clientOperations = new ClientOperations();
        personalOperations = new PersonalOperations();
        watchOperations = new WatchOperations();

//        dbSelect = new DBSelect(dbConnection);
//        dbInsert = new DBInsert(dbConnection);
//        dbDelete = new DBDelete(dbConnection);
//        dbUpdate = new DBUpdate(dbConnection);

//        ArrayList<Client> clients = dbSelect.selectClients();
//        ArrayList<Personal> personal = dbSelect.selectPersonal();
//        ArrayList<Watch> watches = dbSelect.selectWatches();

        mechanics = new ArrayList<>();
        quartzs = new ArrayList<>();
//        deleteWatches = new ArrayList<>();
        boughtWatches = new ArrayList<>();
//        boughtWatchesPerWeek = new ArrayList[]{
//                new ArrayList<>(),
//                new ArrayList<>(),
//                new ArrayList<>(),
//                new ArrayList<>(),
//                new ArrayList<>(),
//                new ArrayList<>(),
//                new ArrayList<>()
//        };
        numberBoughtWatches = new ArrayList<>();
//        numBoughtWatchesPerWeek = new ArrayList[]{
//                new ArrayList<>(),
//                new ArrayList<>(),
//                new ArrayList<>(),
//                new ArrayList<>(),
//                new ArrayList<>(),
//                new ArrayList<>(),
//                new ArrayList<>()
//        };
//        transactions = new ArrayList<>();
//        transactionsPerWeek = new ArrayList[]{
//                new ArrayList<>(),
//                new ArrayList<>(),
//                new ArrayList<>(),
//                new ArrayList<>(),
//                new ArrayList<>(),
//                new ArrayList<>(),
//                new ArrayList<>()
//        };
        report = new Report();

        this.watches = watchOperations.getAllWatches();
        this.clients = clientOperations.getAllClients();
        this.personal = personalOperations.getAllPers();
        this.transactions = transactionOperations.getAllTrans();
        createWatches(watches);
    }

    // ****************************************************************************
    public void createWatches(ArrayList<Watch> watches) {
        for (Watch w : watches) {
            addWatches(w);
        }
    }

    private void addWatches(Watch watch) {
        if (watch instanceof Mechanic) {
            mechanics.add((Mechanic) watch);
        } else if (watch instanceof Quartz) {
            quartzs.add((Quartz) watch);
        }
    }

    // ****************************************************************************

    public void deleteWatch(Watch watch) {
        if (watch instanceof Mechanic) {
            deleteWatchFromCategory(mechanics, watch);
        } else if (watch instanceof Quartz) {
            deleteWatchFromCategory(quartzs, watch);
        }
    }

    private void deleteWatchFromCategory(ArrayList<? extends Watch> arrayWatches, Watch watch) {
//        setDeleteWatch(watch);
        arrayWatches.remove(watch);
    }

    public void deleteWatchFromDB(Watch watch) {
        watchOperations.deleteWatch(watch.getId());
    }

//    private void setDeleteWatch(Watch watch) {
//        deleteWatches.add(watch);
//        return;
//    }

    // ****************************************************************************
    public void setTransaction(Client client, Watch watch, int number,
                               Personal seller) {
        ArrayList<? extends Watch> arrayWatches = typeWatches(watch);
        dbConnection = new DBConnection();
//        dbSelect = new DBSelect(dbConnection);
//        dbInsert = new DBInsert(dbConnection);
//        dbDelete = new DBDelete(dbConnection);
//        dbUpdate = new DBUpdate(dbConnection);
        if (isAvailable(arrayWatches, watch)) {
            buyWatch(watch, number);
            if (isBought) {
                Transaction tr = new Transaction(dbConnection.getNumTransaction() + 1, client, watch, number, getPersonal().get(0), date);
                transactionOperations.insertTrans(tr);
//                idTransaction++;
//                tr.setNumTransaction(dbConnection.getNumTransaction());
                addTransaction(tr, day);
            }
        } else {
            System.out.println("transaction failed. Check the entered data");
        }
        dbConnection.closeConnection();
    }

    private void addTransaction(Transaction transaction, int day) {
        transactions.add(transaction);
//        transactionsPerWeek[day - 1].add(transaction);

    }


    private boolean isAvailable(ArrayList<? extends Watch> watches, Watch watch) {
        for (Watch w : watches) {
            if (w.getId().equals(watch.getId())
                    && watch.getNumber() > 0) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<? extends Watch> typeWatches(Watch watch) {
        ArrayList<? extends Watch> activeWatch;
        if (watch instanceof Mechanic) {
            activeWatch = getMechanics();
        } else {
            activeWatch = getQuartzs();
        }
        return activeWatch;
    }

    public void buyWatch(Watch watch, int number) {
        if (number <= watch.getNumber()) {
            setBuyWatch(watch, number, day);
            watch.setNumber(watch.getNumber() - number);
            isBought = true;
            watchOperations.updateWatch(watch, "", 0, WatchType.None, watch.getNumber(), 0, true, 0, "");
            if (watch.getNumber() == 0) {
                deleteWatch(watch);
            }
        } else {
            System.out.println("ID product: " + watch.getId()
                    + "	Sorry, we have only " + watch.getNumber()
                    + " copies of this watch");
        }
    }

    private void setBuyWatch(Watch watch, int number, int day) {
        boughtWatches.add(watch);
//        boughtWatchesPerWeek[day - 1].add(watch);
        numberBoughtWatches.add(number);
//        numBoughtWatchesPerWeek[day - 1].add(number);
    }

    // ****************************************************************************

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public ArrayList<Quartz> getQuartzs() {
        return quartzs;
    }

    public ArrayList<Mechanic> getMechanics() {
        return mechanics;
    }

    public ArrayList<Personal> getPersonal() {
        return personal;
    }

    public ArrayList<Watch> getWatches() {
        return watches;
    }

//    public ArrayList<Watch> getDeleteWatches() {
//        return deleteWatches;
//    }
//
//    public ArrayList[] getBoughtWatchesPerDay() {
//        return boughtWatchesPerWeek;
//    }

    public ArrayList<Watch> getBoughtWatches() {
        return boughtWatches;
    }

    public ArrayList<Integer> getNumberBoughtWatches() {
        return numberBoughtWatches;
    }

//    public ArrayList[] getNumBoughtWatchesPerDay() {
//        return numBoughtWatchesPerWeek;
//    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

//    public ArrayList[] getTransactionsPerDay() {
//        return transactionsPerWeek;
//    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public ArrayList<String> getStringClient() {
        ArrayList<String> stringClient = new ArrayList<>();
        for (Client cl : clients) {
            if (cl != null) {
                stringClient.add(cl.getFio());
            }
        }
        return stringClient;
    }

    public ArrayList<String> getStringWatch() {
        ArrayList<String> stringWatch = new ArrayList<>();
        for (Watch w : watches) {
            if (w != null) {
                stringWatch.add(w.getName());
            }
        }
        return stringWatch;
    }

    public ArrayList<String> getStringPersonal() {
        ArrayList<String> stringPersonal = new ArrayList<>();
        for (Personal p : personal) {
            if (p != null) {
                stringPersonal.add(p.getFio());
            }
        }
        return stringPersonal;
    }

    public int getIdTransaction() {
        return idTransaction;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public DBConnection getDbConnection() {
        return dbConnection;
    }

    public String[] getFioPerArray() {
        String[] persArray = new String[personal.size()];
        int k = 0;
        for (Personal p : personal) {
            persArray[k] = p.getFio();
            k++;
        }
        return persArray;
    }

    public String[] getFioClientArray() {
        String[] clientArray = new String[clients.size()];
        int k = 0;
        for (Client c : clients) {
            clientArray[k] = c.getFio();
            k++;
        }
        return clientArray;
    }

    public String[] getNameWatchArray() {
        String[] watchArray = new String[watches.size()];
        int k = 0;
        for (Watch w : watches) {
            watchArray[k] = w.getName();
            k++;
        }
        return watchArray;
    }
}
