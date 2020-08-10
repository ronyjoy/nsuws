package com.nsuws.core.services;

import com.nsuws.core.StatisticsLocalStore;
import com.nsuws.core.dto.Statistics;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class StatisticsService {

    private MathContext precision = new MathContext(4);

    /**
     * Recalculate the statistics based of the new number.
     * @param number
     * @return
     */
    public synchronized Statistics recalculate(BigDecimal number) throws StatisticsServiceException {

        if(number==null) {
            throw new StatisticsServiceException("number is null");
        }

        Statistics cStat = StatisticsLocalStore.getInstance().get();

        BigDecimal new_count = cStat.getCount().add(BigDecimal.ONE);
        BigDecimal new_sum = cStat.getSum().add(number);
        BigDecimal new_avg = (new_sum.divide(new_count,10, RoundingMode.HALF_DOWN)).round(precision);
        //calculate the std deviation from current number, old std deviation, old average, new count, new average
        BigDecimal new_std = calculateStdDeviation(number, cStat, new_count, new_avg);

        //build value object to return
        //store the new updated Statistics in the Store
        Statistics nStat = new Statistics(new_avg,new_std,new_count,new_sum);
        StatisticsLocalStore.getInstance().add(nStat);
        System.out.println(nStat);
        return nStat;
    }

    /**
     * find standared deviation from newnumber/stddeviation so far/ average so far/ new total numbers / new average
     * formula is https://jonisalonen.com/2013/deriving-welfords-method-for-computing-variance/
     *
     * @param number
     * @param cStat
     * @param new_count
     * @param new_avg
     * @return
     */
    private BigDecimal calculateStdDeviation(BigDecimal number, Statistics cStat, BigDecimal new_count, BigDecimal new_avg) {
        BigDecimal new_std = BigDecimal.valueOf(0);
        if (new_count.compareTo(BigDecimal.valueOf(0)) == 1) {
            BigDecimal v1 = new_count.subtract(BigDecimal.valueOf(1)).multiply(cStat.getStd().pow(2));
            BigDecimal v2 = (number.subtract(new_avg)).multiply((number.subtract(cStat.getAvg())));
            BigDecimal v3 = (v1.add(v2)).divide(new_count, 10, RoundingMode.HALF_DOWN);
            new_std = v3.sqrt(precision);
        }
        return new_std;
    }
}
