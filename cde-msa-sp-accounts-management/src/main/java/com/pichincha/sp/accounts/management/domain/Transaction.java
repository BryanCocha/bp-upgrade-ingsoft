package com.pichincha.sp.accounts.management.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author bryancocha@gmail.com
 * @class_name Movimiento.java
 * @class_description
 * @create_date 26/09/2025 Copyright 2022.
 */
@Getter
@Setter
@Builder
public class Transaction {
    private String transactionId;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate transactionDate;

    @NotNull
    @Pattern(regexp = "^.{1,10}$", message = "Debe tener entre 1 y 10 caracteres")
    private String transactionType;

    private BigDecimal amount;

    private BigDecimal balance;

    private String idMgmtAccount;

}