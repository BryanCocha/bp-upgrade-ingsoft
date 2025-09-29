package com.pichincha.sp.accounts.management.infrastructure.output.repository.database;

import com.pichincha.sp.accounts.management.infrastructure.output.repository.entity.MgmtAccount;
import com.pichincha.sp.accounts.management.infrastructure.output.repository.entity.MgmtCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author bryancocha@gmail.com
 * @class_name MgmtAccountJpa.java
 * @class_description
 * @create_date 27/09/2025 Copyright 2022.
 */
public interface MgmtAccountRepository extends JpaRepository<MgmtAccount, String> {
    MgmtAccount findByAccountNumber(String accountNumber);
    void deleteByAccountNumber(@Param("accountNumber") String accountNumber);
    List<MgmtAccount> findAll();
    MgmtAccount findByidAccount(Integer idMgmtAccount);
    List<MgmtAccount> findByIdMgmtCustomer(Integer idMgmtCustomer);
}
