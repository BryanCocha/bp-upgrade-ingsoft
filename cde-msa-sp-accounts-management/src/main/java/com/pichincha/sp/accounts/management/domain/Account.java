package com.pichincha.sp.accounts.management.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author bryancocha@gmail.com
 * @class_name Cuenta.java
 * @class_description
 * @create_date 26/09/2025 Copyright 2022.
 */
@Getter
@Setter
@Builder
public class Account {
    private String idAccount;

    @NotNull
    @Pattern(regexp = "^.{1,24}$", message = "Debe tener entre 1 y 24 caracteres")
    private String accountNumber;

    @NotNull
    @Pattern(regexp = "^.{1,24}$", message = "Debe tener entre 1 y 24 caracteres")
    private String accountType;

    private BigDecimal initialBalance;

    private Boolean state;

    private String idMgmtCustomer;
}

