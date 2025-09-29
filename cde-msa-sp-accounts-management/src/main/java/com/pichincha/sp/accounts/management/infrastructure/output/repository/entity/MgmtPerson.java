package com.pichincha.sp.accounts.management.infrastructure.output.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author bryancocha@gmail.com
 * @class_name Person.java
 * @class_description
 * @create_date 27/09/2025 Copyright 2022.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "MGMT_PERSON")
@Inheritance(strategy = InheritanceType.JOINED)
public class MgmtPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MGMT_PERSON")
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "GENDER", nullable = false)
    private String gender;

    @Column(name = "AGE", nullable = false)
    private Integer age;

    @Column(name = "IDENTIFICATION", nullable = false, unique = true)
    private String identification;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "PHONE")
    private String phone;
}
