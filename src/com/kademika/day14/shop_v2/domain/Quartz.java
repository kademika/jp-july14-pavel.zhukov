package com.kademika.day14.shop_v2.domain;

import com.kademika.day14.shop_v2.watches.WatchType;

public class Quartz extends Watch {
    private boolean isBacklight;
    private String moreFeatures;

    public Quartz() {
    }

    public Quartz(String name, int weight, WatchType wt, String id, int number, double price, boolean isBacklight, String moreFeatures) {
        super(name, weight, wt, id, number, price);
        this.isBacklight = isBacklight;
        this.moreFeatures = moreFeatures;
    }

    @Override
    public String getInfo() {
        return id + " Quartz watch: " + name + "; weight: " + weight + "g; type: " + type + "; isBacklight: "
                + isBacklight + "; moreFeatures: " + moreFeatures + ";  number: "
                + number + "; price: " + price + "$";
    }

    public boolean isBacklight() {
        return isBacklight;
    }

    public void setBacklight(boolean isBacklight) {
        this.isBacklight = isBacklight;
    }

    public String getMoreFeatures() {
        return moreFeatures;
    }

    public void setMoreFeatures(String moreFeatures) {
        this.moreFeatures = moreFeatures;
    }
}
