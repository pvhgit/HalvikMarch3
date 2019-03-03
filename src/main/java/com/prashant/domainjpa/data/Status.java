package com.prashant.domainjpa.data;

import java.util.Arrays;

public enum Status {
    PENDING,
    APPROVED,
    REJECTED;

    public static Status getStatusEnum(String status) {
        return Arrays.stream(Status.values()).filter(v -> v.toString().equalsIgnoreCase(status)).findAny()
                .orElseThrow(() -> new RuntimeException("Bad Status value: " + status));
    }
}
