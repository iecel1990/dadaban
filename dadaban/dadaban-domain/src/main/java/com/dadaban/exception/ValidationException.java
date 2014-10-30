package com.dadaban.exception;

/**
 * Created by jrose on 8/3/14.
 */
public class ValidationException extends RuntimeException {

    private static final String message = "验证异常";

    public ValidationException() {
        super(message);
    }

    public ValidationException(String message) {
        super(message);
    }
}
