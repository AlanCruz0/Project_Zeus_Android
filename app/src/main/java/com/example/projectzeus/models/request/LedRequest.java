package com.example.projectzeus.models.request;

public class LedRequest {
    private String value;

    public LedRequest(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
