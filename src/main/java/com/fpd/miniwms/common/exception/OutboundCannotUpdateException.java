package com.fpd.miniwms.common.exception;

import lombok.Getter;

@Getter
public class OutboundCannotUpdateException extends RuntimeException {
    private String message = "이미 출고완료 처리되어 수정이 불가능합니다.";

    public OutboundCannotUpdateException() {}

    public OutboundCannotUpdateException(String message) {
        this.message = message;
    }
}
