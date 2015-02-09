package com.kademika.day14.shop_v2.watches;

import java.util.ArrayList;

/**
 * Created by Admin on 09.02.2015.
 */
public class WatchOperations {

    private WatchDB watchDB;

    public WatchOperations() {

    }

    public boolean checkWatchById(String id) {
        watchDB = new WatchDB();
        if (watchDB.selectWatchById(id) != null) {
            return true;
        }
        return false;
    }

    //***************************************************************
    //    get
    //***************************************************************

    public Watch getWatchById(String id) {
        watchDB = new WatchDB();
        return watchDB.selectWatchById(id);
    }

    public ArrayList<Watch> getAllWatches() {
        watchDB = new WatchDB();
        return watchDB.selectWatches();
    }

    //***************************************************************
    //    update
    //***************************************************************

    public boolean updateWatch(Watch watch, String name, int weight, WatchType watchType, int number,
                               double price, boolean backlight, int numberArrows, String moreFeatures) {
        watchDB = new WatchDB();
        return watchDB.updateWatch(watch, name, weight, watchType, number, price, backlight, numberArrows, moreFeatures);
    }

    //***************************************************************
    //    insert
    //***************************************************************

    public boolean insertWatch(Watch watch) {
        watchDB = new WatchDB();
        return watchDB.insertWatch(watch);
    }

    //***************************************************************
    //    delete
    //***************************************************************

    public boolean deleteWatch(String id) {
        watchDB = new WatchDB();
        return watchDB.deleteClient(id);
    }
}
