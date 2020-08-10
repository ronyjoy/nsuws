package com.nsuws.core.services;

import com.nsuws.core.dto.Statistics;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.MathContext;

public class StatisticsServiceTest {

    StatisticsService uut = new StatisticsService();
    private MathContext precision = new MathContext(4);

    @Test
    public void recalculateTest() throws StatisticsServiceException {
        Statistics result = uut.recalculate(BigDecimal.valueOf(4));
        Assert.assertEquals(BigDecimal.valueOf(4),result.getAvg().stripTrailingZeros());
        Assert.assertEquals(BigDecimal.valueOf(0),result.getStd().stripTrailingZeros());

        result = uut.recalculate(BigDecimal.valueOf(7));
        Assert.assertEquals(BigDecimal.valueOf(5.5),result.getAvg().stripTrailingZeros());
        Assert.assertEquals(BigDecimal.valueOf(1.5),result.getStd().stripTrailingZeros());

        result = uut.recalculate(BigDecimal.valueOf(6));
        Assert.assertEquals(BigDecimal.valueOf(5.667),result.getAvg().stripTrailingZeros());
        Assert.assertEquals(BigDecimal.valueOf(1.247),result.getStd().stripTrailingZeros());

        result = uut.recalculate(BigDecimal.valueOf(9));
        Assert.assertEquals(BigDecimal.valueOf(6.5),result.getAvg().stripTrailingZeros());
        Assert.assertEquals(BigDecimal.valueOf(1.803),result.getStd().stripTrailingZeros());

        result = uut.recalculate(BigDecimal.valueOf(100));
        Assert.assertEquals(BigDecimal.valueOf(25.2),result.getAvg().stripTrailingZeros());
        Assert.assertEquals(BigDecimal.valueOf(37.43),result.getStd().stripTrailingZeros());

        result = uut.recalculate(BigDecimal.valueOf(2222111111111.0352));
        Assert.assertEquals(BigDecimal.valueOf(3.704E+11),result.getAvg().stripTrailingZeros());
        Assert.assertEquals(BigDecimal.valueOf(8.281E+11),result.getStd().stripTrailingZeros());


    }

    @Test(expected = StatisticsServiceException.class)
    public void recalculateNullTest() throws StatisticsServiceException {
        Statistics result = uut.recalculate(null);

    }

}
