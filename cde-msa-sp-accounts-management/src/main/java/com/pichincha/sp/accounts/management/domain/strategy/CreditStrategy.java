package com.pichincha.sp.accounts.management.domain.strategy;

import java.math.BigDecimal;

/**
 * @author bryancocha@gmail.com
 * @class_name CreditStrategy.java
 * @class_description
 * @create_date 28/09/2025 Copyright 2022.
 */
public class CreditStrategy implements BalanceStrategy{
    @Override
    public BigDecimal calculateBalance(BigDecimal currentBalance, BigDecimal amount) {
        return currentBalance.add(amount);
    }
}
