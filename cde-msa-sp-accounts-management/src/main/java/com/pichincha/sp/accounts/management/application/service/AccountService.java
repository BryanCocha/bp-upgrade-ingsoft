package com.pichincha.sp.accounts.management.application.service;

import com.pichincha.sp.accounts.management.application.input.port.AccountInputPort;
import com.pichincha.sp.accounts.management.application.output.port.AccountOutputPort;
import com.pichincha.sp.accounts.management.domain.Account;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author bryancocha@gmail.com
 * @class_name AccountService.java
 * @class_description
 * @create_date 26/09/2025 Copyright 2022.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService implements AccountInputPort {

    private final AccountOutputPort accountOutputPort;

    @Override
    public List<Account> getAccountByNumber(String accountNumber) {
        return accountOutputPort.getAccountByNumber(accountNumber);
    }

    @Override
    public Account createAccount(Account account) {
        return accountOutputPort.createAccount(account);
    }

    @Override
    public Account updateAccount(Account account) {
        return accountOutputPort.updateAccount(account);
    }

    @Override
    public void deleteAccount(String accountId) {
        accountOutputPort.deleteAccount(accountId);
    }

    @Override
    public Account editAccount(Account account) {
        return accountOutputPort.editAccount(account);
    }
}
