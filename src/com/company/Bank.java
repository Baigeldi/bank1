package com.company;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private String BankName;
    private ArrayList<User> customers;
    private ArrayList<Account> accounts;
    private static List<Integer> ids = new ArrayList<>();

    public Bank(String bankName, ArrayList<User> customers, ArrayList<Account> accounts) {
        BankName = bankName;
        this.customers = customers;
        this.accounts = accounts;
    }

    public static int genUniqueId() {
        int id = 0;
        while (true) {
            id = (int) (Math.random() * 899) + 100;
            if (checkForDuplicates(id)) {
                ids.add(id);
                break;
            }
        }
        return id;
    }

    private static boolean checkForDuplicates(int id) {
        for (int i : ids) {
            return (i == id)? true: false;
        }
        return true;
    }

    public void setBankName(String bankName) {
        BankName = bankName;
    }

    public String getBankName() {
        return BankName;
    }

    public List<User> getCustomers() {
        return customers;
    }

    public List<Account> getAccounts() {
        return accounts;
    }
}
