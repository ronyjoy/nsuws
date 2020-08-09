package com.nsuws.resources;

import com.nsuws.core.crypto.EncryptorAesGcm;

import javax.validation.constraints.NotNull;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/nsuws-api")
@Produces(MediaType.APPLICATION_JSON)
public class CryptoAPI {

    private EncryptorAesGcm cryptoAesCbc = new EncryptorAesGcm();

    @POST
    @Path("decrypt")
    public String decrypt(@NotNull String encrypted_text) throws Exception {
        String plainText = cryptoAesCbc.decrypt(encrypted_text);
        return plainText;
    }
}
