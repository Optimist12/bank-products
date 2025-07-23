package org.example.bank.products.deposit;

import java.math.BigDecimal;
/*
Интерфейс Deposit содержит методы для вкладов
*/
public interface Deposit {
    BigDecimal getBalance();
    void addMoney(BigDecimal sum);
    BigDecimal close();
}
