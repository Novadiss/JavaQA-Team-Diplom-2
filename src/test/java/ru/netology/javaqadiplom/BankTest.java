package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BankTest {

    SavingAccount saving = new SavingAccount(5_000, 1_000, 15_000, 4);
    CreditAccount credit = new CreditAccount(6_000, 10_000, 8);
    Bank bank = new Bank();

    @Test
    public void shouldDecreaseSavingAndIncreaseCredit() { // должен уменьшиться сбер. счет и увеличиться кредитный

        boolean actual = bank.transfer(saving, credit, 3_000);

        Assertions.assertEquals(2_000, saving.getBalance());
        Assertions.assertEquals(9_000, credit.getBalance());
        Assertions.assertTrue(actual);
    }

    @Test
    public void shouldDecreaseCreditAndIncreaseSaving() { // должен уменьшиться кредитный счет и увеличиться сберегательный

        boolean actual = bank.transfer(credit, saving, 1_000);

        Assertions.assertEquals(5_000, credit.getBalance());
        Assertions.assertEquals(6_000, saving.getBalance());
        Assertions.assertTrue(actual);
    }

    @Test
    public void shouldNotChangeBalanceIfAmountZeroFromSavingToCredit() { //  счета не изменится если платеж 0

        boolean actual = bank.transfer(saving, credit, 0);

        Assertions.assertEquals(5_000, saving.getBalance());
        Assertions.assertEquals(6_000, credit.getBalance());
        Assertions.assertFalse(actual);
    }

    @Test
    public void shouldNotChangeBalanceIfAmountNegativeFromSavingToCredit() { //  счета не изменятся если платеж отрицательный

        boolean actual = bank.transfer(saving, credit, -1_000);

        Assertions.assertEquals(5_000, saving.getBalance());
        Assertions.assertEquals(6_000, credit.getBalance());
        Assertions.assertFalse(actual);
    }

    @Test
    public void shouldNotChangeBalanceIfAmountZeroFromCreditToSaving() { //  счета не изменятся если платеж 0

        boolean actual = bank.transfer(credit, saving, 0);

        Assertions.assertEquals(6_000, credit.getBalance());
        Assertions.assertEquals(5_000, saving.getBalance());
        Assertions.assertFalse(actual);
    }

    @Test
    public void shouldNotChangeBalanceIfAmountNegativeFromCreditToSaving() { //  счета не изменятся если платеж отрицательный

        boolean actual = bank.transfer(credit, saving, -1_000);

        Assertions.assertEquals(6_000, credit.getBalance());
        Assertions.assertEquals(5_000, saving.getBalance());
        Assertions.assertFalse(actual);
    }

    @Test
    public void shouldNotChangeIfAmountMoreThanBalance() { //  счета не изменится если платеж больше текущего баланса

        bank.transfer(saving, credit, 4500);

        Assertions.assertEquals(5_000, saving.getBalance());
        Assertions.assertEquals(6_000, credit.getBalance());

    }

    @Test
    public void shouldFalseWhenAmountMoreThanBalance() { //  должен вернуться false, так как операцию провести невозможно

        boolean actual = bank.transfer(saving, credit, 4500);
        Assertions.assertFalse(actual);
    }

    @Test
    public void shouldNotChangeIfAmountMoreThanCreditLimit() { //  счета не изменятся если платеж больше текущего баланса

        bank.transfer(credit, saving, 17_000);

        Assertions.assertEquals(6_000, credit.getBalance());
        Assertions.assertEquals(5_000, saving.getBalance());
    }

    @Test
    public void shouldFalseWhenAmountMoreThanCreditLimit() { //  должен вернуться false, так как операцию провести невозможно

        boolean actual = bank.transfer(saving, credit, 17_000);
        Assertions.assertFalse(actual);
    }

    @Test
    public void shouldNotChangeIfAmountMoreThanMaxBalance() { //  счета не изменятся если платеж больше макс.баланса сбер. счета

        bank.transfer(credit, saving, 11_000);

        Assertions.assertEquals(6_000, credit.getBalance());
        Assertions.assertEquals(5_000, saving.getBalance());

    }

    @Test
    public void shouldFalseIfAmountMoreThanMaxBalance() { //  должен вернуться false если платеж больше макс.баланса сбер. счета

        boolean actual = bank.transfer(credit, saving, 11_000);
        Assertions.assertFalse(actual);
    }
}