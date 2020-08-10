package com.nsuws.core;

import com.nsuws.core.dto.Statistics;

import java.io.Serializable;
import java.math.BigDecimal;

public class StatisticsLocalStore implements StatisticsStore, Serializable {
    private static final long serialVersionUID = 654216542545884455L;
    private static final StatisticsLocalStore singleton = new StatisticsLocalStore();
    private volatile BigDecimal count = BigDecimal.ZERO;
    private volatile BigDecimal sum = BigDecimal.ZERO;
    private volatile BigDecimal avg = BigDecimal.ZERO;
    private volatile BigDecimal std = BigDecimal.ZERO;

    private StatisticsLocalStore() {
        if (StatisticsLocalStore.singleton != null) {
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

    public static StatisticsLocalStore getInstance() {
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

    @Override
    public void add(Statistics data) {
        this.count = data.getCount();
        this.sum = data.getSum();
        this.avg = data.getAvg();
        this.std = data.getStd();
    }

    @Override
    public Statistics get() {
        return new Statistics(this.avg,this.std,this.count,this.sum);
    }
}
