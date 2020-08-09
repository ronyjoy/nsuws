package com.nsuws.core.dto;

import com.google.common.base.MoreObjects;

public class Statistics {
    private String avg;
    private String std;

    public Statistics(String avg, String std) {
        this.avg = avg;
        this.std = std;
    }

    public String getAvg() {
        return avg;
    }

    public void setAvg(String avg) {
        this.avg = avg;
    }

    public String getStd() {
        return std;
    }

    public void setStd(String std) {
        this.std = std;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("avg", avg)
                .add("std", std)
                .toString();
    }
}
