package com.company;

import java.util.List;
import java.util.Scanner;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private List<Account> accountList; //KGS and USD
    private String login;
    private String password;

    public User(String firstName, String lastName, List<Account> accountList, String login, String password) {
        this.id = Bank.genUniqueId();
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountList = accountList;
        this.login = login;
        this.password = password;
    }

    public static void perevod(User user) {
        while (true) {
            try {
                System.out.println("Введите сумму, которую хотите перевести...");
                Scanner scanner = new Scanner(System.in);
                int n = scanner.nextInt();
                if (n > user.getAccountList().get(1).getBalance()) {
                    System.err.println("Вы превысили лимит денег");
                }
                if (n < user.getAccountList().get(1).getBalance()) {
                    System.out.println("Вы успешно перевели деньги данному пользователю");
                }
            } catch (Exception e) {
                System.err.println("Ошибка! Неверный формат ввода!");
            }
        }
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
