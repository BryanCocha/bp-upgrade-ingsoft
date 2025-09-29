package com.pichincha.sp.accounts.management.infrastructure.input.adapter.rest.impl;

import com.pichincha.sp.accounts.management.application.input.port.TransactionInputPort;
import com.pichincha.sp.accounts.management.infrastructure.input.adapter.rest.TransactionsApi;
import com.pichincha.sp.accounts.management.infrastructure.input.adapter.rest.mapper.MapperTransactionInput;
import com.pichincha.sp.accounts.management.infrastructure.input.adapter.rest.mapper.MapperTransactionResponse;
import com.pichincha.sp.accounts.management.infrastructure.input.adapter.rest.models.Transaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author bryancocha@gmail.com
 * @class_name TransactionsController.java
 * @class_description
 * @create_date 26/09/2025 Copyright 2022.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class TransactionsController implements TransactionsApi {
    private final TransactionInputPort transactionInputPort;
    private final MapperTransactionInput mapperTransactionInput;
    private final MapperTransactionResponse mapperTransactionResponse;

    @Override
    public ResponseEntity<Transaction> createTransaction(Transaction transaction) {
        com.pichincha.sp.accounts.management.domain.Transaction transactionRequest = mapperTransactionInput.toCreateTransactionInput(transaction);
        var createdTransaction = transactionInputPort.createTransaction(transactionRequest);
        Transaction transactionResponse = mapperTransactionResponse.toCreateTransactionResponse(createdTransaction);
        return new ResponseEntity<>(transactionResponse, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteTransactionById(String transactionId) {
        transactionInputPort.deleteTransaction(transactionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<List<Transaction>> getTransactionById(String idMgmtAccount, String transactionDate) {
        var getTransaction = transactionInputPort.getTransactionById(idMgmtAccount, transactionDate);
        List<Transaction> transactionResponses = getTransaction.stream()
                .map(mapperTransactionResponse::toCreateTransactionResponse)
                .collect(java.util.stream.Collectors.toList());
        return new ResponseEntity<>(transactionResponses, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<Transaction> patchTransactionById(Transaction transaction) {
        var transactionRequest = mapperTransactionInput.toUpgradeTransactionInput(transaction);
        var upgradedTransaction = transactionInputPort.editTransaction(transactionRequest);
        Transaction transactionResponse = mapperTransactionResponse.toUpgradeTransactionResponse(upgradedTransaction);
        return new ResponseEntity<>(transactionResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Transaction> updateTransactionById(Transaction transaction) {
        var transactionRequest = mapperTransactionInput.toUpgradeTransactionInput(transaction);
        var upgradedTransaction = transactionInputPort.updateTransaction(transactionRequest);
        Transaction transactionResponse = mapperTransactionResponse.toUpgradeTransactionResponse(upgradedTransaction);
        return new ResponseEntity<>(transactionResponse, HttpStatus.OK);
    }
}
