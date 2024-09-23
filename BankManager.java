package com.banking;

import java.util.ArrayList;
import java.util.List;

public class BankManager {
    private List<Account> accounts;
    private int nextAccountId;
    private DataPersistence dataPersistence;

    public BankManager() {
        dataPersistence = new DataPersistence();
        accounts = dataPersistence.loadAccounts();
        nextAccountId = accounts.size() + 1;
    }

    public Account createAccount(String accountHolder) {
        Account newAccount = new Account(nextAccountId++, accountHolder);
        accounts.add(newAccount);
        dataPersistence.saveAccounts(accounts);
        return newAccount;
    }

    public Account getAccount(int accountId) {
        for (Account account : accounts) {
            if (account.getAccountId() == accountId) {
                return account;
            }
        }
        System.out.println("Account not found.");
        return null;
    }

    public List<Account> getAllAccounts() {
        return accounts;
    }

    public void closeAccount(int accountId) {
        accounts.removeIf(account -> account.getAccountId() == accountId);
        dataPersistence.saveAccounts(accounts);
    }

    public void transferFunds(int fromAccountId, int toAccountId, double amount) {
        Account fromAccount = getAccount(fromAccountId);
        Account toAccount = getAccount(toAccountId);

        if (fromAccount != null && toAccount != null && amount > 0 && amount <= fromAccount.getBalance()) {
            fromAccount.withdraw(amount);
            toAccount.deposit(amount);
            System.out.println("Transferred: $" + amount + " from Account ID " + fromAccountId + " to Account ID " + toAccountId);
            dataPersistence.saveAccounts(accounts);
        } else {
            System.out.println("Transfer failed. Check accounts and amount.");
        }
    }
}
