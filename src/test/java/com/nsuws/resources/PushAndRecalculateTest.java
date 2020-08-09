package com.nsuws.resources;

import com.nsuws.api.Result;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class PushAndRecalculateTest {

    StatisticsAPI uut = new StatisticsAPI();

    @Test
    public void pushAndRecalculate() {
        Result result = uut.pushAndRecalculate(BigDecimal.valueOf(4));
        Assert.assertEquals(BigDecimal.valueOf(4).toPlainString(),result.getAvg());
        Assert.assertEquals(BigDecimal.valueOf(0).toPlainString(),result.getStd());

        result = uut.pushAndRecalculate(BigDecimal.valueOf(7));
        Assert.assertEquals(BigDecimal.valueOf(5.5).toPlainString(),result.getAvg());
        Assert.assertEquals(BigDecimal.valueOf(1.5).toPlainString(),result.getStd());

        result = uut.pushAndRecalculate(BigDecimal.valueOf(6));
        Assert.assertEquals(BigDecimal.valueOf(5.667).toPlainString(),result.getAvg());
        Assert.assertEquals(BigDecimal.valueOf(1.247).toPlainString(),result.getStd());

        result = uut.pushAndRecalculate(BigDecimal.valueOf(9));
        Assert.assertEquals(BigDecimal.valueOf(6.5).toPlainString(),result.getAvg());
        Assert.assertEquals(BigDecimal.valueOf(1.803).toPlainString(),result.getStd());

        result = uut.pushAndRecalculate(BigDecimal.valueOf(100));
        Assert.assertEquals(BigDecimal.valueOf(25.2).toPlainString(),result.getAvg());
        Assert.assertEquals(BigDecimal.valueOf(37.43).toPlainString(),result.getStd());
    }

}
