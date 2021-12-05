package com.applab.gli.enumeration;

public enum Status {

    INACTIVE("INACTIVE"),
    ACTIVE("ACTIVE");

    private final String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

}
