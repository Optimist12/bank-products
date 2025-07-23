package org.example.bank.products.cards;

import java.math.BigDecimal;

/*
Интерфейс Card содержит методы для карт
 */
public interface Card {

    BigDecimal getBalance();
    void increaseBalance(BigDecimal sum);
    void decreaseBalance(BigDecimal sum);
}
