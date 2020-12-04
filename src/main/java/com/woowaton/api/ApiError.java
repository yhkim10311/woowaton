package com.woowaton.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public class ApiError {

    private final String message;

    private final int status;

    ApiError(Throwable throwable, int status){ this(throwable.getMessage(), status);}

}
