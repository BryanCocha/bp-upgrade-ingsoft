package com.pichincha.sp.accounts.management.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author bryancocha@gmail.com
 * @class_name Report.java
 * @class_description
 * @create_date 28/09/2025 Copyright 2022.
 */
@Getter
@Setter
@Builder
public class Report {
    private LocalDate transactionDate;

    private String name;

    private String accountNumber;

    private String transactionType;

    private BigDecimal balance;

    private Boolean state;

    private BigDecimal amount;
}
