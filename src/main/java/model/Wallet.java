package model;

import java.util.HashMap;
import java.util.Map;

public class Wallet {
    private double balance;
    private final Map<String, Double> categoryBalances;
    private final Map<String, Double> budgets;

    public Wallet() {
        this.balance = 0.0;
        this.categoryBalances = new HashMap<>();
        this.budgets = new HashMap<>();
    }

    public double getBalance() {
        return balance;
    }

    public void addIncome(double amount) {
        balance += amount;
    }

    public void addExpense(String category, double amount) {
        categoryBalances.put(category, categoryBalances.getOrDefault(category, 0.0) - amount);
        balance -= amount;
    }

    public void setBudget(String category, double limit) {
        budgets.put(category, limit);
    }

    public double getRemainingBudget(String category) {
        return budgets.getOrDefault(category, 0.0) - categoryBalances.getOrDefault(category, 0.0);
    }

    public Map<String, Double> getCategoryBalances() {
        return categoryBalances;
    }
}
