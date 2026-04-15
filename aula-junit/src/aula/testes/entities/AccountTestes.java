package aula.testes.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import aula.testes.factory.AccountFactory;
import aula.entities.Account;

public class AccountTestes {

  @Test
  public void depositShouldIncreaseBalanceWhenPositiveAmount() {
    // Arrange
    double amount = 200.0;
    double expectedValue = 196.0;
    Account acc = AccountFactory.createEmptyAccount();

    // Act
    acc.deposit(amount);

    // Assert
    Assertions.assertEquals(expectedValue, acc.getBalance());
  }

  @Test
  public void depositShouldDoNothingWhenNegativeAmount() {
    // Arrange
    double expectedValue = 100.0;
    Account acc = AccountFactory.createAccount(expectedValue);
    double amount = -200.0;

    // Act
    acc.deposit(amount);

    // Assert
    Assertions.assertEquals(expectedValue, acc.getBalance());
  }

  @Test
  public void fullWithdrawShouldClearBalanceAndReturnFullBalance() {
    // Arrange
    double expectedValue = 0.0;
    double initialBalance = 800.0;
    Account acc = AccountFactory.createAccount(initialBalance);

    // Act
    double result = acc.fullWithdraw();

    System.out.println("expectedValue: " + expectedValue);
    // Assert
    Assertions.assertTrue(expectedValue == acc.getBalance());
    Assertions.assertTrue(result == initialBalance);
  }

  @Test
  public void withdrawShouldDecreaseBalanceWhenSufficientBalance() {
    // Arrange
    Account acc = AccountFactory.createAccount(800.0);
    double amount = 500.0;
    double expectedValue = 300.0;

    // Act
    acc.withdraw(amount);

    // Assert
    Assertions.assertEquals(expectedValue, acc.getBalance());
  }

  @Test
  public void withdrawShouldThrowExceptionWhenInsufficientBalance() {
    // Arrange
    Account acc = AccountFactory.createAccount(800.0);
    double amount = 801.0;

    // Assert
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      // Act
      acc.withdraw(amount);
    });

    // Maneira reduzida de fazer o teste, ao sacar um valor maior que o limite.
    // Assert
    // Assertions.assertThrows(IllegalArgumentException.class, () -> {
    // Account acc = AccountFactory.createAccount(800.0);
    // acc.withdraw(801.0);
    // });
  }
}
