package ru.avdeev.order_service.exception;

import io.netty.handler.codec.http.HttpResponseStatus;

public class ApiException extends RuntimeException {

    private final HttpResponseStatus httpStatus;

    public ApiException(HttpResponseStatus httpStatus, String message, Object... args) {
        super(String.format(message, args));
        this.httpStatus = httpStatus;
    }
}
