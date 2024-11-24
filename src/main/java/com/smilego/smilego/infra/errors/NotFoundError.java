package com.smilego.smilego.infra.errors;

public class NotFoundError extends RuntimeException {
    public NotFoundError() {
        super(ErrorsEnum.NOT_FOUND.toString());
    }
}
