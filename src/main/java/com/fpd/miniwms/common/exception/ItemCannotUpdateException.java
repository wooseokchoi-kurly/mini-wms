package com.fpd.miniwms.common.exception;

import lombok.Getter;

@Getter
public class ItemCannotUpdateException extends RuntimeException{

    private String message = "현재 사용중인 아이템은 수정이 불가능합니다.";

    public ItemCannotUpdateException() {}

    public ItemCannotUpdateException(String message) {
        this.message = message;
    }
}
