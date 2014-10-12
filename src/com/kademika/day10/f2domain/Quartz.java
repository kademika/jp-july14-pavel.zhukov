package com.kademika.day10.f2domain;

public class Quartz extends Watch {
    private boolean isBacklight;
    private String moreFeatures;

    public Quartz(String name, int weight, WatchType wt, boolean isBacklight, String moreFeatures) {
        super(name, weight, wt);
        this.isBacklight = isBacklight;
        this.moreFeatures = moreFeatures;
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
