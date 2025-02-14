package com.sparta.spartaproject.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RestApiException.class)
    public ResponseEntity<ExceptionResponse> handleCustomException(RestApiException e) {
        log.error("handleCustomException", e);
        ExceptionCode exceptionCode = e.getExceptionCode();
        return handleExceptionInternal(exceptionCode);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponse> handleIllegalArgument(IllegalArgumentException e) {
        log.warn("handleIllegalArgument", e);
        ExceptionCode exceptionCode = CustomExceptionCode.INVALID_PARAMETER;
        return handleExceptionInternal(exceptionCode, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleAllException(Exception e) {
        log.error("handleAllException", e);
        ExceptionCode exceptionCode = CustomExceptionCode.INTERNAL_SERVER_ERROR;
        return handleExceptionInternal(exceptionCode);
    }

    private ResponseEntity<ExceptionResponse> handleExceptionInternal(ExceptionCode exceptionCode) {
        return ResponseEntity
                .status(exceptionCode.getHttpStatus())
                .body(makeErrorResponse(exceptionCode));
    }

    private ResponseEntity<ExceptionResponse> handleExceptionInternal(ExceptionCode exceptionCode, String message) {
        return ResponseEntity
                .status(exceptionCode.getHttpStatus())
                .body(makeErrorResponse(exceptionCode, message));
    }

    private ExceptionResponse makeErrorResponse(ExceptionCode exceptionCode) {
        return ExceptionResponse.builder()
                .code(exceptionCode.name())
                .message(exceptionCode.getMessage())
                .build();
    }

    private ExceptionResponse makeErrorResponse(ExceptionCode exceptionCode, String message) {
        return ExceptionResponse.builder()
                .code(exceptionCode.name())
                .message(message)
                .build();
    }
}
