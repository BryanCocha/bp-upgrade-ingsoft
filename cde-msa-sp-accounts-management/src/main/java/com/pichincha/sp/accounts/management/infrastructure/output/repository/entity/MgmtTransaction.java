package com.pichincha.sp.accounts.management.infrastructure.output.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author bryancocha@gmail.com
 * @class_name MgmtTransaction.java
 * @class_description
 * @create_date 27/09/2025 Copyright 2022.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "MGMT_TRANSACTION")
public class MgmtTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MGMT_TRANSACTION")
    private Long transactionId;

    @Column(name = "TRANSACTION_DATE", nullable = false)
    private LocalDate transactionDate;

    @Column(name = "TRANSACTION_TYPE", nullable = false)
    private String transactionType;

    @Column(name = "AMOUNT", nullable = false)
    private BigDecimal amount;

    @Column(name = "BALANCE", nullable = false)
    private BigDecimal balance;

    @Column(name = "ID_MGMT_ACCOUNT", nullable = false)
    private Integer idMgmtAccount;

}
