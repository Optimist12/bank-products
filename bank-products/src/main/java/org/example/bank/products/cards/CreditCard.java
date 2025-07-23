package org.example.bank.products.cards;


import lombok.Getter;
import org.example.bank.products.Currency;

import java.math.BigDecimal;

/*
Реализация кредитной карты
 */
public class CreditCard extends AbstractCard {

    private BigDecimal creditLimit;
    @Getter
    private BigDecimal interestRate;

    public CreditCard(String name, BigDecimal balance, Currency currency, BigDecimal interestRate, BigDecimal creditLimit) {
        super(name, balance, currency);
        setInterestRate(interestRate);
        setCreditLimit(creditLimit);
    }

    public BigDecimal getDebtAmount() {
        if (balance.compareTo(BigDecimal.ZERO) > 0)
            return BigDecimal.ZERO;
        return balance.abs();
    }

    @Override
    public void increaseBalance(BigDecimal sum) {
        balance = balance.add(sum);
    }

    @Override
    public void decreaseBalance(BigDecimal sum) {
        if (creditLimit.add(balance).subtract(sum).compareTo(BigDecimal.ZERO) > 0) {
            balance = balance.subtract(sum);
        } else throw new IllegalArgumentException("Превышен кредитный лимит");
    }

    private void setCreditLimit(BigDecimal creditLimit) {
        if (creditLimit == null || creditLimit.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Кредитный лимит должен быть положительным числом");
        }
        this.creditLimit = creditLimit;
    }

    private void setInterestRate(BigDecimal interestRate) {
        if (interestRate == null || interestRate.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Процентная ставка не может быть отрицательной");
        }
        if (interestRate.compareTo(BigDecimal.valueOf(100)) > 0) {
            throw new IllegalArgumentException("Процентная ставка не может превышать 100%");
        }
        this.interestRate = interestRate;
    }
}

