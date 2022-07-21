package com.fpd.miniwms.common.exception;

import lombok.Getter;

@Getter
public class InboundCannotUpdateException extends RuntimeException{

    private String message = "이미 입고완료 처리되어 수정이 불가능합니다.";

    public InboundCannotUpdateException() {}

    public InboundCannotUpdateException(String message) {
        this.message = message;
    }
}
