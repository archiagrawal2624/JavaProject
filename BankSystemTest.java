package com.banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BankSystemTest {
    private BankManager bankManager;

    @BeforeEach
    public void setUp() {
        bankManager = new BankManager();
    }

    @Test
    public void testCreateAccount() {
        Account account = bankManager.createAccount("Archi");
        assertNotNull(account);
        assertEquals("Archi", account.getAccountHolder());
        assertEquals(0.0, account.getBalance());
        assertFalse(account.isClosed());
    }

    @Test
    public void testDeposit() {
        Account account = bankManager.createAccount("Rahul");
        account.deposit(100.0);
        assertEquals(100.0, account.getBalance());
        assertEquals(1, account.getTransactionHistory().size());
        assertEquals("Deposit", account.getTransactionHistory().get(0).toString());
    }

    @Test
    public void testWithdraw() {
        Account account = bankManager.createAccount("Sumit");
        account.deposit(200.0);
        account.withdraw(100.0);
        assertEquals(100.0, account.getBalance());
        assertEquals(2, account.getTransactionHistory().size());
        assertEquals("Withdraw", account.getTransactionHistory().get(1).toString());
    }

    @Test
    public void testWithdrawInsufficientFunds() {
        Account account = bankManager.createAccount("Naman");
        account.deposit(50.0);
        account.withdraw(100.0);
        assertEquals(50.0, account.getBalance()); // Balance should remain the same
        assertEquals(1, account.getTransactionHistory().size()); // No new transaction
    }

    @Test
    public void testCloseAccount() {
        Account account = bankManager.createAccount("Harsh");
        account.closeAccount();
        assertTrue(account.isClosed());
        assertEquals(1, account.getTransactionHistory().size()); // Closing transaction should be logged
        assertEquals("Account Closed", account.getTransactionHistory().get(0).toString());
    }

    @Test
    public void testTransferFunds() {
        Account fromAccount = bankManager.createAccount("Shivani");
        Account toAccount = bankManager.createAccount("Rajat");
        fromAccount.deposit(300.0);
        bankManager.transferFunds(fromAccount.getAccountId(), toAccount.getAccountId(), 150.0);

        assertEquals(150.0, fromAccount.getBalance());
        assertEquals(150.0, toAccount.getBalance());
        assertEquals(2, fromAccount.getTransactionHistory().size());
        assertEquals("Withdraw", fromAccount.getTransactionHistory().get(1).toString());
        assertEquals(1, toAccount.getTransactionHistory().size());
        assertEquals("Deposit", toAccount.getTransactionHistory().get(0).toString());
    }

    @Test
    public void testTransferFundsInsufficientBalance() {
        Account fromAccount = bankManager.createAccount("Sanchita");
        Account toAccount = bankManager.createAccount("Ruhi");
        fromAccount.deposit(100.0);
        bankManager.transferFunds(fromAccount.getAccountId(), toAccount.getAccountId(), 200.0);

        assertEquals(100.0, fromAccount.getBalance()); // Balance should remain the same
        assertEquals(0.0, toAccount.getBalance()); // No funds should be transferred
    }

    @Test
    public void testViewAllAccounts() {
        bankManager.createAccount("Teena");
        bankManager.createAccount("Pinky");
        List<Account> accounts = bankManager.getAllAccounts();
        assertEquals(2, accounts.size());
    }
}
