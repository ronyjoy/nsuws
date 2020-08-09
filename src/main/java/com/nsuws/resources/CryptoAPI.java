package com.nsuws.resources;

import com.nsuws.core.crypto.CryptoAesCbc;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/nsuws-api")
@Produces(MediaType.APPLICATION_JSON)
public class CryptoAPI {

    private CryptoAesCbc cryptoAesCbc = new CryptoAesCbc();

    @POST
    @Path("decrypt")
    public String decrypt(@NotNull String encrypted_text) throws Exception {
        String plainText = cryptoAesCbc.decrypt(encrypted_text);
        return plainText;
    }
}
