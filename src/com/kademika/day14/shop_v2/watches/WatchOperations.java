package com.kademika.day14.shop_v2.watches;

import com.kademika.day14.shop_v2.domain.Watch;

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
            watchDB.closeWatchDB();
            return true;
        }
        watchDB.closeWatchDB();
        return false;
    }

    //***************************************************************
    //    get
    //***************************************************************

    public Watch getWatchById(String id) {
        Watch watch = null;
        watchDB = new WatchDB();
        watch = watchDB.selectWatchById(id);
        watchDB.closeWatchDB();
        return watch;
    }

    public ArrayList<Watch> getAllWatches() {
        ArrayList<Watch> watches = null;
        watchDB = new WatchDB();
        watches = watchDB.selectWatches();
        watchDB.closeWatchDB();
        return watches;
    }

    //***************************************************************
    //    update
    //***************************************************************

    public boolean updateWatch(Watch watch, String name, int weight, WatchType watchType, int number,
                               double price, boolean backlight, int numberArrows, String moreFeatures) {
        boolean bool;
        watchDB = new WatchDB();
        bool = watchDB.updateWatch(watch, name, weight, watchType, number, price, backlight, numberArrows, moreFeatures);
        watchDB.closeWatchDB();
        return bool;
    }

    //***************************************************************
    //    insert
    //***************************************************************

    public boolean insertWatch(Watch watch) {
        boolean bool;
        watchDB = new WatchDB();
        bool = watchDB.insertWatch(watch);
        watchDB.closeWatchDB();
        return bool;
    }

    //***************************************************************
    //    delete
    //***************************************************************

    public boolean deleteWatch(String id) {
        boolean bool;
        watchDB = new WatchDB();
        bool = watchDB.deleteClient(id);
        watchDB.closeWatchDB();
        return bool;
    }
}
