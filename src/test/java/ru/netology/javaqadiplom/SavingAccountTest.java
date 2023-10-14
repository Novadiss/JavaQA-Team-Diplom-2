package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SavingAccountTest {
    @Test //ok
    public void shouldSaveAccount() { // создает новый сбер. счет с заданными параметрами
        SavingAccount account = new SavingAccount(
                5_000,
                1_000,
                15_000,
                4
        );

        Assertions.assertEquals(5_000, account.getBalance());
        Assertions.assertEquals(1_000, account.getMinBalance());
        Assertions.assertEquals(15_000, account.getMaxBalance());
        Assertions.assertEquals(4, account.getRate());
    }

    @Test // ok
    public void shouldThrowExceptionIfNegativeRate() { // выброс исключения, если ставка отрицательная
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    5_000,
                    1_000,
                    15_000,
                    -3);
        });
    }

    @Test // ok
    public void shouldThrowExceptionIfMinBalanceMoreMaxBalance() { // выброс исключения, если минимальный баланс больше максимального
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    5_000,
                    18_000,
                    15_000,
                    3);
        });
    }

    @Test // ок
    public void shouldThrowExceptionIfBalanceMoreMaxBalance() { // выброс исключения, если начальный баланс больше максимального
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    20_000,
                    1_000,
                    15_000,
                    3);
        });
    }

    @Test // ок
    public void shouldThrowExceptionIfBalanceLessMinBalance() { // выброс исключения, если начальный баланс меньше минимального
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    1_000,
                    5_000,
                    15_000,
                    3);
        });
    }

    @Test // ok
    public void shouldPayAboveMinBalance() { // баланс должен уменьшиться на сумму покупки, но остаться выше мин. баланса
        SavingAccount account = new SavingAccount(
                8_000,
                1_000,
                10_000,
                5
        );
        account.pay(3_000);
        Assertions.assertEquals(8_000 - 3_000, account.getBalance());
    }

    @Test // ok
    public void shouldPayToMinBalance() { // баланс должен уменьшиться до размера мин. баланса
        SavingAccount account = new SavingAccount(
                8_000,
                1_000,
                10_000,
                5
        );
        account.pay(7_000);
        Assertions.assertEquals(8_000 - 7_000, account.getBalance());
    }

    @Test // ок
    public void shouldNotPayLessThanMinBalance() { // баланс не должен измениться, т.к. он может стать меньше минимального
        SavingAccount account = new SavingAccount(
                8_000,
                1_000,
                10_000,
                5
        );
        account.pay(8_000);
        Assertions.assertEquals(8_000, account.getBalance());
    }

    @Test // ок
    public void shouldNotPayLessThanZero() { // баланс не должен измениться, т.к. он может стать отрицательным
        SavingAccount account = new SavingAccount(
                8_000,
                1_000,
                10_000,
                5
        );
        account.pay(9_000);
        Assertions.assertEquals(8_000, account.getBalance());
    }

    @Test // ok
    public void shouldNotPayIfAmountZero() { // баланс не должен измениться, т.к. платеж нулевой
        SavingAccount account = new SavingAccount(
                8_000,
                1_000,
                10_000,
                5
        );
        account.pay(0);
        Assertions.assertEquals(8_000, account.getBalance());
    }

    @Test // ok
    public void shouldNotPayIfAmountNegative() { // баланс не должен измениться, т.к. платеж отрицательный
        SavingAccount account = new SavingAccount(
                8_000,
                1_000,
                10_000,
                5
        );
        account.pay(-1_000);
        Assertions.assertEquals(8_000, account.getBalance());
    }

    @Test // ok
    public void shouldTrueIfPayAboveMinBalance() { // должен вернуть true,если платеж успешный
        SavingAccount account = new SavingAccount(
                8_000,
                1_000,
                10_000,
                5
        );
        boolean actual = account.pay(3_000);
        Assertions.assertTrue(actual);
    }

    @Test // ок
    public void shouldTrueIfPayToMinBalance() { // должен вернуть true,если платеж успешный
        SavingAccount account = new SavingAccount(
                8_000,
                1_000,
                10_000,
                5
        );
        boolean actual = account.pay(7_000);
        Assertions.assertTrue(actual);
    }

    @Test // ok
    public void shouldFalseIfPayLessThanMinBalance() { // должен вернуть false,если платеж не прошел (текущий баланс меньше минимального)
        SavingAccount account = new SavingAccount(
                8_000,
                1_000,
                10_000,
                5
        );
        boolean actual = account.pay(8_000);
        Assertions.assertFalse(actual);
    }

    @Test // ok
    public void shouldFalseIfPayLessThanZero() { // должен вернуть false,если платеж не прошел (баланс уходит в минус)
        SavingAccount account = new SavingAccount(
                8_000,
                1_000,
                10_000,
                5
        );
        boolean actual = account.pay(9_000);
        Assertions.assertFalse(actual);
    }

    @Test // ok
    public void shouldFalseIfAmountZero() { // должен вернуть false,если платеж равен нулю
        SavingAccount account = new SavingAccount(
                8_000,
                1_000,
                10_000,
                5
        );
        boolean actual = account.pay(0);
        Assertions.assertFalse(actual);
    }

    @Test // ok
    public void shouldFalseIfAmountNegative() { // должен вернуть false,если платеж отрицательный
        SavingAccount account = new SavingAccount(
                8_000,
                1_000,
                10_000,
                5
        );
        boolean actual = account.pay(-1_000);
        Assertions.assertFalse(actual);
    }

    @Test // ок
    public void shouldAddLessThanMaxBalance() { // баланс должен увеличиться на сумму пополнения, но не больше максимального
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );
        account.add(3_000);
        Assertions.assertEquals(2_000 + 3_000, account.getBalance());
    }

    @Test // ок
    public void shouldAddToMaxBalance() { // баланс должен увеличиться на сумму пополнения и стать равным максимальному
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );
        account.add(8_000);
        Assertions.assertEquals(2_000 + 8_000, account.getBalance());
    }

    @Test // ok
    public void shouldNotAddIfMoreThanMaxBalance() { // баланс не должен измениться, т.к. станет больше максимального
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );
        account.add(10_000);
        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test // ok
    public void shouldNotAddBecauseAmountNegative() { // баланс не должен измениться, т.к. платеж отрицательный
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );
        account.add(-3_000);
        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test // ok
    public void shouldNotAddBecauseAmountZero() { // баланс не должен измениться, т.к. платеж равен нулю
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );
        account.add(0);
        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test // ok
    public void shouldTrueWhenAddLessThanMaxBalance() { // должен вернуть true когда пополнение прошло успешно
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );
        boolean actual = account.add(3_000);
        Assertions.assertTrue(actual);
    }

    @Test // не прошед
    public void shouldTrueIfAddToMaxBalance() { // должен вернуть true когда пополнение прошло успешно
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );
        boolean actual = account.add(8_000);
        Assertions.assertTrue(actual);
    }

    @Test // ok
    public void shouldFalseIfAddMoreThanMaxBalance() { // должен вернуть false при пополнении, если баланс становится больше максимального
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );
        boolean actual = account.add(10_000);
        Assertions.assertFalse(actual);
    }

    @Test // ok
    public void shouldFalseIfAddAmountZero() { //  должен вернуть false если платеж равен нулю
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );
        boolean actual = account.add(0);
        Assertions.assertFalse(actual);
    }

    @Test // ok
    public void shouldFalseIfAddAmountNegative() { //  должен вернуть false если платеж отрицательный
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );
        boolean actual = account.add(-1_000);
        Assertions.assertFalse(actual);
    }

    @Test // ok
    public void shouldYearChange() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        int expected = 100;
        int actual = account.yearChange();
        Assertions.assertEquals(expected, actual);
    }
}
