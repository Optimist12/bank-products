package org.example.bank.products.cards;


import org.example.bank.products.AbstractBankProduct;
import org.example.bank.products.Currency;
import java.math.BigDecimal;
/*
Абстрактный класс для общих полей и общих реализаций разных типов карт
Для добавления новых видов карт необходимо наследовать этот класс и имплементировать методы интерфейса Card
*/
public abstract class AbstractCard extends AbstractBankProduct implements Card {

    public AbstractCard(String name, BigDecimal balance, Currency currency) {
        super(name, balance, currency);
    }

    @Override
    public BigDecimal getBalance() {
        return balance;
    }
}
