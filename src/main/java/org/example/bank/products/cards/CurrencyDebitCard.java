package org.example.bank.products.cards;

import org.example.bank.products.Currency;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.example.bank.products.Currency.RUB;
/*
Реализация дебетовой валютной карты
 */
public class CurrencyDebitCard extends DebitCard {

    public CurrencyDebitCard(String name, BigDecimal balance, Currency currency) {
        super(name, balance, currency);
        checkCurrency(currency);
    }

    public void increaseBalance(BigDecimal sum, Currency currencySum) {
            balance = balance.add(convertCurrency(sum, currency, currencySum));
    }

    public void decreaseBalance(BigDecimal sum, Currency currencySum) {
        if (balance.subtract(convertCurrency(sum, currency, currencySum)).compareTo(BigDecimal.ZERO) > 0) {
            balance = balance.subtract(sum);
        } else throw new IllegalArgumentException("Недостаточно средств");
    }

    // Необходима реализация конвертации в валюту карты
    private BigDecimal convertCurrency(BigDecimal sum, Currency currencyCard, Currency currencySum) {
        return sum.divide(new BigDecimal(2), 2, RoundingMode.HALF_UP);
    }

    private void checkCurrency(Currency currency) {
        if (RUB.equals(currency))
            throw new IllegalArgumentException("Валюта вклада не может быть RUB");
    }
}
