package com.company;

import java.util.Date;

public class Transaction {
    private String type;
    private double amount;
    private static String timeStamp;
    private Account account;

    public Transaction(String type,double amount, String timeStamp, Account account) {
        this.type = type;
        this.amount = amount;
        this.timeStamp = timeStamp;
        this.account = account;
    }
    void getInfo(){
        System.out.println("Тип транзакции: "+this.type);
        System.out.println("Сумма транзакции: "+this.amount);
        System.out.println("Дата транзакции: "+this.getTimeStamp());
        System.out.println("Баланс после транзакции: "+getAccount().getBalance());
        System.out.println("Дата транзакции: "+this.getTimeStamp());
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public static void setTimeStamp(String timeStamp) {
        Transaction.timeStamp = timeStamp;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public double getAmount() {
        return amount;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public Account getAccount() {
        return account;
    }
}
