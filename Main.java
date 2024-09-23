package com.banking;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BankManager bankManager = new BankManager();
        Scanner scanner = new Scanner(System.in);
        String command;

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Create Account");
            System.out.println("2. View Account");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Transfer Funds");
            System.out.println("6. View Transaction History");
            System.out.println("7. Close Account");
            System.out.println("8. View All Accounts");
            System.out.println("9. Exit");
            System.out.print("Choose an option: ");
            command = scanner.nextLine();

            switch (command) {
                case "1":
                    System.out.print("Enter account holder name: ");
                    String holderName = scanner.nextLine();
                    bankManager.createAccount(holderName);
                    break;
                case "2":
                    System.out.print("Enter account ID: ");
                    int viewId = Integer.parseInt(scanner.nextLine());
                    Account account = bankManager.getAccount(viewId);
                    if (account != null) {
                        System.out.println(account);
                    }
                    break;
                case "3":
                    System.out.print("Enter account ID: ");
                    int depositId = Integer.parseInt(scanner.nextLine());
                    Account depositAccount = bankManager.getAccount(depositId);
                    if (depositAccount != null) {
                        System.out.print("Enter amount to deposit: ");
                        double depositAmount = Double.parseDouble(scanner.nextLine());
                        depositAccount.deposit(depositAmount);
                    }
                    break;
                case "4":
                    System.out.print("Enter account ID: ");
                    int withdrawId = Integer.parseInt(scanner.nextLine());
                    Account withdrawAccount = bankManager.getAccount(withdrawId);
                    if (withdrawAccount != null) {
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawAmount = Double.parseDouble(scanner.nextLine());
                        withdrawAccount.withdraw(withdrawAmount);
                    }
                    break;
                case "5":
                    System.out.print("Enter from account ID: ");
                    int fromId = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter to account ID: ");
                    int toId = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter amount to transfer: ");
                    double transferAmount = Double.parseDouble(scanner.nextLine());
                    bankManager.transferFunds(fromId, toId, transferAmount);
                    break;
                case "6":
                    System.out.print("Enter account ID: ");
                    int historyId = Integer.parseInt(scanner.nextLine());
                    Account historyAccount = bankManager.getAccount(historyId);
                    if (historyAccount != null) {
                        List<Transaction> transactions = historyAccount.getTransactionHistory();
                        if (transactions.isEmpty()) {
                            System.out.println("No transactions found.");
                        } else {
                            transactions.forEach(System.out::println);
                        }
                    }
                    break;
                case "7":
                    System.out.print("Enter account ID to close: ");
                    int closeId = Integer.parseInt(scanner.nextLine());
                    bankManager.closeAccount(closeId);
                    break;
                case "8":
                    List<Account> accounts = bankManager.getAllAccounts();
                    if (accounts.isEmpty()) {
                        System.out.println("No accounts available.");
                    } else {
                        accounts.forEach(System.out::println);
                    }
                    break;
                case "9":
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }
}
