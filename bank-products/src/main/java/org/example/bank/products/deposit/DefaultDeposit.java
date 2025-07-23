package org.example.bank.products.deposit;

import org.example.bank.products.Currency;

import java.math.BigDecimal;

/*
Реализация вклада обыкновенного
 */
public class DefaultDeposit extends AbstractDeposit {

    public DefaultDeposit(String name, BigDecimal balance, Currency currency) {
        super(name, balance, currency);
        isClosed = false;
    }

    @Override
    public void addMoney(BigDecimal sum) {
        if (!isClosed) {
            balance = balance.add(sum);
        } else throw new IllegalArgumentException("Вклад уже закрыт");
    }

    @Override
    public BigDecimal close() {
        if (!isClosed) {
            isClosed = true;
            return getBalance();
        } else throw new IllegalArgumentException("Вклад уже закрыт");
    }
}
