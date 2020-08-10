package com.nsuws.api;

import com.google.common.base.MoreObjects;

public class DecryptResult {

    private String value;

    public DecryptResult(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("value", value)
                .toString();
    }
}
