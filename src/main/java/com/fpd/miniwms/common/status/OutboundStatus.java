package com.fpd.miniwms.common.status;

import lombok.Getter;

@Getter
public enum OutboundStatus {
    READY("출고대기"),
    COMPLETE("출고완료");

    private final String name;

    OutboundStatus(String name) {
        this.name = name;
    }
}
