package com.pichincha.sp.accounts.management.domain.strategy;

import java.math.BigDecimal;

/**
 * @author bryancocha@gmail.com
 * @class_name DebitStrategy.java
 * @class_description
 * @create_date 28/09/2025 Copyright 2022.
 */
public class DebitStrategy implements BalanceStrategy{
    @Override
    public BigDecimal calculateBalance(BigDecimal currentBalance, BigDecimal amount) {
        if (currentBalance.compareTo(amount) < 0) {
            throw new IllegalArgumentException("Saldo no disponible");
        }
        return currentBalance.subtract(amount);
    }
}
