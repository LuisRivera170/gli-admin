package com.applab.gli.enumeration;

public enum ResponseStatus {

    SUCCESS("SUCCESS"),
    CREATED("CREATED"),
    UPDATED("UPDATED"),
    DELETED("DELETED"),
    NOT_FOUND("NOT_FOUND");

    private final String status;

    ResponseStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

}
