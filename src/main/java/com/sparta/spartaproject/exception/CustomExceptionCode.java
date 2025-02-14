package com.sparta.spartaproject.exception;

import org.springframework.http.HttpStatus;


public enum CustomExceptionCode implements ExceptionCode {

    POST_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "존재하지 않는 게시글입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal server error"),
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST.value(), "잘못된 요청 파라미터입니다.");

    private final int httpStatus;
    private final String message;

    CustomExceptionCode(int httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    @Override
    public int getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
