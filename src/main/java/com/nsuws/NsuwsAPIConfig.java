package com.nsuws;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

import javax.validation.constraints.NotNull;

public class NsuwsAPIConfig extends Configuration {
    @NotNull
    private final String encryptionKey;

    @JsonCreator
    public NsuwsAPIConfig(@JsonProperty("encryptionKey") String encryptionKey) {
        this.encryptionKey = encryptionKey;
    }

    public String getEncryptionKey() {
        return encryptionKey;
    }
}