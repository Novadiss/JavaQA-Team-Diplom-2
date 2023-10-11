package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditAccountTest {

    @Test
    public void shouldAddToPositiveBalance() { //пополнение баланса при пустом изначальном балансе и нормальных значениях кредитного лимита и рейтинга
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.add(3_000);

        Assertions.assertEquals(3_000, account.getBalance());
    }

    @Test
    public void shouldAddToNegativeBalance() {//пополнение баланса на отрицательное значение при пустом изначальном балансе и нормальных значениях кредитного лимита и рейтинга
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.add(-30_000);

        Assertions.assertEquals(0, account.getBalance());
    }

    @Test
    public void shouldAddToZeroBalance() {//пополнение баланса на нулевое значение при пустом изначальном балансе и нормальных значениях кредитного лимита и рейтинга
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.add(0);

        Assertions.assertEquals(0, account.getBalance());
    }

    @Test // не работает
    public void testInitialBalanceWithPositiveValues() {//пополнение баланса при наличии положительного изначального баланса и нормальных значениях кредитного лимита и рейтинга
        CreditAccount account = new CreditAccount(
                1000,
                5_000,
                15
        );

        account.add(3_000);

        Assertions.assertEquals(4_000, account.getBalance());
    }

    @Test
    public void testInitialBalanceWithNegativeValues() {// проверка что отрицательный изначальный баланс быть не может
        CreditAccount account = new CreditAccount(
                -10000,
                5_000,
                15
        );

        account.add(3_000);

        Assertions.assertEquals(3000, account.getBalance());
    }

    @Test
    public void testCreditLimitWithZeroValues() {// проверка что кредитный лимит может быть нулевым
        CreditAccount account = new CreditAccount(
                0,
                0,
                15
        );

        Assertions.assertEquals(0, account.getCreditLimit());
    }

    @Test // не прошел
    public void testCreditLimitWithNegativeValues() {// проверка что кредитный лимит не может быть отрицательным
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    new CreditAccount(0, -5_0000, 15);
                }
        );
    }

    @Test // не прошел
    public void testRateWithZeroValues() {// проверка ставка кредитования может быть равна 0
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    new CreditAccount(0, 0, 15);
                }
        );
    }

    @Test
    public void testRateWithNegativeValues() {// проверка ставка кредитования не может быть отрицательной
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    new CreditAccount(0, 5_0000, -15);
                }
        );
    }

    @Test //не прошел
    public void shouldPayPositiveBalance() { //проверка платежа с нормальными исходными данными
        CreditAccount account = new CreditAccount(
                5_000,
                5_000,
                15
        );

        account.pay(3_000);

        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void shouldNegativePay() { //проверка отрицательного платежа 
        CreditAccount account = new CreditAccount(
                5_000,
                5_000,
                15
        );

        account.pay(-3_000);

        Assertions.assertEquals(5_000, account.getBalance());
    }

    @Test //не прошел
    public void shouldPayMoreThenCreditLimit() { //проверка платежа больше кредитного лимита
        CreditAccount account = new CreditAccount(
                2_000,
                500,
                15
        );

        account.pay(3_000);

        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void shouldYearChangeThenNegativeBalance() { //проверка начисления кпрцентов по кредиту на отрицательный баланс
        CreditAccount account = new CreditAccount(
                0_000,
                500,
                15
        );

        account.pay(200);

        Assertions.assertEquals(-30, account.yearChange());
    }

    @Test //не прошел
    public void shouldYearChangeThenPositiveBalance() { //проверка что при положительном балансе проценты по кредиту не начисляются
        CreditAccount account = new CreditAccount(
                0_000,
                500,
                15
        );

        account.add(3_000);

        Assertions.assertEquals(0, account.yearChange());
    }

    @Test
    public void shouldYearChangeThenZeroBalance() { //проверка что при нулевом балансе проценты по кредиту не начисляются
        CreditAccount account = new CreditAccount(
                0_000,
                500,
                15
        );


        Assertions.assertEquals(0, account.yearChange());
    }
}
