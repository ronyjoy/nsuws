package com.nsuws.core.services;

import com.nsuws.api.Result;
import com.nsuws.core.StatisticsInfoStore;
import com.nsuws.core.dto.Statistics;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class StatisticsService {

    private MathContext precision = new MathContext(4);

    public synchronized Statistics recalculate(BigDecimal number) {
        BigDecimal cur_count = StatisticsInfoStore.getInstance().getCount();
        BigDecimal cur_sum = StatisticsInfoStore.getInstance().getSum();
        BigDecimal cur_std = StatisticsInfoStore.getInstance().getStd();
        BigDecimal cur_avg = StatisticsInfoStore.getInstance().getAvg();

        BigDecimal new_count = cur_count.add(BigDecimal.valueOf(1));
        BigDecimal new_sum = cur_sum.add(number);
        BigDecimal new_avg = (new_sum.divide(new_count,10, RoundingMode.HALF_DOWN)).round(precision);
        BigDecimal new_std = calculateStdDeviation(number, cur_std, cur_avg, new_count, new_avg);

        if (cur_count.compareTo(BigDecimal.valueOf(0)) == 1) {
            new_std = calculateStdDeviation(number, cur_std, cur_avg, new_count, new_avg);
        }

        Statistics statistics = new Statistics(new_avg.stripTrailingZeros().toPlainString(),new_std.stripTrailingZeros().toPlainString());
        StatisticsInfoStore.getInstance().add(new_count,new_sum,new_avg,new_std);
        System.out.println(statistics);
        return statistics;
    }

    private BigDecimal calculateStdDeviation(BigDecimal number, BigDecimal cur_std, BigDecimal cur_avg, BigDecimal new_count, BigDecimal new_avg) {
        BigDecimal new_std = BigDecimal.valueOf(0);
        if (new_count.compareTo(BigDecimal.valueOf(0)) == 1) {
            BigDecimal v1 = new_count.subtract(BigDecimal.valueOf(1)).multiply(cur_std.pow(2));
            BigDecimal v2 = (number.subtract(new_avg)).multiply((number.subtract(cur_avg)));
            BigDecimal v3 = (v1.add(v2)).divide(new_count, 10, RoundingMode.HALF_DOWN);
            new_std = v3.sqrt(precision);
        }
        return new_std;
    }
}
