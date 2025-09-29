package com.pichincha.sp.accounts.management.infrastructure.output.adapter;

import com.pichincha.sp.accounts.management.application.output.port.ReportOutputPort;
import com.pichincha.sp.accounts.management.domain.Report;
import com.pichincha.sp.accounts.management.infrastructure.exception.NotFoundException;
import com.pichincha.sp.accounts.management.infrastructure.output.repository.database.MgmtAccountRepository;
import com.pichincha.sp.accounts.management.infrastructure.output.repository.database.MgmtCustomerRepository;
import com.pichincha.sp.accounts.management.infrastructure.output.repository.database.MgmtPersonRepository;
import com.pichincha.sp.accounts.management.infrastructure.output.repository.database.MgmtTransactionRepository;
import com.pichincha.sp.accounts.management.infrastructure.output.repository.entity.MgmtAccount;
import com.pichincha.sp.accounts.management.infrastructure.output.repository.entity.MgmtCustomer;
import com.pichincha.sp.accounts.management.infrastructure.output.repository.entity.MgmtPerson;
import com.pichincha.sp.accounts.management.infrastructure.output.repository.entity.MgmtTransaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author bcochaba@pichincha.com
 * @class_name ReportOutputAdapter.java
 * @class_description
 * @create_date 29/09/2025 Copyright 2022 Banco Pichincha.
 */

@Repository
@RequiredArgsConstructor
public class ReportOutputAdapter implements ReportOutputPort {
    private final MgmtCustomerRepository mgmtCustomerRepository;
    private final MgmtPersonRepository mgmtPersonRepository;
    private final MgmtTransactionRepository mgmtTransactionRepository;
    private final MgmtAccountRepository mgmtAccountRepository;

    @Override
    public List<Report> getReportById(String identification, String transactionDate) {
        MgmtPerson person = mgmtPersonRepository.findByIdentification(identification);
        if (person == null) {throw new NotFoundException("Not found");}
        MgmtCustomer customer = mgmtCustomerRepository.findByPersonId(Integer.parseInt(person.getId().toString()));
        if (customer == null) {throw new NotFoundException("Not found");}
        List<MgmtAccount> account = mgmtAccountRepository.findByIdMgmtCustomer(Integer.parseInt(customer.getIdMgmtCustomer().toString()));
        if (account.isEmpty()) {throw new NotFoundException("Not found");}
        List<MgmtTransaction> transactions = account.stream()
                .flatMap(acc -> mgmtTransactionRepository.findByIdMgmtAccountAndTransactionDate(Integer.parseInt(acc.getIdAccount().toString()), LocalDate.parse(transactionDate)).stream())
                .toList();
        if (transactions.isEmpty()) {throw new NotFoundException("Not found");}

        List<Report> reportList = account.stream()
                .flatMap(acc -> mgmtTransactionRepository
                        .findByIdMgmtAccountAndTransactionDate(Integer.parseInt(acc.getIdAccount().toString()), LocalDate.parse(transactionDate))
                        .stream()
                        .map(tx -> Report.builder()
                                .transactionDate(LocalDate.parse(tx.getTransactionDate().toString()))
                                .name(person.getName())
                                .accountNumber(acc.getAccountNumber())
                                .transactionType(tx.getTransactionType())
                                .balance(tx.getBalance())
                                .state(acc.getState())
                                .amount(tx.getAmount())
                                .build()
                        )
                )
                .collect(Collectors.toList());

        return reportList;
    }
}
