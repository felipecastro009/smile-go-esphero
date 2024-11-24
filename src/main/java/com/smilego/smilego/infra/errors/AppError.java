package com.smilego.smilego.infra.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AppError {
    private int status;
    private String message;
    private long timestamp;
}
