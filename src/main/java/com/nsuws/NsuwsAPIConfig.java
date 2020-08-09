package com.nsuws;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

import javax.validation.constraints.NotNull;

public class NsuwsAPIConfig extends Configuration {
    @NotNull
    private final String key;

    @JsonCreator
    public NsuwsAPIConfig(@JsonProperty("key") String key) {
        this.key = key;
    }

    public String getDefaultSize() {
        return key;
    }
}