package service;

import model.Wallet;

import java.util.Map;

public class WalletService {
    public void addIncome(Wallet wallet, double amount) {
        wallet.addIncome(amount);
        System.out.println("Добавлена информация о Вашем доходе: +" + amount + ". Текущий баланс: " + wallet.getBalance());
    }

    public void addExpense(Wallet wallet, String category, double amount) {
        double remainingBudget = wallet.getRemainingBudget(category);
        if (remainingBudget < amount) {
            System.out.println("Превышение лимита бюджета для категории: " + category);
        } else {
            wallet.addExpense(category, amount);
            System.out.println("Добавлена информация о Вашем расходе: -" + amount + ". Текущий баланс: " + wallet.getBalance());
        }
    }

    public void setBudget(Wallet wallet, String category, double limit) {
        wallet.setBudget(category, limit);
        System.out.println("Установлен бюджет для категории '" + category + "': " + limit);
    }

    public void displayOverview(Wallet wallet) {
        System.out.println("Общий баланс: " + wallet.getBalance());
        Map<String, Double> categoryBalances = wallet.getCategoryBalances();
        System.out.println("Баланс по категориям:");
        for (Map.Entry<String, Double> entry : categoryBalances.entrySet()) {
            System.out.println("Категория: " + entry.getKey() + ", Остаток: " + entry.getValue());
        }
    }
}