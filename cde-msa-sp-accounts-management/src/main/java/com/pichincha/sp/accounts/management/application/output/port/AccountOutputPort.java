package com.pichincha.sp.accounts.management.application.output.port;

import com.pichincha.sp.accounts.management.domain.Account;
import java.util.List;

/**
 * @author bryancocha@gmail.com
 * @class_name AccountOutputPort.java
 * @class_description
 * @create_date 26/09/2025 Copyright 2022.
 */
public interface AccountOutputPort {
    List<Account> getAccountByNumber(String accountNumber);
    Account createAccount(Account account);
    Account updateAccount(Account account);
    void deleteAccount(String accountId);
    Account editAccount(Account account);
}
