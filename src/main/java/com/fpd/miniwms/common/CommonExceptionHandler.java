package com.fpd.miniwms.common;

import com.fpd.miniwms.common.exception.InboundCannotUpdateException;
import com.fpd.miniwms.common.exception.ItemCannotUpdateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(value = {InboundCannotUpdateException.class, ItemCannotUpdateException.class})
    private ResponseEntity<ExceptionResponse> handleCustomException(RuntimeException e) {
        log.error(e.getClass().getName() + "Handler: {}", e.getMessage());
        e.printStackTrace();

        return ExceptionResponse.toResponseEntity(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    private ResponseEntity<ExceptionResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error(e.getClass().getName() + "Handler: {}", e.getMessage());
        e.printStackTrace();

        return ExceptionResponse.toResponseEntity(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(value = {Exception.class})
    private ResponseEntity<ExceptionResponse> handleException(Exception e) {
        String message = "서버에러가 발생했습니다.";
        e.printStackTrace();

        return ExceptionResponse.toResponseEntity(HttpStatus.BAD_REQUEST, message);
    }
}
