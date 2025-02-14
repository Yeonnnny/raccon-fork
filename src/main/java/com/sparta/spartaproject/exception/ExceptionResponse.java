package com.sparta.spartaproject.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Builder
public class ExceptionResponse {
    private final String code;
    private final String message;
}
