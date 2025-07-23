package bank.products;

import org.example.bank.products.Currency;
import org.example.bank.products.cards.CreditCard;
import org.example.bank.products.cards.CurrencyDebitCard;
import org.example.bank.products.cards.DebitCard;
import org.example.bank.products.deposit.DefaultDeposit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.example.bank.products.Currency.RUB;
import static org.example.bank.products.Currency.USD;

public class BankProductsTests {
    public CreditCard defaultCreditCard;
    public DebitCard defaultDebitCard;
    public DefaultDeposit defaultDeposit;

    @BeforeEach
    void createCard(){
        defaultCreditCard = new CreditCard("test", new BigDecimal(100), RUB, new BigDecimal(1), new BigDecimal(100));
        defaultDebitCard = new DebitCard("test", new BigDecimal(100), RUB);
        defaultDeposit = new DefaultDeposit("test", new BigDecimal(100), RUB);
    }

    @Test
    void checkFieldsCreditCard() {
        assertThat(defaultCreditCard.getName()).isEqualTo("test");
        assertThat(defaultCreditCard.getCurrency()).isEqualTo(RUB);
        assertThat(defaultCreditCard.getBalance()).isEqualTo(new BigDecimal(100));
        assertThat(defaultCreditCard.getInterestRate()).isEqualTo(new BigDecimal(1));
    }

    @Test
    void checkIncreaseCreditCardBalance() {
        CreditCard card = new CreditCard("test", new BigDecimal(100), RUB, new BigDecimal(1), new BigDecimal(100));
        card.increaseBalance(new BigDecimal(1));
        assertThat(card.getBalance()).isEqualTo(new BigDecimal(101));
    }

    @Test
    void checkDecreaseCreditCardBalance() {
        CreditCard card = new CreditCard("test", new BigDecimal(100), RUB, new BigDecimal(1), new BigDecimal(100));
        card.decreaseBalance(new BigDecimal(1));
        assertThat(card.getBalance()).isEqualTo(new BigDecimal(99));
    }

    @Test
    void checkNoDeptAmount() {
        CreditCard card = new CreditCard("test", new BigDecimal(100), RUB, new BigDecimal(1), new BigDecimal(100));
        assertThat(card.getDebtAmount()).isEqualTo(BigDecimal.ZERO);
    }
    @Test
    void checkPositiveDeptAmount() {
        CreditCard card = new CreditCard("test", new BigDecimal(-100), RUB, new BigDecimal(1), new BigDecimal(100));
        assertThat(card.getDebtAmount()).isEqualTo(new BigDecimal(100));
    }

    @Test
    void checkDecreaseCreditCardBalanceCreditError() {
        CreditCard card = new CreditCard("test", new BigDecimal(100), RUB, new BigDecimal(1), new BigDecimal(100));
        assertThatIllegalArgumentException().isThrownBy(() -> card.decreaseBalance(new BigDecimal(1000)));
    }

    @Test
    void checkNotNegativeInterestRate() {
        assertThatIllegalArgumentException().isThrownBy(() -> new CreditCard("test", new BigDecimal(100), RUB, new BigDecimal(-1), new BigDecimal(100)));
    }

    @Test
    void checkNotNegativeCreditLimit() {
        assertThatIllegalArgumentException().isThrownBy(() -> new CreditCard("test", new BigDecimal(100), RUB, new BigDecimal(1), new BigDecimal(-1100)));
    }

    @Test
    void checkIncreaseBalanceDebitCard() {
        DebitCard card = new DebitCard("test", new BigDecimal(100), RUB);
        card.increaseBalance(new BigDecimal(1));
        assertThat(card.getBalance()).isEqualTo(new BigDecimal(101));
    }

    @Test
    void checkDecreaseBalanceDebitCard() {
        DebitCard card = new DebitCard("test", new BigDecimal(100), RUB);
        card.increaseBalance(new BigDecimal(1));
        assertThat(card.getBalance()).isEqualTo(new BigDecimal(101));
    }

    @Test
    void checkDecreaseNegativeBalanceDebitCard() {
        DebitCard card = new DebitCard("test", new BigDecimal(100), RUB);
        assertThatIllegalArgumentException().isThrownBy(() -> card.decreaseBalance(new BigDecimal(1000)));
    }

    @Test
    void checkFieldsDebitCard() {
        assertThat(defaultDebitCard.getName()).isEqualTo("test");
        assertThat(defaultDebitCard.getCurrency()).isEqualTo(RUB);
        assertThat(defaultDebitCard.getBalance()).isEqualTo(new BigDecimal(100));
    }

    @Test
    void checkDepositFields() {
        assertThat(defaultDeposit.getName()).isEqualTo("test");
        assertThat(defaultDeposit.getCurrency()).isEqualTo(RUB);
        assertThat(defaultDeposit.getBalance()).isEqualTo(new BigDecimal(100));
    }

    @Test
    void checkCloseDeposit() {
        BigDecimal finalMoney = defaultDeposit.close();
        assertThat(defaultDeposit.getBalance()).isEqualTo(finalMoney);
    }

    @Test
    void checkAddMoneyToCloseDeposit() {
        defaultDeposit.close();
        assertThatIllegalArgumentException().isThrownBy(() -> defaultDeposit.addMoney(new BigDecimal(1000)));
    }

    @Test
    void checkCloseDepositNoClosedSecondTry() {
        defaultDeposit.close();
        assertThatIllegalArgumentException().isThrownBy(() -> defaultDeposit.close());
    }

    @Test
    void checkAddMoneyToDeposit() {
        defaultDeposit.addMoney(new BigDecimal(1000));
        assertThat(defaultDeposit.getBalance()).isEqualTo(new BigDecimal(1100));
    }

    @Test
    void checkIncreaseBalanceCurrencyDebitCard() {
        CurrencyDebitCard card = new CurrencyDebitCard("test", new BigDecimal(100), USD);
        card.increaseBalance(new BigDecimal(1), RUB);
        assertThat(card.getBalance()).isEqualTo(new BigDecimal("100.50"));
    }

    @Test
    void checkUnsupportedCurrencyDebitCard() {
        assertThatIllegalArgumentException().isThrownBy(() -> new CurrencyDebitCard("test", new BigDecimal(100), RUB));
    }

    @Test
    void checkDecreaseBalanceCurrencyDebitCard() {
        CurrencyDebitCard card = new CurrencyDebitCard("test", new BigDecimal(100), USD);
        card.decreaseBalance(new BigDecimal(1), RUB);
        assertThat(card.getBalance()).isEqualTo(new BigDecimal("99"));
    }

    @Test
    void checkNullCreditCardName() {
        assertThatNullPointerException().isThrownBy(() -> new CreditCard(null, new BigDecimal(100), RUB, new BigDecimal(1), new BigDecimal(-1100)));
    }
    @Test
    void checkNullCreditCardBalance() {
        assertThatNullPointerException().isThrownBy(() -> new CreditCard("test", null, RUB, new BigDecimal(1), new BigDecimal(-1100)));
    }
    @Test
    void checkNullCreditCardCurrency() {
        assertThatNullPointerException().isThrownBy(() -> new CreditCard("test", new BigDecimal(1), null, new BigDecimal(1), new BigDecimal(-1100)));
    }
}
