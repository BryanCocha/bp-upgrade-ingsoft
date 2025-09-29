package com.pichincha.sp.accounts.management.application.input.port;

import com.pichincha.sp.accounts.management.domain.Transaction;

import java.util.List;

/**
 * @author bryancocha@gmail.com
 * @class_name TransactionInputPort.java
 * @class_description 
 * @create_date 26/09/2025 Copyright 2022.
 */
public interface TransactionInputPort {
    List<Transaction> getTransactionById(String idMgmtAccount, String transactionDate);
    Transaction createTransaction(Transaction transaction);
    Transaction updateTransaction(Transaction transaction);
    void deleteTransaction(String transactionId);
    Transaction editTransaction(Transaction transaction);
}
