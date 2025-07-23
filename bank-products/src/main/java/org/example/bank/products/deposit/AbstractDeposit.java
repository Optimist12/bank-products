package org.example.bank.products.deposit;


import org.example.bank.products.AbstractBankProduct;
import org.example.bank.products.Currency;

import java.math.BigDecimal;
/*
Абстрактный класс для общих полей и общих реализаций разных типов вкладов
*/
public abstract class AbstractDeposit extends AbstractBankProduct implements Deposit {

    public AbstractDeposit(String name, BigDecimal balance, Currency currency) {
        super(name, balance, currency);
    }

    protected Boolean isClosed;

    @Override
    public BigDecimal getBalance() {
        return balance;
    }

}
