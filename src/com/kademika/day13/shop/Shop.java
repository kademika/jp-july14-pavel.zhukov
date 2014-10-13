package com.kademika.day13.shop;

import com.kademika.day13.shop.watches.Mechanic;
import com.kademika.day13.shop.watches.Quartz;
import com.kademika.day13.shop.watches.Watch;

import java.util.ArrayList;

public class Shop {
    private ArrayList<Personal> personal;
    private ArrayList<Client> clients;
    private ArrayList<Mechanic> mechanics;
    private ArrayList<Quartz> quartzs;
    private Report report;
    private ArrayList<Watch> watches;
    private ArrayList<Watch> deleteWatches;
    private ArrayList<Watch> boughtWatches;
    private ArrayList<Watch>[] boughtWatchesPerWeek;
    private ArrayList<Integer> numberBoughtWatches;
    private ArrayList<Integer>[] numBoughtWatchesPerWeek;
    private ArrayList<Transaction> transactions;
    private ArrayList<Transaction>[] transactionsPerWeek;
    private int day;
    private boolean isBought = false;
    private int idTransaction;

    public Shop(ArrayList<Client> clients, ArrayList<Personal> personal,
                ArrayList<Watch> arrayWatches) {
        mechanics = new ArrayList<>();
        quartzs = new ArrayList<>();
        deleteWatches = new ArrayList<>();
        boughtWatches = new ArrayList<>();
        boughtWatchesPerWeek = new ArrayList[7];
        numberBoughtWatches = new ArrayList<>();
        numBoughtWatchesPerWeek = new ArrayList[7];
        transactions = new ArrayList<>();
        transactionsPerWeek = new ArrayList[7];
        report = new Report();

        watches = arrayWatches;
        this.clients = clients;
        this.personal = personal;
        createWatches(arrayWatches);
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
        setDeleteWatch(watch);
        arrayWatches.remove(watch);
    }

    private void setDeleteWatch(Watch watch) {
        deleteWatches.add(watch);
        return;
    }

    // ****************************************************************************
    public void setTransaction(Client client, Watch watch, int number,
                               Personal seller) {
        ArrayList<? extends Watch> arrayWatches = typeWatches(watch);
        if (isAvailable(arrayWatches, watch)) {
            buyWatch(watch, number);
            if (isBought) {
                Transaction tr = new Transaction(client, watch, number, getPersonal().get(0));
                idTransaction++;
                tr.setNumTransaction(idTransaction);
                addTransaction(tr, day);
            }
        } else {
            System.out.println("Transaction failed. Check the entered data");
        }
    }

    private void addTransaction(Transaction transaction, int day) {
        transactions.add(transaction);
        transactionsPerWeek[day - 1] = transactions;

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
        boughtWatchesPerWeek[day - 1] = boughtWatches;
        numberBoughtWatches.add(number);
        numBoughtWatchesPerWeek[day - 1] = numberBoughtWatches;
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

    public ArrayList<Watch> getDeleteWatches() {
        return deleteWatches;
    }

    public ArrayList[] getBoughtWatchesPerDay() {
        return boughtWatchesPerWeek;
    }

    public ArrayList<Watch> getBoughtWatches() {
        return boughtWatches;
    }

    public ArrayList<Integer> getNumberBoughtWatches() {
        return numberBoughtWatches;
    }

    public ArrayList[] getNumBoughtWatchesPerDay() {
        return numBoughtWatchesPerWeek;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public ArrayList[] getTransactionsPerDay() {
        return transactionsPerWeek;
    }

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

}
