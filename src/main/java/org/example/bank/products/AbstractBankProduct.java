package org.example.bank.products;

import lombok.Getter;

import java.math.BigDecimal;

import static java.util.Objects.*;

public class AbstractBankProduct {
    public AbstractBankProduct(String name, BigDecimal balance, Currency currency) {
        this.name = requireNonNull(name);
        this.balance = requireNonNull(balance);
        this.currency = requireNonNull(currency);
    }

    @Getter
    protected String name;
    protected BigDecimal balance;
    @Getter
    protected Currency currency;
}
