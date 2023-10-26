package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditAccountTest {

    @Test
    public void testInitialBalanceWithNegativeValues() {// проверка что отрицательный изначальный баланс быть не может
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    new CreditAccount(-1_000, 5_0000, 15);
                }
        );
    }

    @Test
    public void testInitialBalanceWithZeroValues() { //проверка что нулевой изначальный баланс возможен
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        Assertions.assertEquals(0, account.getBalance());
    }

    @Test
    public void testInitialBalanceWithPositiveValues() { //проверка что изначальный баланс больше нуля возможен
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );

        Assertions.assertEquals(1_000, account.getBalance());
    }

    @Test
    public void testCreditLimitWithNegativeValues() {// проверка что кредитный лимит не может быть отрицательным
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    new CreditAccount(0, -5_0000, 15);
                }
        );
    }

    @Test // не прошел
    public void testCreditLimitWithZeroValues() {// проверка что кредитный лимит может быть равен 0
        CreditAccount account = new CreditAccount(
                1_000,
                0,
                15
        );

        Assertions.assertEquals(0, account.getCreditLimit());
    }

    @Test
    public void testCreditLimitWithPositiveValues() {// проверка что кредитный лимит может быть положительным
        CreditAccount account = new CreditAccount(
                1_000,
                1_000,
                15
        );

        Assertions.assertEquals(1_000, account.getCreditLimit());
    }

    @Test
    public void testRateWithNegativeValues() {// проверка ставка кредитования не может быть отрицательной
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    new CreditAccount(0, 5_0000, -15);
                }
        );
    }

    @Test
    public void testRateWithZeroValues() {// проверка ставка кредитования может быть равна 0
        CreditAccount account = new CreditAccount(
                1_000,
                1_000,
                0
        );

        Assertions.assertEquals(0, account.getRate());
    }

    @Test
    public void testRateWithPositiveValues() {// проверка что cтавка кредитования может быть положительной
        CreditAccount account = new CreditAccount(
                1_000,
                1_000,
                15
        );

        Assertions.assertEquals(15, account.getRate());
    }

    @Test
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
    public void shouldZeroPay() { //проверка что нулевой платеж невозможен с нормальными исходными данными
        CreditAccount account = new CreditAccount(
                5_000,
                5_000,
                15
        );

        boolean actual = account.pay(0);

        Assertions.assertFalse(actual);
    }

    @Test
    public void shouldNegativePay() { //проверка что отрицательный платеж невозможен с нормальными исходными данными
        CreditAccount account = new CreditAccount(
                5_000,
                5_000,
                15
        );

        boolean actual = account.pay(-3_000);

        Assertions.assertFalse(actual);
    }

    @Test
    public void shouldPayMoreThenCreditLimit() { //проверка что платеж за пределы кредитного лимита невозможен
        CreditAccount account = new CreditAccount(
                2_000,
                500,
                15
        );

        boolean actual = account.pay(3_000);

        Assertions.assertFalse(actual);
    }

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
    public void shouldAddToNegativeBalance() {//Проверка что пополнение на отрицательное значение невозможно
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        boolean actual = account.add(-30_000);

        Assertions.assertFalse(actual);
    }

    @Test
    public void shouldAddToZeroBalance() {//Проверка что пополнение на нулевое значение невозможно
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        boolean actual = account.add(0);

        Assertions.assertFalse(actual);
    }

    @Test
    public void testBalanceWithPositiveValues() {//пополнение баланса при наличии положительного изначального баланса и нормальных значениях кредитного лимита и рейтинга
        CreditAccount account = new CreditAccount(
                1000,
                5_000,
                15
        );

        account.add(3_000);

        Assertions.assertEquals(4_000, account.getBalance());
    }

    @Test
    public void shouldYearChangeThenNegativeBalance() { //проверка начисления кпрцентов по кредиту на отрицательный баланс
        CreditAccount account = new CreditAccount(
                0,
                500,
                15
        );

        account.pay(200);

        Assertions.assertEquals(-30, account.yearChange());
    }

    @Test
    public void shouldYearChangeThenPositiveBalance() { //проверка что при положительном балансе проценты по кредиту не начисляются
        CreditAccount account = new CreditAccount(
                0,
                500,
                15
        );

        account.add(3_000);

        Assertions.assertEquals(0, account.yearChange());
    }

    @Test
    public void shouldYearChangeThenZeroBalance() { //проверка что при нулевом балансе проценты по кредиту не начисляются
        CreditAccount account = new CreditAccount(
                0,
                500,
                15
        );

        Assertions.assertEquals(0, account.yearChange());
    }

    @Test 
    public void shouldYearChangeIfBalanceNotMultipleOf100() { //Проверка начисления процентов при балансе не кратном 100
        CreditAccount account = new CreditAccount(
                0,
                500,
                15
        );

        account.pay(160);
        int expected = -24;
        int actual = account.yearChange();
        Assertions.assertEquals(expected, actual);
    }
}
