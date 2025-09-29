package com.pichincha.sp.accounts.management.infrastructure.exception;

import lombok.Getter;

import java.util.List;

/**
 * @author bcochaba@pichincha.com
 * @class_name ValidatorBeanException.java
 * @class_description
 * @create_date 29/09/2025 Copyright 2022 Banco Pichincha.
 */
@Getter
public class ValidatorBeanException extends RuntimeException{
    private final List<String> errors;

    public ValidatorBeanException(String message, List<String> errors) {
        super(message);
        this.errors = errors;
    }
}
