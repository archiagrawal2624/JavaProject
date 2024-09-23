package com.banking;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataPersistence {

    private final String FILE_NAME = "data/accounts.dat";

    public void saveAccounts(List<Account> accounts) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(accounts);
        } catch (IOException e) {
            System.out.println("Error saving accounts: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public List<Account> loadAccounts() {
        List<Account> accounts = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            accounts = (List<Account>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading accounts: " + e.getMessage());
        }
        return accounts;
    }
}
