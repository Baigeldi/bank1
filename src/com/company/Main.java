package com.company;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {
    static Bank bank;
    static User loggedUser;
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<User> users = new ArrayList<>();
    static SimpleDateFormat sdf = new SimpleDateFormat("dd - MM - yyyy");

    public static void main(String[] args) {

        ArrayList<Account> accounts = new ArrayList<>();
        ArrayList<Account> baysalList = new ArrayList<>();
        User baysal = new User("Байсал", "Уланбеков", baysalList, "11", "22");
        Account baysalKgz = new Account(300, "KGS", baysal);
        Account baysalUsd = new Account(3000, "USD", baysal);
        baysalList.add(baysalKgz);
        baysalList.add(baysalUsd);
        users.add(baysal);
        accounts.addAll(baysalList);

        ArrayList<Account> almazList = new ArrayList<>();
        User almaz = new User("Алмаз", "Сагынбеков", almazList, "al@gmail.com", "22");
        Account almazKGZ = new Account(4000, "KGS", almaz);
        Account almazUSD = new Account(5000, "USD", almaz);
        almazList.add(almazKGZ);
        almazList.add(almazUSD);
        users.add(almaz);
        accounts.addAll(almazList);

        System.out.println("------KYRGYZSTAN BANK------");
        System.out.println("*****************************");

        login();
        bank = new Bank("", users, accounts);
    }

    private static void login() {
        Scanner scanner = new Scanner(System.in);
        int count = 0;
        while (true) {
            try {
                System.out.println("Введите ваш логин...");
                String login = scanner.nextLine();
                System.out.println("Введите ваш пароль...");
                String password = scanner.nextLine();
                while (true) {
                    for (User user : users) {
                        if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                            System.out.println("Здравствуйте, " + user.getFirstName() + "!");
                            System.out.println("Что будем делать сегодня?");
                            System.out.println("**************************");
                            System.out.println();
                            loggedUser = user;
                            mainMenu();
                        }
                    }
                    if (count < 2) {
                        count++;
                        System.out.println("Неправильный логин и пароль! У вас осталось " + (3 - count) + " попыток ввода");
                        System.out.println("Введите ваш логин...");
                        login = scanner.nextLine();
                        System.out.println("Введите ваш пароль...");
                        password = scanner.nextLine();
                    } else {
                        System.out.println("Вы 3 раза ввели логин и пароль. Система закрывается!");
                        System.exit(0);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Неправильный формат ввода");
            }
        }
    }

    private static void mainMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Мой аккаунт");
            System.out.println("2. Пополнить счет");
            System.out.println("3. Снять деньги");
            System.out.println("4. Перевести деньги на другой счет");
            System.out.println("5. Информация о пополнение счета");
            System.out.println("6. Редактировать");
            System.out.println("7. Выход");
            try {
                int choice = scanner.nextInt();
                if (choice < 8 && choice > 0) {
                    switch (choice) {
                        case 1:
                            myAccount();
                            mainMenu();
                            break;
                        case 2:
                            PopolnenieScheta();
                            mainMenu();
                            break;
                        case 3:
                            snyatDengi();
                            mainMenu();
                            break;
                        case 4:
                            transfer();
                            mainMenu();
                            break;
                        case 5:
                            infoPopol();
                            mainMenu();
                            break;
                        case 6:
                            redactirovat();
                            mainMenu();
                            break;
                        case 7:
                            System.exit(0);
                            break;
                    }
                } else {
                    System.out.println("Нет такого раздела! Введите правильное число");
                    scanner.next();
                }
            } catch (Exception e) {
                System.out.println("Неверный формат ввода! Повторите ввод");
                scanner.next();
            }
        }
    }

    private static void infoPopol() {
        User user = loggedUser;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy  HH:mm:ss");
        System.out.println("Пожалуйста, выберите аккаунт ...");
        System.out.println();
        System.out.println("1. USD");
        System.out.println("2. KGS");
        try {
            int choice = scanner.nextInt();
            if (choice == 1){
                for (Transaction t : user.getAccountList().get(1).getTransactions()) {
                    System.out.println("Сумма: " + t.getAmount() + " | Тип сделки: " + t.getType() + " | Дата: " + sdf.format(t.getTimeStamp()));
                    System.out.println("----------------------------------------------------------");
                    restart();
                }
            }
            else if (choice == 2){
                for (Transaction t: user.getAccountList().get(0).getTransactions()) {
                    System.out.println("Сумма: " + t.getAmount() + " | Тип сделки: " + t.getType() + " | Дата: " + sdf.format(t.getTimeStamp()));
                    System.out.println("----------------------------------------------------------");
                    restart();
                }
            }else {
                System.out.println("Нет такого раздела! Введите правильное число!");
                scanner.next();
            }
        }catch (Exception e){
            System.out.println("Неверный формат ввода! Повторите ввод");
            scanner.next();
        }
    }

    private static void transfer() {
        boolean x = false;
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите ID которому хотели перевести");
            int id = scanner.nextInt();
            for (User user : users) {
                if (id == user.getId()) {
                    User.perevod(user);
                    x = true;
                }
            }
            if (x) {
                break;
            }
            if (x == false) {
                System.err.println("Такого пользователя нет!");
            }
        }
    }

    private static void snyatDengi() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("С какого счета вы хотите вывести деньги? ");
            System.out.println("1. USD");
            System.out.println("2. KGS");
            System.out.println("3. В главное меню");
            try {
                int choice = scanner.nextInt();
                if (choice < 4 && choice > 0) {
                    switch (choice) {
                        case 1:
                            snyatUSD(loggedUser.getAccountList().get(0));
                            break;
                        case 2:
                            snyatKGS(loggedUser.getAccountList().get(1));
                            break;
                        case 3:
                            mainMenu();
                    }
                } else {
                    System.out.println("Нет такого раздела! Введите правильное число");
                    scanner.next();
                }
            } catch (Exception e) {
                System.out.println("Неверный формат ввода! Повторите ввод");
                scanner.next();
            }
        }
    }

    private static void redactirovat() {
        Scanner scanner = new Scanner(System.in);
        User user = loggedUser;
        System.out.println("Ваши данные:");
        System.out.println("---------------");
        System.out.println("Имя: " + user.getFirstName());
        System.out.println("Фамилия: " + user.getLastName());
        System.out.println("Логин: " + user.getLogin());
        System.out.println("Пароль: " + user.getPassword());
        System.out.println("Аккаунты: " + user.getAccountList().get(0).getName() + ", " + user.getAccountList().get(1).getName());
        System.out.println("--------------------------------------------------");
        System.out.println();
        while (true) {
            System.out.println("1. Изменить имя");
            System.out.println("2. Изменить фамилию");
            System.out.println("3. Изменить логин");
            System.out.println("4. Изменить пароль");
            System.out.println("5. Выйти в главное меню");
            try {
                int choice = scanner.nextInt();
                if (choice < 6 && choice > 0) {
                    switch (choice) {
                        case 1:
                            System.out.println("Пожалуйста, введите ваше новое имя...");
                            String firstName = scanner.nextLine();
                            user.setFirstName(firstName);
                            System.out.println("Ваше имя изменено");
                            mainMenu();
                        case 2:
                            System.out.println("Пожалуйста, введите ваше новую фамилию...");
                            String lastName = scanner.nextLine();
                            user.setFirstName(lastName);
                            System.out.println("Ваше имя изменено");
                            mainMenu();
                        case 3:
                            System.out.println("Пожалуйста, введите ваш новый логин...");
                            String login = scanner.nextLine();
                            user.setFirstName(login);
                            System.out.println("Ваше имя изменено");
                            mainMenu();
                        case 4:
                            System.out.println("Пожалуйста, введите ваш новый пароль...");
                            String password = scanner.nextLine();
                            user.setFirstName(password);
                            System.out.println("Ваше имя изменено");
                            mainMenu();
                        case 5:
                            mainMenu();
                    }
                } else {
                    System.out.println("Нет такого раздела! Введите правильное число");
                    scanner.next();
                }
            } catch (Exception e) {
                System.out.println("Неверный формат ввода! Повторите ввод");
                scanner.next();
            }
        }
    }

    private static void PopolnenieScheta() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("На какой счет вы хотите внести депозит? ");
            System.out.println("1. USD");
            System.out.println("2. KGS");
            System.out.println("3. В главное меню");
            try {
                int choice = scanner.nextInt();
                if (choice < 4 && choice > 0) {
                    switch (choice) {
                        case 1:
                            popolUSD(loggedUser.getAccountList().get(1));
                            break;
                        case 2:
                            popolKGS(loggedUser.getAccountList().get(0));
                            break;
                        case 3:
                            mainMenu();
                    }
                } else {
                    System.out.println("Нет такого раздела! Введите правильное число");
                    scanner.next();
                }
            } catch (Exception e) {
                System.out.println("Неверный формат ввода! Повторите ввод");
                scanner.next();
            }
        }
    }

    private static void popolKGS(Account account) {
        System.out.println("Введите сумму:");
        double summa = scanner.nextDouble();
        double newBalans = account.getBalance() + summa;
        account.setBalance(newBalans);
        Transaction transaction = new Transaction("Пополнение счете",summa,sdf.format(new Date()),account);
        transaction.getInfo();
        restart();
    }

    private static void popolUSD(Account account) {
        System.out.println("Введите сумму:");
        double summa = scanner.nextDouble();
        double newBalans = account.getBalance() + summa;
        account.setBalance(newBalans);
        Transaction transaction = new Transaction("Пополнение счете",summa,sdf.format(new Date()),account);
        transaction.getInfo();
        restart();

    }

    private static void snyatKGS(Account account) {
        System.out.println("Введите сумму:");
        double summa = scanner.nextDouble();
        double newBalans = account.getBalance() - summa;
        account.setBalance(newBalans);
        Transaction transaction = new Transaction("Пополнение счете",summa,sdf.format(new Date()),account);
        transaction.getInfo();
        restart();
    }

    private static void snyatUSD(Account account) {
        System.out.println("Введите сумму:");
        double summa = scanner.nextDouble();
        double newBalans = account.getBalance() - summa;
        account.setBalance(newBalans);
        Transaction transaction = new Transaction("Пополнение счете",summa,sdf.format(new Date()),account);
        transaction.getInfo();
        restart();
    }

    private static void myAccount() {
        User user = loggedUser;
        System.out.println("Ваши данные:");
        System.out.println("---------------------");
        System.out.println("Имя: " + user.getFirstName());
        System.out.println("Фамилия: " + user.getFirstName());
        System.out.println("Логин: " + user.getLogin());
        System.out.println("Пароль: " + user.getPassword());
        System.out.println("ID: " + user.getId());
        System.out.println("Баланс USD: " + user.getAccountList().get(1).getBalance());
        System.out.println("Баланс KGS: " + user.getAccountList().get(0).getBalance());
        restart();
    }

    static void restart() {
        System.out.println();
        boolean d = false;
        System.out.println("Нажмите любое число чтобы перейти в гланое меню");
        System.out.println("Нажмите 0 чтобы завершит процесс и сохранит изминение");
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                int b = scanner.nextInt();
                if (b == 0) {
//                    savingData();
                }
                d = true;
                mainMenu();
                break;
            } catch (Exception e) {
                System.err.println("Нажмите любое ЧИСЛО чтобы перейти в гланое меню!");
            }
            if (d) {
                break;
            }
        }
    }
}
