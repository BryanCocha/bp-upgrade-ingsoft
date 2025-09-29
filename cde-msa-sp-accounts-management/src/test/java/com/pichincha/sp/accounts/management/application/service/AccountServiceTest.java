package com.pichincha.sp.accounts.management.application.service;

import com.pichincha.sp.accounts.management.domain.Account;
import com.pichincha.sp.accounts.management.application.output.port.AccountOutputPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AccountServiceTest {
    @Mock
    private AccountOutputPort accountOutputPort;

    @InjectMocks
    private AccountService accountService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAccountByNumber() {
        Account account = Account.builder()
            .idAccount("1")
            .accountNumber("12345")
            .accountType("AHORRO")
            .initialBalance(java.math.BigDecimal.TEN)
            .state(true)
            .idMgmtCustomer("CUST123")
            .build();
        when(accountOutputPort.getAccountByNumber("12345"))
            .thenReturn(Collections.singletonList(account));
        List<Account> result = accountService.getAccountByNumber("12345");
        assertEquals(1, result.size());
        verify(accountOutputPort).getAccountByNumber("12345");
    }

    @Test
    void testCreateAccount() {
        Account account = Account.builder()
            .idAccount("1")
            .accountNumber("12345")
            .accountType("AHORRO")
            .initialBalance(java.math.BigDecimal.TEN)
            .state(true)
            .idMgmtCustomer("CUST123")
            .build();
        when(accountOutputPort.createAccount(account)).thenReturn(account);
        Account result = accountService.createAccount(account);
        assertNotNull(result);
        verify(accountOutputPort).createAccount(account);
    }

    @Test
    void testUpdateAccount() {
        Account account = Account.builder()
            .idAccount("1")
            .accountNumber("12345")
            .accountType("AHORRO")
            .initialBalance(java.math.BigDecimal.TEN)
            .state(true)
            .idMgmtCustomer("CUST123")
            .build();
        when(accountOutputPort.updateAccount(account)).thenReturn(account);
        Account result = accountService.updateAccount(account);
        assertNotNull(result);
        verify(accountOutputPort).updateAccount(account);
    }

    @Test
    void testDeleteAccount() {
        doNothing().when(accountOutputPort).deleteAccount("1");
        accountService.deleteAccount("1");
        verify(accountOutputPort).deleteAccount("1");
    }

    @Test
    void testEditAccount() {
        Account account = Account.builder()
            .idAccount("1")
            .accountNumber("12345")
            .accountType("AHORRO")
            .initialBalance(java.math.BigDecimal.TEN)
            .state(true)
            .idMgmtCustomer("CUST123")
            .build();
        when(accountOutputPort.editAccount(account)).thenReturn(account);
        Account result = accountService.editAccount(account);
        assertNotNull(result);
        verify(accountOutputPort).editAccount(account);
    }
}
