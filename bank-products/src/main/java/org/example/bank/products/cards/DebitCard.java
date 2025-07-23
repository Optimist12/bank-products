package org.example.bank.products.cards;


import org.example.bank.products.Currency;

import java.math.BigDecimal;
/*
Реализация дебетовой карты
 */
public class DebitCard extends AbstractCard {

    public DebitCard(String name, BigDecimal balance, Currency currency) {
        super(name, balance, currency);
    }

    @Override
    public BigDecimal getBalance() {
        return balance;
    }

    @Override
    public void increaseBalance(BigDecimal sum) {
        balance = balance.add(sum);
    }

    @Override
    public void decreaseBalance(BigDecimal sum) {
        if (balance.subtract(sum).compareTo(BigDecimal.ZERO) > 0) {
            balance = balance.subtract(sum);
        }
        else throw new IllegalArgumentException("Недостаточно средств");
    }
}
