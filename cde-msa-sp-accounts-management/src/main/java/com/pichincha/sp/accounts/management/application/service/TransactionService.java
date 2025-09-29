package com.pichincha.sp.accounts.management.application.service;

import com.pichincha.sp.accounts.management.application.input.port.TransactionInputPort;
import com.pichincha.sp.accounts.management.application.output.port.TransactionOutputPort;
import com.pichincha.sp.accounts.management.domain.Transaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author bryancocha@gmail.com
 * @class_name TransactionService.java
 * @class_description
 * @create_date 26/09/2025 Copyright 2022.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionService implements TransactionInputPort {

    private final TransactionOutputPort transactionOutputPort;

    @Override
    public List<Transaction> getTransactionById(String idMgmtAccount, String transactionDate) {
        return transactionOutputPort.getTransactionById(idMgmtAccount, transactionDate);
    }

    @Override
    public Transaction createTransaction(Transaction transaction) {
        return transactionOutputPort.createTransaction(transaction);
    }

    @Override
    public Transaction updateTransaction(Transaction transaction) {
        return transactionOutputPort.updateTransaction(transaction);
    }

    @Override
    public void deleteTransaction(String transactionId) {
        transactionOutputPort.deleteTransaction(transactionId);
    }

    @Override
    public Transaction editTransaction(Transaction transaction) {
        return transactionOutputPort.editTransaction(transaction);
    }
}
