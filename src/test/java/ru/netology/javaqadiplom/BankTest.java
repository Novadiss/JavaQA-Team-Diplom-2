package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BankTest {
    SavingAccount savingAccount1 = new SavingAccount(
            2_000,
            0,
            10_000,
            5
    );

    SavingAccount savingAccount2 = new SavingAccount(
            1_000,
            0,
            10_000,
            5
    );

    CreditAccount creditAccount1 = new CreditAccount(
            4_000,
            5_000,
            15
    );

    CreditAccount creditAccount2 = new CreditAccount(
            0,
            5_000,
            15
    );


    @Test
    public void testTransferWithPositiveAmountForCredit() { //проверка прохождения положительного платежа с CreditAccount на CreditAccount
        Bank temp = new Bank();
        temp.transfer(creditAccount1, creditAccount2, 3_000);

        int expected = 3_000;
        int actual = creditAccount2.getBalance();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testTransferWithPositiveAmountForSaving() { //проверка прохождения положительного платежа с SavingAccount на SavingAccount
        Bank temp = new Bank();
        temp.transfer(savingAccount1, savingAccount2, 1_000);

        int expected = 2_000;
        int actual = savingAccount2.getBalance();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testTransferWithPositiveAmountFromSavingToCredit() { //проверка прохождения положительного платежа с SavingAccount на CreditAccount
        Bank temp = new Bank();
        temp.transfer(savingAccount1, creditAccount2, 1_000);

        int expected = 1_000;
        int actual = creditAccount2.getBalance();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testTransferWithPositiveAmountFromCreditToSaving() { //проверка прохождения положительного платежа с CreditAccount на SavingAccount
        Bank temp = new Bank();
        temp.transfer(creditAccount1, savingAccount2, 1_000);

        int expected = 2_000;
        int actual = savingAccount2.getBalance();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testTransferWithNegativeAmount() { //проверка что отрицательный трансфер невозможен
        Bank temp = new Bank();

        boolean actual = temp.transfer(creditAccount1, savingAccount2, -1_000);
        Assertions.assertFalse(actual);
    }

    @Test
    public void testTransferWithZeroAmount() { //проверка что нулевой трансфер невозможен
        Bank temp = new Bank();

        boolean actual = temp.transfer(creditAccount1, savingAccount2, 0);
        Assertions.assertFalse(actual);
    }

    @Test
    public void testTransferWithNegativeAmountBalanceAdderNotChange() { //проверка что при отказе отрицательного трансфера счет получателя не изменится
        Bank temp = new Bank();
        temp.transfer(creditAccount1, creditAccount2, -3_000);

        int expected = 0;
        int actual = creditAccount2.getBalance();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testTransferWithNegativeAmountBalancePayerNotChange() { //проверка что при отказе отрицательного трансфера счет отправителя не изменится
        Bank temp = new Bank();
        temp.transfer(creditAccount1, creditAccount2, -3_000);

        int expected = 4_000;
        int actual = creditAccount1.getBalance();

        Assertions.assertEquals(expected, actual);
    }
}
