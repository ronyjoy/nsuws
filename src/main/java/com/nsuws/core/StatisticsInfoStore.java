package com.nsuws.core;

import java.io.Serializable;
import java.math.BigDecimal;

public class StatisticsInfoStore implements Serializable {
    private static final long serialVersionUID = 654216542545884455L;
    private static final StatisticsInfoStore singleton = new StatisticsInfoStore();
    private volatile BigDecimal count = new BigDecimal(0);
    private volatile BigDecimal sum = new BigDecimal(0);
    private volatile BigDecimal avg = new BigDecimal(0);
    private volatile BigDecimal std = new BigDecimal(0);
    private StatisticsInfoStore() {
        if (StatisticsInfoStore.singleton != null) {
            throw new InstantiationError("Creating of this object is not allowed.");
        }
    }
    public void add(BigDecimal count, BigDecimal sum,BigDecimal avg,BigDecimal std) {
        this.count = count;
        this.sum = sum;
        this.avg = avg;
        this.std = std;
    }

    public BigDecimal getCount() {
        return count;
    }

    public BigDecimal getAvg() {
        return avg;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public BigDecimal getStd() {
        return std;
    }

    public static StatisticsInfoStore getInstance() {
        return singleton;
    }

    //Prevents Class creation during cloning
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cloning of this class is not allowed");
    }

    //Prevents New Object Creation by returning same singleton object
    protected Object readResolve() {
        return singleton;
    }

}
