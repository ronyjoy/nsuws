package com.nsuws.resources;


import com.nsuws.api.Result;
import com.nsuws.core.crypto.EncryptorAesGcm;
import com.nsuws.core.dto.Statistics;
import com.nsuws.core.services.StatisticsService;

import javax.validation.constraints.NotNull;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.math.BigDecimal;

@Path("/nsuws-api/statistics")
@Produces(MediaType.APPLICATION_JSON)
public class StatisticsAPI {

    private StatisticsService statisticsService = new StatisticsService();
    private EncryptorAesGcm encryptorAesGcm = new EncryptorAesGcm();

    @POST
    @Path("recalculate")
    public Result pushAndRecalculate(@NotNull BigDecimal number)  {
        Statistics statistics = statisticsService.recalculate(number);
        Result result = new Result(statistics.getAvg(),statistics.getStd());
        return result;
    }

    @POST
    @Path("recalculate/encrypt")
    public Result pushAndRecalculateEncrypt(@NotNull BigDecimal number) throws Exception {
        Statistics statistics = statisticsService.recalculate(number);
        String avg = encryptorAesGcm.encrypt(statistics.getAvg());
        String std = encryptorAesGcm.encrypt(statistics.getStd());
        Result result = new Result(avg,std);
        return result;
    }

}
