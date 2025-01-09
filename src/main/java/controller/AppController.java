package controller;

import model.User;
import model.Wallet;
import service.CategoryService;
import service.UserService;
import service.WalletService;

import java.util.Scanner;

public class AppController {
    private final UserService userService;
    private final WalletService walletService;
    private final CategoryService categoryService;
    private User currentUser;

    public AppController(UserService userService, WalletService walletService, CategoryService categoryService) {
        this.userService = userService;
        this.walletService = walletService;
        this.categoryService = categoryService;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Добро пожаловать в систему управления личными финансами!");
        while (true) {
            if (currentUser == null) {
                System.out.println("Введите Ваш логин:");
                String login = scanner.nextLine();
                System.out.println("Введите Ваш пароль:");
                String password = scanner.nextLine();

                userService.authenticate(login, password).ifPresentOrElse(user -> {
                    currentUser = user;
                    System.out.println("Добро пожаловать, " + user.getLogin());
                }, () -> System.out.println("Ошибка! Неверный логин или пароль."));
            } else {
                System.out.println("\nВыберите действие:");
                System.out.println("1. Добавить доход");
                System.out.println("2. Добавить расход");
                System.out.println("3. Установить бюджет для категории");
                System.out.println("4. Просмотреть текущий баланс и категории");
                System.out.println("5. Добавить новую категорию");
                System.out.println("6. Выйти из приложения");

                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1 -> {
                        System.out.println("Введите сумму дохода:");
                        double income = Double.parseDouble(scanner.nextLine());
                        walletService.addIncome(currentUser.getWallet(), income);
                    }
                    case 2 -> {
                        System.out.println("Введите категорию расхода:");
                        String category = scanner.nextLine();
                        System.out.println("Введите сумму расхода:");
                        double expense = Double.parseDouble(scanner.nextLine());
                        walletService.addExpense(currentUser.getWallet(), category, expense);
                    }
                    case 3 -> {
                        System.out.println("Введите категорию:");
                        String category = scanner.nextLine();
                        System.out.println("Введите лимит бюджета:");
                        double limit = Double.parseDouble(scanner.nextLine());
                        walletService.setBudget(currentUser.getWallet(), category, limit);
                    }
                    case 4 -> walletService.displayOverview(currentUser.getWallet());
                    case 5 -> {
                        System.out.println("Введите имя категории:");
                        String name = scanner.nextLine();
                        System.out.println("Это категория дохода? (да/нет):");
                        boolean isIncome = scanner.nextLine().equalsIgnoreCase("да");
                        categoryService.addCategory(name, isIncome);
                    }
                    case 6 -> {
                        System.out.println("Благодарим за использование сервиса!Всего доброго.");
                        return;
                    }
                    default -> System.out.println("Пожалуйста, повторите выбор!");
                }
            }
        }
    }
}

