package com.banking;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Account implements Serializable {
    private static final long serialVersionUID = 1L;
    private int accountId;
    private String accountHolder;
    private double balance;
    private List<Transaction> transactionHistory;

    public Account(int accountId, String accountHolder) {
        this.accountId = accountId;
        this.accountHolder = accountHolder;
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();
    }

    public int getAccountId() {
        return accountId;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add(new Transaction("Deposit", amount));
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add(new Transaction("Withdraw", amount));
        } else {
            System.out.println("Invalid withdrawal amount.");
        }
    }

    public void closeAccount() {
        // Handle final actions if needed
    }

    @Override
    public String toString() {
        return "Account ID: " + accountId + ", Holder: " + accountHolder + ", Balance: $" + balance;
    }
}
