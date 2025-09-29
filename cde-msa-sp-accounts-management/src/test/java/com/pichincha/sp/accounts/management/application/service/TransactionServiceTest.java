package com.pichincha.sp.accounts.management.application.service;

import com.pichincha.sp.accounts.management.domain.Transaction;
import com.pichincha.sp.accounts.management.application.output.port.TransactionOutputPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransactionServiceTest {
    @Mock
    private TransactionOutputPort transactionOutputPort;

    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetTransactionById() {
        Transaction transaction = Transaction.builder()
            .transactionId("1")
            .transactionDate(java.time.LocalDate.now())
            .transactionType("DEP")
            .amount(java.math.BigDecimal.TEN)
            .balance(java.math.BigDecimal.TEN)
            .idMgmtAccount("ACC123")
            .build();
        when(transactionOutputPort.getTransactionById("1", "2025-09-29"))
            .thenReturn(Collections.singletonList(transaction));
        List<Transaction> result = transactionService.getTransactionById("1", "2025-09-29");
        assertEquals(1, result.size());
        verify(transactionOutputPort).getTransactionById("1", "2025-09-29");
    }

    @Test
    void testCreateTransaction() {
        Transaction transaction = Transaction.builder()
            .transactionId("1")
            .transactionDate(java.time.LocalDate.now())
            .transactionType("DEP")
            .amount(java.math.BigDecimal.TEN)
            .balance(java.math.BigDecimal.TEN)
            .idMgmtAccount("ACC123")
            .build();
        when(transactionOutputPort.createTransaction(transaction)).thenReturn(transaction);
        Transaction result = transactionService.createTransaction(transaction);
        assertNotNull(result);
        verify(transactionOutputPort).createTransaction(transaction);
    }

    @Test
    void testUpdateTransaction() {
        Transaction transaction = Transaction.builder()
            .transactionId("1")
            .transactionDate(java.time.LocalDate.now())
            .transactionType("DEP")
            .amount(java.math.BigDecimal.TEN)
            .balance(java.math.BigDecimal.TEN)
            .idMgmtAccount("ACC123")
            .build();
        when(transactionOutputPort.updateTransaction(transaction)).thenReturn(transaction);
        Transaction result = transactionService.updateTransaction(transaction);
        assertNotNull(result);
        verify(transactionOutputPort).updateTransaction(transaction);
    }

    @Test
    void testDeleteTransaction() {
        doNothing().when(transactionOutputPort).deleteTransaction("1");
        transactionService.deleteTransaction("1");
        verify(transactionOutputPort).deleteTransaction("1");
    }

    @Test
    void testEditTransaction() {
        Transaction transaction = Transaction.builder()
            .transactionId("1")
            .transactionDate(java.time.LocalDate.now())
            .transactionType("DEP")
            .amount(java.math.BigDecimal.TEN)
            .balance(java.math.BigDecimal.TEN)
            .idMgmtAccount("ACC123")
            .build();
        when(transactionOutputPort.editTransaction(transaction)).thenReturn(transaction);
        Transaction result = transactionService.editTransaction(transaction);
        assertNotNull(result);
        verify(transactionOutputPort).editTransaction(transaction);
    }
}
