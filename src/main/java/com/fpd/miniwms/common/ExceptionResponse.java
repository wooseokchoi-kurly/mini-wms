package com.fpd.miniwms.common;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Getter
@Builder
public class ExceptionResponse {
    @Builder.Default
    private final LocalDateTime timestamp = LocalDateTime.now();
    private final int status;
    private final String error;
    private final String message;

    public static ResponseEntity<ExceptionResponse> toResponseEntity(HttpStatus httpStatus, String message) {
        return ResponseEntity
                .status(httpStatus)
                .body(ExceptionResponse.builder()
                        .status(httpStatus.value())
                        .error(httpStatus.name())
                        .message(message)
                        .build());
    }
}



