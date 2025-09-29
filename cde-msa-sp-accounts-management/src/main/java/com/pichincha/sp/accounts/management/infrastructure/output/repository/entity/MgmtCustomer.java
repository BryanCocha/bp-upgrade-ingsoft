package com.pichincha.sp.accounts.management.infrastructure.output.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author bryancocha@gmail.com
 * @class_name MgmtCustomer.java
 * @class_description
 * @create_date 27/09/2025 Copyright 2022.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "MGMT_CUSTOMER")
public class MgmtCustomer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MGMT_CUSTOMER")
    private Long idMgmtCustomer;

    @Column(name = "CUSTOMER_ID", nullable = false, unique = true)
    private String customerId;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "STATE", nullable = false)
    private Boolean state;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "ID_MGMT_PERSON")
    private MgmtPerson person;

}

