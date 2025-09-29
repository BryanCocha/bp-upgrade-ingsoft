package com.pichincha.sp.accounts.management.infrastructure.output.repository.database;

import com.pichincha.sp.accounts.management.infrastructure.output.repository.entity.MgmtAccount;
import com.pichincha.sp.accounts.management.infrastructure.output.repository.entity.MgmtTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * @author bryancocha@gmail.com
 * @class_name MgmtTransactionRepository.java
 * @class_description
 * @create_date 27/09/2025 Copyright 2022.
 */
public interface MgmtTransactionRepository extends JpaRepository<MgmtTransaction, Integer> {
    @Query(
            value = "SELECT * FROM mgmt_transaction " +
                    "WHERE id_mgmt_account = :idMgmtAccount " +
                    "AND transaction_date BETWEEN (CAST(:transactionDate AS DATE) - INTERVAL '7 days') AND CAST(:transactionDate AS DATE)",
            nativeQuery = true
    )
    List<MgmtTransaction> findRecentTransactionsByIdMgmtAccount(@Param("idMgmtAccount") Integer idMgmtAccount,
                                                                @Param("transactionDate") LocalDate transactionDate);
    MgmtTransaction findByTransactionId(Integer transactionId);
    void deleteByTransactionId(Integer transactionId);
    @Query(
        value = "SELECT * FROM mgmt_transaction " +
                "WHERE id_mgmt_account = :idMgmtAccount " +
                "AND transaction_date BETWEEN (CAST(:transactionDate AS DATE) - INTERVAL '1 month') AND CAST(:transactionDate AS DATE)",
        nativeQuery = true
    )
    List<MgmtTransaction> findByIdMgmtAccountAndTransactionDate(@Param("idMgmtAccount") Integer idMgmtAccount,
                                                               @Param("transactionDate") LocalDate transactionDate);
}
