package com.pichincha.sp.accounts.management.infrastructure.output.repository.database;

import com.pichincha.sp.accounts.management.infrastructure.output.repository.entity.MgmtAccount;
import com.pichincha.sp.accounts.management.infrastructure.output.repository.entity.MgmtPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author bryancocha@gmail.com
 * @class_name MgmtPersonRepository.java
 * @class_description
 * @create_date 27/09/2025 Copyright 2022.
 */
public interface MgmtPersonRepository extends JpaRepository<MgmtPerson, String> {
    void deleteByIdentification(@Param("identification") String identification);
    MgmtPerson findByIdentification(String identification);
}
