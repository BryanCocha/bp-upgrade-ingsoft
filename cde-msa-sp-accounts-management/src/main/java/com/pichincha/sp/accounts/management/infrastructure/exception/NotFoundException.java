package com.pichincha.sp.accounts.management.infrastructure.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * @author bcochaba@pichincha.com
 * @class_name NotFoundException.java
 * @class_description
 * @create_date 29/09/2025 Copyright 2022 Banco Pichincha.
 */
@Getter
@RequiredArgsConstructor
public class NotFoundException extends RuntimeException{
    private final String message;
    private final HttpStatus code;

    public NotFoundException(String message) {
        super(message);
        this.message = message;
        this.code = null;
    }
}
