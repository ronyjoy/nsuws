package com.nsuws.api;

import com.google.common.base.MoreObjects;

import java.math.BigDecimal;

/**
 * Statistics Input Object
 */
public class StatisticsInput {

    private BigDecimal number;

    public BigDecimal getNumber() {
        return number;
    }

    public void setNumber(BigDecimal number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("number", number)
                .toString();
    }
}
