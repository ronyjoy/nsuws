package com.nsuws.api;

import com.google.common.base.MoreObjects;

public class Result {
    private String avg;
    private String std;

    public Result() {
    }

    public Result(String avg, String std) {
        this.avg = avg;
        this.std = std;
    }

    public String getAvg() {
        return avg;
    }

    public String getStd() {
        return std;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("avg", avg)
                .add("std", std)
                .toString();
    }


}