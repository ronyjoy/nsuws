package com.nsuws.core.dto;

import com.google.common.base.MoreObjects;

import java.math.BigDecimal;

/**
 * Value object to transfer Statistics details
 */
public final class Statistics {

    private BigDecimal avg;
    private BigDecimal std;
    private BigDecimal count;
    private BigDecimal sum;

    /**
     * Constructor
     * @param avg
     * @param std
     * @param count
     * @param sum
     */
    public Statistics(BigDecimal avg, BigDecimal std, BigDecimal count, BigDecimal sum) {
        this.avg = avg;
        this.std = std;
        this.count = count;
        this.sum = sum;
    }

    public BigDecimal getAvg() {
        return avg;
    }

    public BigDecimal getStd() {
        return std;
    }

    public BigDecimal getCount() {
        return count;
    }

    public BigDecimal getSum() {
        return sum;
    }


    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("avg", avg)
                .add("std", std)
                .add("count", count)
                .add("sum", sum)
                .toString();
    }
}
