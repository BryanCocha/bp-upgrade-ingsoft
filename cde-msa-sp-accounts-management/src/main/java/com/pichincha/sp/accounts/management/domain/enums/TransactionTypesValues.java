package com.pichincha.sp.accounts.management.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author bryancocha@gmail.com
 * @class_name TransactionTypesValues.java
 * @class_description
 * @create_date 28/09/2025 Copyright 2022.
 */
@Getter
@AllArgsConstructor
public enum TransactionTypesValues {
    CREDITO("CREDITO"),
    DEBITO("DEBITO");

    private final String value;
}
