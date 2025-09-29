package com.pichincha.sp.accounts.management.infrastructure.output.repository.database;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import com.pichincha.sp.accounts.management.infrastructure.output.repository.entity.MgmtCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author bryancocha@gmail.com
 * @class_name MgmtCustomerRepository.java
 * @class_description
 * @create_date 27/09/2025 Copyright 2022.
 */
public interface MgmtCustomerRepository extends JpaRepository<MgmtCustomer, String> {
    @Query("SELECT c FROM MgmtCustomer c WHERE c.person.identification = :identification")
    MgmtCustomer findByPersonIdentification(@Param("identification") String identification);
    void deleteByPersonIdentification(@Param("identification") String identification);
    MgmtCustomer findByIdMgmtCustomer(Long idMgmtCustomer);
    MgmtCustomer findByPersonId(Integer idMgmtPerson);
    List<MgmtCustomer> findAll();
}
