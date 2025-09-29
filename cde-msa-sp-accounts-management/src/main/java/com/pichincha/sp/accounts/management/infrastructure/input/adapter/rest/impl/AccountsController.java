package com.pichincha.sp.accounts.management.infrastructure.input.adapter.rest.impl;

import com.pichincha.sp.accounts.management.application.input.port.AccountInputPort;
import com.pichincha.sp.accounts.management.infrastructure.input.adapter.rest.AccountsApi;
import com.pichincha.sp.accounts.management.infrastructure.input.adapter.rest.mapper.MapperAccountInput;
import com.pichincha.sp.accounts.management.infrastructure.input.adapter.rest.mapper.MapperAccountResponse;
import com.pichincha.sp.accounts.management.infrastructure.input.adapter.rest.models.Account;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.util.stream.Collectors;


import java.util.List;

/**
 * @author bryancocha@gmail.com
 * @class_name AccountsController.java
 * @class_description
 * @create_date 26/09/2025 Copyright 2022.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class AccountsController implements AccountsApi {
    private final AccountInputPort accountInputPort;
    private final MapperAccountInput mapperAccountInput;
    private final MapperAccountResponse mapperAccountResponse;

    @Override
    public ResponseEntity<Account> createAccount(Account account) {
        com.pichincha.sp.accounts.management.domain.Account accountRequest = mapperAccountInput.toCreateAccountInput(account);
        var createdAccount = accountInputPort.createAccount(accountRequest);
        Account accountResponse = mapperAccountResponse.toCreateAccountResponse(createdAccount);
        return new ResponseEntity<>(accountResponse, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteAccountByNumber(String accountNumber) {
        accountInputPort.deleteAccount(accountNumber);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<List<Account>> getAccounts(String accountNumber) {
        var getAccount = accountInputPort.getAccountByNumber(accountNumber);
        List<Account> accountResponses = getAccount.stream()
                .map(mapperAccountResponse::toCreateAccountResponse)
                .collect(Collectors.toList());
        return new ResponseEntity<>(accountResponses, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<Account> patchAccountByNumber(Account account) {
        var accountRequest = mapperAccountInput.toUpgradeAccountInput(account);
        var upgradedAccount = accountInputPort.editAccount(accountRequest);
        Account accountResponse = mapperAccountResponse.toUpgradeAccountResponse(upgradedAccount);
        return new ResponseEntity<>(accountResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Account> updateAccountByNumber(Account account) {
        var accountRequest = mapperAccountInput.toUpgradeAccountInput(account);
        var upgradedAccount = accountInputPort.updateAccount(accountRequest);
        Account accountResponse = mapperAccountResponse.toUpgradeAccountResponse(upgradedAccount);
        return new ResponseEntity<>(accountResponse, HttpStatus.OK);
    }
}
