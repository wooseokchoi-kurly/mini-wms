package com.fpd.miniwms.common.status;

import lombok.Getter;

@Getter
public enum InboundStatus {
    READY("입고대기"),
    COMPLETE("입고완료");

    private final String name;

    InboundStatus(String name) {
        this.name = name;
    }
}
