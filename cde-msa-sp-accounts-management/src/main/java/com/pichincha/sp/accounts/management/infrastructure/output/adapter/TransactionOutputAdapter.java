package com.pichincha.sp.accounts.management.infrastructure.output.adapter;

import com.pichincha.sp.accounts.management.application.output.port.TransactionOutputPort;
import com.pichincha.sp.accounts.management.domain.Transaction;
import com.pichincha.sp.accounts.management.domain.enums.TransactionTypesValues;
import com.pichincha.sp.accounts.management.domain.strategy.BalanceStrategy;
import com.pichincha.sp.accounts.management.infrastructure.exception.NotFoundException;
import com.pichincha.sp.accounts.management.infrastructure.output.repository.database.MgmtAccountRepository;
import com.pichincha.sp.accounts.management.infrastructure.output.repository.database.MgmtTransactionRepository;
import com.pichincha.sp.accounts.management.infrastructure.output.repository.entity.MgmtAccount;
import com.pichincha.sp.accounts.management.infrastructure.output.repository.entity.MgmtTransaction;
import com.pichincha.sp.accounts.management.domain.strategy.CreditStrategy;
import com.pichincha.sp.accounts.management.domain.strategy.DebitStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

import static com.pichincha.sp.accounts.management.domain.util.UtilClass.validateBean;

@Repository
@RequiredArgsConstructor
public class TransactionOutputAdapter implements TransactionOutputPort {

    private final MgmtTransactionRepository mgmtTransactionRepository;
    private final MgmtAccountRepository mgmtAccountRepository;

    @Override
    public List<Transaction> getTransactionById(String idMgmtAccount, String transactionDate) {
        List<MgmtTransaction> transactionRetrieved =
                mgmtTransactionRepository.findRecentTransactionsByIdMgmtAccount(
                        Integer.parseInt(idMgmtAccount),
                        LocalDate.parse(transactionDate));
        if (transactionRetrieved == null || transactionRetrieved.isEmpty()) {
            throw new NotFoundException("Not found");
        }
        return transactionRetrieved.stream()
                .map(t -> Transaction.builder()
                        .transactionId(t.getTransactionId().toString())
                        .transactionDate(t.getTransactionDate())
                        .transactionType(t.getTransactionType())
                        .amount(t.getAmount())
                        .balance(t.getBalance())
                        .idMgmtAccount(t.getIdMgmtAccount().toString())
                        .build())
                .collect(java.util.stream.Collectors.toList());

    }

    @Override
    public Transaction createTransaction(Transaction transaction) {
        validateBean(transaction);

        MgmtAccount accountInitial = mgmtAccountRepository.findByidAccount(Integer.parseInt(transaction.getIdMgmtAccount()));
        if (accountInitial == null) {
            throw new NotFoundException("Account not found");
        }
        if (transaction.getTransactionType().equals(TransactionTypesValues.CREDITO.getValue())) {
            BalanceStrategy strategy = new CreditStrategy();
            accountInitial.setInitialBalance(strategy.calculateBalance(accountInitial.getInitialBalance(), transaction.getAmount()));
            mgmtAccountRepository.save(accountInitial);
        } else if (transaction.getTransactionType().equals(TransactionTypesValues.DEBITO.getValue())) {
            BalanceStrategy strategy = new DebitStrategy();
            accountInitial.setInitialBalance(strategy.calculateBalance(accountInitial.getInitialBalance(), transaction.getAmount()));
            mgmtAccountRepository.save(accountInitial);
        } else {
            throw new IllegalArgumentException("Tipo de Transaccion Invalido");
        }

        MgmtAccount accountFinal = mgmtAccountRepository.findByidAccount(Integer.parseInt(transaction.getIdMgmtAccount()));
        MgmtTransaction entity = new MgmtTransaction(
                null,
        LocalDate.now(),
        transaction.getTransactionType(),
        transaction.getAmount(),
        accountFinal.getInitialBalance(),
        Integer.parseInt(transaction.getIdMgmtAccount())
        );

        MgmtTransaction savedTransaction = mgmtTransactionRepository.save(entity);
        return Transaction.builder()
                .transactionId(savedTransaction.getTransactionId().toString())
                .transactionDate(savedTransaction.getTransactionDate())
                .transactionType(savedTransaction.getTransactionType())
                .amount(savedTransaction.getAmount())
                .balance(savedTransaction.getBalance())
                .idMgmtAccount(savedTransaction.getIdMgmtAccount().toString())
                .build();
    }

    @Override
    public void deleteTransaction(String transactionId) {
        mgmtTransactionRepository.deleteById(Integer.parseInt(transactionId));
    }

    @Override
    public Transaction updateTransaction(Transaction transaction) {
        MgmtTransaction entity = mgmtTransactionRepository.findByTransactionId(Integer.parseInt(transaction.getTransactionId()));
        if (entity == null) {
            throw new NotFoundException("Not found");
        }
        entity.setTransactionDate(transaction.getTransactionDate());
        entity.setTransactionType(transaction.getTransactionType());
        entity.setAmount(transaction.getAmount());
        entity.setBalance(transaction.getBalance());

        MgmtTransaction updatedEntity = mgmtTransactionRepository.save(entity);
        return Transaction.builder()
                .transactionId(updatedEntity.getTransactionId().toString())
                .transactionDate(updatedEntity.getTransactionDate())
                .transactionType(updatedEntity.getTransactionType())
                .amount(updatedEntity.getAmount())
                .balance(updatedEntity.getBalance())
                .idMgmtAccount(updatedEntity.getIdMgmtAccount().toString())
                .build();
    }

    @Override
    public Transaction editTransaction(Transaction transaction) {
        MgmtTransaction entity = mgmtTransactionRepository.findByTransactionId(Integer.parseInt(transaction.getTransactionId()));
        if (entity == null) {
            throw new NotFoundException("Not found");
        }
        if (transaction.getTransactionDate() != null) entity.setTransactionDate(transaction.getTransactionDate());
        if (transaction.getTransactionType() != null) entity.setTransactionType(transaction.getTransactionType());
        if (transaction.getAmount() != null) entity.setAmount(transaction.getAmount());
        if (transaction.getBalance() != null) entity.setBalance(transaction.getBalance());

        MgmtTransaction updatedEntity = mgmtTransactionRepository.save(entity);
        return Transaction.builder()
                .transactionId(updatedEntity.getTransactionId().toString())
                .transactionDate(updatedEntity.getTransactionDate())
                .transactionType(updatedEntity.getTransactionType())
                .amount(updatedEntity.getAmount())
                .balance(updatedEntity.getBalance())
                .idMgmtAccount(updatedEntity.getIdMgmtAccount().toString())
                .build();
    }
}
