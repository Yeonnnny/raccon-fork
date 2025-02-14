package com.sparta.spartaproject.exception;

public interface ExceptionCode {
    String name();
    int getHttpStatus();
    String getMessage();
}
