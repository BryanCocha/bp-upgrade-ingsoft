package com.pichincha.sp.accounts.management.infrastructure.output.adapter;

import com.pichincha.sp.accounts.management.application.output.port.AccountOutputPort;
import com.pichincha.sp.accounts.management.domain.Account;
import com.pichincha.sp.accounts.management.infrastructure.exception.NotFoundException;
import com.pichincha.sp.accounts.management.infrastructure.output.repository.database.MgmtCustomerRepository;
import com.pichincha.sp.accounts.management.infrastructure.output.repository.entity.MgmtAccount;
import com.pichincha.sp.accounts.management.infrastructure.output.repository.database.MgmtAccountRepository;
import com.pichincha.sp.accounts.management.infrastructure.output.repository.entity.MgmtCustomer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Collections;

import static com.pichincha.sp.accounts.management.domain.util.UtilClass.validateBean;

/**
 * @author bryancocha@gmail.com
 * @class_name AccountOutputAdapter.java
 * @class_description
 * @create_date 27/09/2025 Copyright 2022.
 */
@Repository
@RequiredArgsConstructor
public class AccountOutputAdapter implements AccountOutputPort {
    private final MgmtAccountRepository mgmtAccountRepository;
    private final MgmtCustomerRepository mgmtCustomerRepository;

    @Override
    public List<Account> getAccountByNumber(String accountNumber) {
        if (accountNumber==null || accountNumber.isEmpty()) {
            List<MgmtAccount> accounts = mgmtAccountRepository.findAll();
            if (accounts == null) {
                throw new NotFoundException("Not found");
            }
            return accounts.stream().map(account -> Account.builder()
                    .idAccount(account.getIdAccount().toString())
                    .accountNumber(account.getAccountNumber())
                    .accountType(account.getAccountType())
                    .initialBalance(account.getInitialBalance())
                    .state(account.getState())
                    .idMgmtCustomer(account.getIdMgmtCustomer().toString())
                    .build()).collect(Collectors.toList());
        }

        MgmtAccount accountRetrieved = mgmtAccountRepository.findByAccountNumber(accountNumber);
            if (accountRetrieved == null) {
                throw new NotFoundException("Not found");
            }
            return List.of(Account.builder()
                    .idAccount(accountRetrieved.getIdAccount().toString())
                    .accountNumber(accountRetrieved.getAccountNumber())
                    .accountType(accountRetrieved.getAccountType())
                    .initialBalance(accountRetrieved.getInitialBalance())
                    .state(accountRetrieved.getState())
                    .idMgmtCustomer(accountRetrieved.getIdMgmtCustomer().toString())
                    .build());
    }

    @Override
    public Account createAccount(Account account) {
        validateBean(account);

        Long id = Long.valueOf(account.getIdMgmtCustomer());
        MgmtCustomer customer = mgmtCustomerRepository.findByIdMgmtCustomer(id);
        if (customer == null) {
            throw new NotFoundException("Client not found with id");
        }
        MgmtAccount entity = new MgmtAccount(
                null,
                account.getAccountNumber(),
                account.getAccountType(),
                account.getInitialBalance(),
                account.getState(),
                Long.parseLong(account.getIdMgmtCustomer())
        );
        MgmtAccount savedAccount = mgmtAccountRepository.save(entity);
        return Account.builder()
                .idAccount(savedAccount.getIdAccount().toString())
                .accountNumber(savedAccount.getAccountNumber())
                .accountType(savedAccount.getAccountType())
                .initialBalance(savedAccount.getInitialBalance())
                .state(savedAccount.getState())
                .idMgmtCustomer(savedAccount.getIdMgmtCustomer().toString())
                .build();
    }

    @Override
    @Transactional
    public void deleteAccount(String accountNumber) {
        mgmtAccountRepository.deleteByAccountNumber(accountNumber);
    }

    @Override
    public Account updateAccount(Account account) {
        MgmtAccount entity = mgmtAccountRepository.findByAccountNumber(account.getAccountNumber());
        if (entity == null) {
            throw new NotFoundException("Not found");
        }

        entity.setAccountType(account.getAccountType());
        entity.setInitialBalance(account.getInitialBalance());
        entity.setState(account.getState());

        MgmtAccount updatedEntity = mgmtAccountRepository.save(entity);

        return Account.builder()
                .idAccount(updatedEntity.getIdAccount().toString())
                .accountNumber(updatedEntity.getAccountNumber())
                .accountType(updatedEntity.getAccountType())
                .initialBalance(updatedEntity.getInitialBalance())
                .state(updatedEntity.getState())
                .idMgmtCustomer(updatedEntity.getIdMgmtCustomer().toString())
                .build();
    }

    @Override
    public Account editAccount(Account account) {
        MgmtAccount entity = mgmtAccountRepository.findByAccountNumber(account.getAccountNumber());
        if (entity == null) {
            throw new NotFoundException("Not found");
        }

        if (account.getAccountType() != null) entity.setAccountType(account.getAccountType());
        if (account.getInitialBalance() != null) entity.setInitialBalance(account.getInitialBalance());
        if (account.getState() != null) entity.setState(account.getState());

        MgmtAccount updatedEntity = mgmtAccountRepository.save(entity);

        return Account.builder()
                .idAccount(updatedEntity.getIdAccount().toString())
                .accountNumber(updatedEntity.getAccountNumber())
                .accountType(updatedEntity.getAccountType())
                .initialBalance(updatedEntity.getInitialBalance())
                .state(updatedEntity.getState())
                .idMgmtCustomer(updatedEntity.getIdMgmtCustomer().toString())
                .build();
    }
}