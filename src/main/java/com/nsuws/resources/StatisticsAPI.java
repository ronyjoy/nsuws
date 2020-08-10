package com.nsuws.resources;


import com.nsuws.api.DecryptResult;
import com.nsuws.api.StatisticsInput;
import com.nsuws.api.StatisticsResult;
import com.nsuws.core.crypto.EncryptorAesGcm;
import com.nsuws.core.dto.Statistics;
import com.nsuws.core.services.StatisticsService;
import com.nsuws.core.services.StatisticsServiceException;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.math.BigDecimal;

@Path("/nsuws-api/statistics")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StatisticsAPI {
    private String encryptionKey;
    private StatisticsService statisticsService = new StatisticsService();
    private EncryptorAesGcm encryptorAesGcm;

    public StatisticsAPI(String encryptionKey) {
        this.encryptionKey = encryptionKey;
        encryptorAesGcm = new EncryptorAesGcm(encryptionKey);
    }

    @POST
    @Path("recalculate")
    public StatisticsResult pushAndRecalculate(@NotNull StatisticsInput input) {
        Statistics statistics = null;
        try {
            statistics = statisticsService.recalculate(new BigDecimal(input.getNumber()));
        } catch (StatisticsServiceException e) {
            e.printStackTrace();
        }
        StatisticsResult result = new StatisticsResult(statistics.getAvg().stripTrailingZeros().toPlainString(),
                                    statistics.getStd().stripTrailingZeros().toPlainString());
        return result;
    }

    @POST
    @Path("recalculate/encrypt")
    public StatisticsResult pushAndRecalculateEncrypt(@NotNull StatisticsInput input) {
        Statistics statistics = null;
        StatisticsResult result = null;
        try {
            statistics = statisticsService.recalculate(new BigDecimal(input.getNumber()));
            String avg = encryptorAesGcm.encrypt(statistics.getAvg().toPlainString());
            String std = encryptorAesGcm.encrypt(statistics.getStd().toPlainString());
            result = new StatisticsResult(avg, std);
        } catch (StatisticsServiceException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @GET
    @Path("decrypt")
    public DecryptResult decrypt(@HeaderParam ("cipherTxt") String cipherTxt) throws Exception {
        String plainText = encryptorAesGcm.decrypt(cipherTxt);
        DecryptResult result = new DecryptResult(plainText);
        return result;
    }

}
