package com.pichincha.sp.accounts.management.infrastructure.output.repository.entity;

import com.pichincha.sp.accounts.management.domain.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author bryancocha@gmail.com
 * @class_name MgtmAccount.java
 * @class_description
 * @create_date 27/09/2025 Copyright 2022.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MGMT_ACCOUNT")
public class MgmtAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MGMT_ACCOUNT")
    private Long idAccount;

    @Column(name = "ACCOUNT_NUMBER", nullable = false, unique = true)
    private String accountNumber;

    @Column(name = "ACCOUNT_TYPE", nullable = false)
    private String accountType;

    @Column(name = "INITIAL_BALANCE", nullable = false)
    private BigDecimal initialBalance;

    @Column(name = "STATE", nullable = false)
    private Boolean state;

    @Column(name = "ID_MGMT_CUSTOMER", nullable = false)
    private Long idMgmtCustomer;
}
