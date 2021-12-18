package com.company;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private int id;
    private double balance;
    private String name;
    private User accountHolder;
    ArrayList<Transaction> transactions = new ArrayList<>();

    public Account(double balance, String name, User accountHolder) {
        this.id = Bank.genUniqueId();
        this.balance = balance;
        this.name = name;
        this.accountHolder = accountHolder;
    }

    void getInfo() {
        System.out.println("Id " + this.id);
        System.out.println("Баланс " + this.balance);
        System.out.println("Тип счета " + this.name);
        System.out.println("Владелец " + getAccountHolder().getFirstName() + " " + getAccountHolder().getLastName());
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setAccountHolder(User accountHolder) {
        this.accountHolder = accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public User getAccountHolder() {
        return accountHolder;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
    }
}
